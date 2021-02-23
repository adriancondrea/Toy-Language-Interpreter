package Model.Statement;

import CustomException.CustomException;
import Model.AbstractDataTypes.DictionaryInterface;
import Model.AbstractDataTypes.MyDictionary;
import Model.ProgramState;
import Model.Type.Type;

public interface Statement {
    ProgramState execute(ProgramState currentState) throws CustomException;
    DictionaryInterface<String, Type> typecheck(DictionaryInterface<String, Type> typeEnvironment);
}
