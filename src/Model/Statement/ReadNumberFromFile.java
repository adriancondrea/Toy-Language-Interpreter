package Model.Statement;

import CustomException.CustomException;
import CustomException.TypecheckException;
import CustomException.FileException;
import CustomException.ExpressionException;
import CustomException.StatementException;
import Model.AbstractDataTypes.DictionaryInterface;
import Model.Expression.Expression;
import Model.ProgramState;
import Model.Type.IntegerType;
import Model.Type.StringType;
import Model.Type.Type;
import Model.Value.NumberValue;
import Model.Value.StringValue;
import Model.Value.Value;

import java.io.BufferedReader;
import java.io.IOException;

public class ReadNumberFromFile implements Statement{
    private final Expression expression;
    private final String variableName;

    public ReadNumberFromFile(Expression expression, String variableName){
        this.expression = expression;
        this.variableName = variableName;
    }
    @Override
    public ProgramState execute(ProgramState currentState) throws CustomException {
        DictionaryInterface<String, Value> symbolTable = currentState.getSymbolTable();
        DictionaryInterface<StringValue, BufferedReader> fileTable = currentState.getFileTable();
        if(symbolTable.isDefined(variableName)){
            Value value = symbolTable.lookup(variableName);
            if(value.getType().equals(new IntegerType())){
                Value expressionValue = expression.evaluateExpression(symbolTable, currentState.getHeapTable());
                if(expressionValue.getType().equals(new StringType())){
                    StringValue stringValue = (StringValue) expressionValue;
                    BufferedReader reader = fileTable.lookup(stringValue);
                    try{
                        String line = reader.readLine();
                        NumberValue variableValue;
                        if(line == null){
                            variableValue = new NumberValue();
                        }
                        else{
                            variableValue = new NumberValue(Integer.parseInt(line));
                        }
                        symbolTable.update(variableName, variableValue);
                    } catch (IOException e) {
                        throw new FileException("Error reading from file!");
                    }
                }
                else{
                    throw new ExpressionException("Expression not of type string!");
                }
            }
            else{
                throw new StatementException("Value type is not int!");
            }
        }
        else{
            throw new StatementException("Variable name not defined!");
        }
        return null;
    }

    @Override
    public DictionaryInterface<String, Type> typecheck(DictionaryInterface<String, Type> typeEnvironment) {
        Type variableType = typeEnvironment.lookup(variableName);
        Type expressionType = expression.typecheck(typeEnvironment);
        if(variableType.equals(new IntegerType())){
            if(expressionType.equals(new StringType())){
                return typeEnvironment;
            }
            throw new TypecheckException("expression not of integer type!");
        }
        else {
            throw new TypecheckException("variable not of string type!");
        }
    }

    @Override
    public String toString() {
        return "readFile(" + expression.toString() + ", " + variableName +")";
    }
}
