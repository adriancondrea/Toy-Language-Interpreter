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

public class HeapAllocationStatement implements Statement{
    private final String variableName;
    private final Expression expression;

    public HeapAllocationStatement(String variableName, Expression expression){
        this.variableName = variableName;
        this.expression = expression;
    }

    @Override
    public ProgramState execute(ProgramState currentState) throws CustomException {
        DictionaryInterface<String, Value>  symbolTable = currentState.getSymbolTable();
        HeapInterface<Value> heapTable = currentState.getHeapTable();

        if(symbolTable.isDefined(variableName)) {
            Value symbolTableValue = symbolTable.lookup(variableName);
            if(symbolTableValue.getType() instanceof ReferenceType) {
                Value value = expression.evaluateExpression(symbolTable, heapTable);
                if(value.getType().equals(((ReferenceType) symbolTableValue.getType()).getInner())) {
                    int address = heapTable.allocate(value);
                    symbolTable.update(variableName, new ReferenceValue(address, value.getType()));
                }
                else {
                    throw new StatementException("Types of variable and expression are incompatible!");
                }
            }
            else {
                throw new StatementException("Variable is not of reference type!");
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
            throw new TypecheckException("HeapAllocationStatement: right hand side and left hand side have different types!");
        }
    }

    @Override
    public String toString() {
        return "new(" + variableName + ", " + expression.toString() + ")";
    }
}
