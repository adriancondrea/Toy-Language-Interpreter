package Model.Statement;

import CustomException.CustomException;
import CustomException.TypecheckException;
import Model.AbstractDataTypes.DictionaryInterface;
import Model.AbstractDataTypes.HeapInterface;
import Model.Expression.Expression;
import Model.ProgramState;
import Model.Type.ReferenceType;
import Model.Type.Type;
import Model.Value.ReferenceValue;
import Model.Value.Value;
import CustomException.StatementException;

public class WriteHeapStatement implements Statement{
    private final String variableName;
    private final Expression expression;

    public WriteHeapStatement(String variableName, Expression expression) {
        this.variableName = variableName;
        this.expression = expression;
    }

    @Override
    public ProgramState execute(ProgramState currentState) throws CustomException {
        DictionaryInterface<String, Value> symbolTable = currentState.getSymbolTable();
        HeapInterface<Value> heapTable = currentState.getHeapTable();
        if(symbolTable.isDefined(variableName)){
            Value value = symbolTable.lookup(variableName);
            if(value instanceof ReferenceValue) {
                int referenceAddress = ((ReferenceValue) value).getAddress();
                if(heapTable.allocatedAddress(referenceAddress)) {
                    Value expressionValue = expression.evaluateExpression(symbolTable, heapTable);
                    if(expressionValue.getType().equals(heapTable.getValue(referenceAddress).getType())) {
                        heapTable.putValueAtAddress(referenceAddress, expressionValue);
                    }
                    else {
                        throw new StatementException("Expression not of " + variableName + " type!");
                    }
                }
                else {
                    throw new StatementException("Address doesn't exist on heap!");
                }
            }
            else {
                throw new StatementException("Variable not of reference type!");
            }
        }
        else {
            throw new StatementException("Variable name not defined!");
        }
        return null;
    }

    @Override
    public DictionaryInterface<String, Type> typecheck(DictionaryInterface<String, Type> typeEnvironment) {
        Type variableType = typeEnvironment.lookup(variableName);
        Type expressionType = expression.typecheck(typeEnvironment);
        if(variableType.equals(new ReferenceType(expressionType))){
            return typeEnvironment;
        }
        else{
            throw new TypecheckException("WriteHeapStatement: right hand side and left hand side have different types!");
        }
    }

    @Override
    public String toString() {
        return "writeHeap(" + variableName + ", " + expression.toString() + ")";
    }
}
