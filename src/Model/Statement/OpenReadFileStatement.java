package Model.Statement;

import CustomException.CustomException;
import CustomException.TypecheckException;
import Model.AbstractDataTypes.DictionaryInterface;
import Model.AbstractDataTypes.HeapInterface;
import Model.Expression.Expression;
import Model.ProgramState;
import Model.Type.StringType;
import Model.Type.Type;
import Model.Value.StringValue;
import Model.Value.Value;
import CustomException.FileException;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class OpenReadFileStatement implements Statement{
    private final Expression exp;
    public OpenReadFileStatement(Expression exp){
        this.exp = exp;
    }

    @Override
    public ProgramState execute(ProgramState currentState) throws CustomException {
        DictionaryInterface<String, Value> symbolTable = currentState.getSymbolTable();
        DictionaryInterface<StringValue, BufferedReader> fileTable = currentState.getFileTable();
        HeapInterface<Value> heapTable = currentState.getHeapTable();
        Value value = exp.evaluateExpression(symbolTable, heapTable);
        if(value.getType().equals(new StringType())){
            StringValue stringValue = (StringValue) value;
            if(fileTable.isDefined(stringValue)){
                throw new FileException("File already opened!");
            }
            else {
                try{
                    BufferedReader buffRead = new BufferedReader(new FileReader(stringValue.getValue()));
                    fileTable.add(stringValue, buffRead);
                } catch(IOException exception){
                    throw new FileException("File cannot be opened! message: " + exception.getMessage());
                }
            }
        }
        else {
            throw new FileException("Expression evaluation not StringType!");
        }
        return null;
    }

    @Override
    public DictionaryInterface<String, Type> typecheck(DictionaryInterface<String, Type> typeEnvironment) {
        Type expressionType = exp.typecheck(typeEnvironment);
        if(expressionType.equals(new StringType())){
            return typeEnvironment;
        }
        else{
            throw new TypecheckException("Expression not of type string!");
        }
    }

    @Override
    public String toString() {
        return "Open Read File '" + exp.toString() + "'";
    }
}
