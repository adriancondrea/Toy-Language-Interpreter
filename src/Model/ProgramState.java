package Model;

import CustomException.CustomException;
import Model.AbstractDataTypes.*;
import Model.Statement.Statement;
import Model.Type.Type;
import Model.Value.StringValue;
import Model.Value.Value;

import java.io.BufferedReader;
import java.util.concurrent.atomic.AtomicInteger;

public class ProgramState {

    private final StackInterface<Statement> executionStack;
    private final DictionaryInterface<String, Value> symbolTable;
    private final ListInterface<Value> outputList;
    private final DictionaryInterface<StringValue, BufferedReader> fileTable;
    private HeapInterface<Value> heapTable;
    private final LatchInterface<Integer> latchTable;
    Statement originalProgram;
    static final AtomicInteger previousId = new AtomicInteger(0);
    public final Integer id;

    public ProgramState(Statement originalProgram){
        this.executionStack = new MyStack<>();
        this.symbolTable = new MyDictionary<>();
        this.outputList = new MyList<>();
        this.fileTable = new MyDictionary<>();
        this.heapTable = new MyHeap<>();
        this.originalProgram = originalProgram;
        this.latchTable = new MyLatch<>();
        id = newId();
        if(originalProgram != null)
            executionStack.push(originalProgram);
    }

    public ProgramState(StackInterface<Statement> executionStack, DictionaryInterface<String, Value> symbolTable, ListInterface<Value> outputList, DictionaryInterface<StringValue, BufferedReader> fileTable, HeapInterface<Value> heapTable, LatchInterface<Integer> latchTable, Statement originalProgram) {
        this.executionStack = executionStack;
        this.symbolTable = symbolTable;
        this.outputList = outputList;
        this.fileTable = fileTable;
        this.heapTable = heapTable;
        this.latchTable = latchTable;
        this.originalProgram = originalProgram;
        id = newId();
        if(originalProgram != null)
            executionStack.push(originalProgram);
    }

    private Integer newId() {
        return previousId.incrementAndGet();
    }

    public StackInterface<Statement> getExecutionStack() {
        return executionStack;
    }

    public ListInterface<Value> getOutputList() {
        return this.outputList;
    }


    public DictionaryInterface<String, Value> getSymbolTable() {
        return this.symbolTable;
    }


    public DictionaryInterface<StringValue, BufferedReader> getFileTable() {
        return fileTable;
    }


    public HeapInterface<Value> getHeapTable() {
        return heapTable;
    }

    public void setHeapTable(HeapInterface<Value> heapTable) {
        this.heapTable = heapTable;
    }

    @Override
    public String toString() {
        return "~~id: " + id.toString()+"~~\n"+
                "\t\t~~Execution Stack_"+id.toString()+"~~\n" +
                executionStack.toString() +
                "~~~~~~~~~~~~~~~~~~~~~~~~"+
                "\n\t\t~~Symbol Table_"+id.toString()+"~~\n" +
                symbolTable.toString() +
                "~~~~~~~~~~~~~~~~~~~~~~~~"+
                "\n\t\t~~Output List_"+id.toString()+"~~\n" +
                outputList.toString()+
                "~~~~~~~~~~~~~~~~~~~~~~~~"+
                "\n\t\t~~File Table_"+id.toString()+"~~\n"+
                fileTable.KeysToString()+
                "~~~~~~~~~~~~~~~~~~~~~~~~"+
                "\n\t\t~~Heap Table_"+id.toString()+"~~\n"+
                heapTable.toString()+
                "~~~~~~~~~~END OF STATE~~~~~~~~~~";
    }

    public Boolean isNotCompleted() {
        return !this.getExecutionStack().isEmpty();
    }

    public ProgramState oneStepExecution() {
        if(executionStack.isEmpty())
            throw new CustomException("Stack is empty!");
        Statement currentStatement = executionStack.pop();
        return currentStatement.execute(this);
    }

    public void typecheck() {
        DictionaryInterface<String, Type> typeEnvironment = new MyDictionary<>();
        this.getExecutionStack().peek().typecheck(typeEnvironment);
    }

    public LatchInterface<Integer> getLatchTable() {
        return latchTable;
    }

}
