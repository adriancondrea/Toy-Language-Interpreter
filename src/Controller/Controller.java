package Controller;

import CustomException.CustomException;
import Model.AbstractDataTypes.*;
import Model.ProgramState;
import Model.Statement.Statement;
import Model.Value.ReferenceValue;
import Model.Value.Value;
import Repository.Repository;

import java.util.*;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;


public class Controller {
    private final Repository repository;
    //if displayState flag is set to true, display the program state after each execution
    private Boolean displayState;
    private ExecutorService executor;
    public Controller(Repository repository){ this.repository = repository; displayState = false; }


    public List<ProgramState> getProgramStates(){
        return repository.getProgramStates();
    }

    public void oneStepExecution(){
        List<ProgramState> programStateList = removeCompletedPrograms(repository.getProgramStates());
        executor = Executors.newFixedThreadPool(2);
        ProgramState state = programStateList.get(0);
        state.getHeapTable().setContent(
                garbageCollector(
                        getSymbolTableAddresses(
                                programStateList.stream().map(programState -> programState.getSymbolTable().getContent().values()).collect(Collectors.toList()),
                                state.getHeapTable().getContent()
                        ),
                        state.getHeapTable().getContent()
                )
        );
        oneStepForAllPrograms(programStateList);
        List<ProgramState> newProgramStateList = removeCompletedPrograms(programStateList);
        if(newProgramStateList.isEmpty()){
            repository.setProgramStates(programStateList);
        }
        else {
            repository.setProgramStates(newProgramStateList);
        }
        executor.shutdown();
    }
    public void oneStepForAllPrograms(List<ProgramState> programStates){
        //before the execution, print the programState list into the log file
        programStates.forEach(repository::logProgramStateExecution);

        //run concurrently one step for each of the existing programStates

        //prepare the list of callables
        List<Callable<ProgramState>> callList = programStates.stream()
                .map((ProgramState p) -> (Callable<ProgramState>)(p::oneStepExecution)) .collect(Collectors.toList());

        //start the execution of the callables
        //it returns the list of new created PrgStates (namely threads)
        List<ProgramState> newProgramStates;
        try {
            newProgramStates = executor.invokeAll(callList).stream()
                    .map(future -> {
                        try {
                            return future.get();
                        } catch (CustomException | InterruptedException | ExecutionException e) {
                            /*HERE------------------------------------------------------------------------------------------
                            System.out.println(e.getMessage());
                            System.exit(1);
                            return null;
                             */
                            throw new CustomException(e.getMessage());
                        }
                    })
                    .filter(Objects::nonNull)
                    .collect(Collectors.toList());
        }
        catch (InterruptedException e){
            throw new CustomException(e.getMessage());
        }
        programStates.addAll(newProgramStates);
        programStates.forEach(repository::logProgramStateExecution);
        repository.setProgramStates(programStates);
        }


    Map<Integer, Value> garbageCollector(Set<Integer> symbolTableAddresses, Map<Integer, Value> heap){
        return heap.entrySet().stream()
                .filter(e -> symbolTableAddresses.contains(e.getKey()))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }

    Set<Integer> getSymbolTableAddresses(List<Collection<Value>> symbolTableValues, Map<Integer, Value> heapTable) {
        Set<Integer> symbolTableAddresses = new HashSet<>();
        symbolTableValues.forEach(symbolTable -> symbolTable.stream()
        .filter(v -> v instanceof ReferenceValue)
        .forEach(v -> {
            while (v instanceof ReferenceValue){
                symbolTableAddresses.add(((ReferenceValue) v).getAddress());
                v = heapTable.get(((ReferenceValue) v).getAddress());
            }
        }));
        return symbolTableAddresses;
    }

    public void setDisplayState(Boolean displayState) { this.displayState = displayState; }


    public void allStepExecution(){
        executor = Executors.newFixedThreadPool(2);
        //remove the completed programs
        List<ProgramState> programStates = removeCompletedPrograms(repository.getProgramStates());
        while(!programStates.isEmpty()){
            ProgramState state = programStates.get(0);
            state.getHeapTable().setContent(
                    garbageCollector(
                    getSymbolTableAddresses(
                            programStates.stream().map(programState -> programState.getSymbolTable().getContent().values()).collect(Collectors.toList()),
                            state.getHeapTable().getContent()
                    ),
                            state.getHeapTable().getContent()
                    )
            );
            oneStepForAllPrograms(programStates);
            programStates = removeCompletedPrograms(repository.getProgramStates());
        }
        executor.shutdownNow();
        //HERE the repository still contains at least one Completed program
        //and its List<ProgramState> is not empty. We have to eupdate the repository state
        repository.setProgramStates(programStates);
    }

    public void createState(Statement program){
        //create a new state from the program given as parameter
        ProgramState state;
        StackInterface<Statement> stack = new MyStack<>();
        stack.push(program);
        state = new ProgramState(stack, new MyDictionary<>(), new MyList<>(), new MyDictionary<>(), new MyHeap<>(), new MyLatch<>(), null);
        repository.addState(state);
    }

    List<ProgramState> removeCompletedPrograms(List<ProgramState> ProgramStates) {
        return  ProgramStates.stream()
                .filter(ProgramState::isNotCompleted)
                .collect(Collectors.toList());
    }

    public void typecheck() {
        repository.getProgramStates().get(0).typecheck();
    }
}
