package Repository;

import Model.ProgramState;
import java.util.List;

public interface Repository {
    void addState(ProgramState newState);
    void logProgramStateExecution(ProgramState programState);
    List<ProgramState> getProgramStates();
    void setProgramStates(List<ProgramState> newProgramList);
}
