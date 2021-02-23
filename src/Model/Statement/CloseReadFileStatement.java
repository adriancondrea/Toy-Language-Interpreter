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
import CustomException.CollectionException;
import java.io.BufferedReader;
import java.io.IOException;

public class CloseReadFileStatement implements Statement{
    private final Expression expression;

    public CloseReadFileStatement(Expression expression){
        this.expression = expression;
    }
    @Override
    public ProgramState execute(ProgramState currentState) throws CustomException {
        DictionaryInterface<String, Value> symbolTable = currentState.getSymbolTable();
        DictionaryInterface<StringValue, BufferedReader> fileTable = currentState.getFileTable();
        HeapInterface<Value> heapTable = currentState.getHeapTable();
        Value expressionValue = expression.evaluateExpression(symbolTable, heapTable);
        if(expressionValue.getType().equals(new StringType())) {
            BufferedReader bufferedReader;
            try {
                bufferedReader = fileTable.lookup((StringValue) expressionValue);
            }
            catch (CollectionException e){
                throw new FileException("File " + expressionValue.toString() + " could not be closed because is not open / doesn't exist!");
            }
            try {
                bufferedReader.close();
            } catch (IOException e) {
                throw new FileException("Could not close file!");
            }
            fileTable.remove((StringValue) expressionValue);
        }
        else{
            throw new FileException("Expression evaluation not StringType!");
        }
        return null;
    }

    @Override
    public DictionaryInterface<String, Type> typecheck(DictionaryInterface<String, Type> typeEnvironment) {
        Type expressionType = expression.typecheck(typeEnvironment);
        if(expressionType.equals(new StringType())) {
            return typeEnvironment;
        }
        else{
            throw new TypecheckException("Expression not of type string!");
        }
    }

    @Override
    public String toString() {
        return "closeRFile(" + expression.toString() + ")";
    }
}
