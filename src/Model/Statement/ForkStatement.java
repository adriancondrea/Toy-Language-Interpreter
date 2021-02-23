package Model.Statement;

import CustomException.CustomException;
import Model.AbstractDataTypes.DictionaryInterface;
import Model.AbstractDataTypes.MyStack;
import Model.AbstractDataTypes.StackInterface;
import Model.ProgramState;
import Model.Type.Type;

public class ForkStatement implements Statement{
    private final Statement statement;

    public ForkStatement(Statement statement){
        this.statement = statement;
    }

    @Override
    public ProgramState execute(ProgramState currentState) throws CustomException {
        StackInterface<Statement> forkExecutionStack = new MyStack<>();
        forkExecutionStack.push(statement);
        return new ProgramState(forkExecutionStack,
                                currentState.getSymbolTable().deepCopy(),
                                currentState.getOutputList(),
                                currentState.getFileTable(),
                                currentState.getHeapTable(),
                                currentState.getLatchTable(),
                                null);
    }

    @Override
    public DictionaryInterface<String, Type> typecheck(DictionaryInterface<String, Type> typeEnvironment) {
        statement.typecheck(typeEnvironment.deepCopy());
        return typeEnvironment;
    }

    @Override
    public String toString() {
        return "fork(" + statement.toString() + ")";
    }
}
