package Model.Statement;

import CustomException.CustomException;
import CustomException.StatementException;
import Model.AbstractDataTypes.DictionaryInterface;
import Model.ProgramState;
import Model.Type.Type;
import Model.Value.Value;

public class VariableDeclarationStatement implements  Statement{
    private final Type type;
    private final String variableName;
    public VariableDeclarationStatement(String id, Type t){ type = t; variableName = id; }

    @Override
    public ProgramState execute(ProgramState currentState) throws CustomException {
        DictionaryInterface<String, Value> symbolTable = currentState.getSymbolTable();
        if(symbolTable.isDefined(variableName))
            throw new StatementException("Variable is already declared!\n");
        else
            symbolTable.add(variableName, type.defaultValue());
        return null;
    }

    @Override
    public DictionaryInterface<String, Type> typecheck(DictionaryInterface<String, Type> typeEnvironment) {
        typeEnvironment.add(variableName, type);
        return typeEnvironment;
    }

    @Override
    public String toString() {
        return type.toString() + " " + variableName;
    }
}
