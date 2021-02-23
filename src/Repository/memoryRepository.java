package Repository;

import CustomException.RepositoryException;
import Model.ProgramState;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

public class memoryRepository implements Repository{
    private List<ProgramState> programStates;
    private String logFilePath = "log.txt";

    public memoryRepository(){
        programStates = new LinkedList<>();
    }

    public memoryRepository(String logFilePath){
        programStates = new LinkedList<>();
        this.logFilePath = logFilePath;
    }

    public memoryRepository(ProgramState programState, String logFilePath){
        this.programStates = new LinkedList<>();
        programStates.add(programState);
        this.logFilePath = logFilePath;
    }

    @Override
    public void addState(ProgramState newState) {
        programStates.add(newState);
    }


    @Override
    public void logProgramStateExecution(ProgramState programState) {
        PrintWriter logFile;
        try {
            logFile = new PrintWriter(new BufferedWriter(new FileWriter(logFilePath, true)));
        } catch (IOException e) {
            throw new RepositoryException("Log program state execution failed!");
        }
        Date date = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
        logFile.println("Log date: " + formatter.format(date) + "\n");
        logFile.println(programState);
        logFile.println("Log ended!\n\n\n");
        logFile.close();
    }

    @Override
    public List<ProgramState> getProgramStates() {
        return programStates;
    }

    @Override
    public void setProgramStates(List<ProgramState> newProgramList) {
        programStates = newProgramList;
    }
}
