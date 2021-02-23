package Model.Statement;

import CustomException.CustomException;
import Model.AbstractDataTypes.DictionaryInterface;
import Model.ProgramState;
import Model.Type.Type;

public class NopStatement implements Statement{
    public NopStatement() {}

    @Override
    public ProgramState execute(ProgramState currentState) throws CustomException {
        return null;
    }

    @Override
    public DictionaryInterface<String, Type> typecheck(DictionaryInterface<String, Type> typeEnvironment) {
        return typeEnvironment;
    }

    @Override
    public String toString() {
        return "nop";
    }
}
