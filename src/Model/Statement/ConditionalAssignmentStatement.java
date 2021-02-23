package Model.Statement;

import CustomException.CustomException;
import CustomException.TypecheckException;
import CustomException.StatementException;
import Model.AbstractDataTypes.DictionaryInterface;
import Model.AbstractDataTypes.HeapInterface;
import Model.Expression.Expression;
import Model.ProgramState;
import Model.Type.BoolType;
import Model.Type.Type;
import Model.Value.BooleanValue;
import Model.Value.Value;

public class ConditionalAssignmentStatement implements Statement{
    private final String id;
    private final Expression conditionalExpression, trueExpression, falseExpression;

    public ConditionalAssignmentStatement(String id, Expression conditionalExpression, Expression trueExpression, Expression falseExpression){
        this.id = id;
        this.conditionalExpression = conditionalExpression;
        this.trueExpression = trueExpression;
        this.falseExpression = falseExpression;
    }

    @Override
    public ProgramState execute(ProgramState currentState) throws CustomException {
        DictionaryInterface<String, Value> symbolTable = currentState.getSymbolTable();
        HeapInterface<Value> heapTable = currentState.getHeapTable();
        if(symbolTable.isDefined(id)){
            Value conditionalExpressionValue = conditionalExpression.evaluateExpression(symbolTable, heapTable);
            if(conditionalExpressionValue.equals(new BooleanValue(true))){
                Value expressionValue = trueExpression.evaluateExpression(symbolTable, heapTable);
                symbolTable.update(id, expressionValue);
            }
            else{
                Value expressionValue = falseExpression.evaluateExpression(symbolTable, heapTable);
                symbolTable.update(id, expressionValue);
            }
        }
        else throw new StatementException("Variable Id is not declared!\n");
        return null;
    }

    @Override
    public DictionaryInterface<String, Type> typecheck(DictionaryInterface<String, Type> typeEnvironment) {
        Type conditionalExpressionType = conditionalExpression.typecheck(typeEnvironment);
        if(conditionalExpressionType.equals(new BoolType())) {
            Type variableType = typeEnvironment.lookup(id);
            Type trueExpressionType = trueExpression.typecheck(typeEnvironment);
            if (variableType.equals(trueExpressionType)) {
                Type falseExpressionType = falseExpression.typecheck(typeEnvironment);
                if (variableType.equals(falseExpressionType)) {
                    return typeEnvironment;
                }
                else throw new TypecheckException("ConditionalAssignment: False branch expression value does not have the variable type!");
            }
            else throw new TypecheckException("ConditionalAssignment: True branch expression value does not have the variable type!");
        }
        else throw new TypecheckException("ConditionalAssignment: Conditional expression is not of Bool type!");
        }

    @Override
    public String toString() {
        return id + "= (" + conditionalExpression.toString() + ") ? (" + trueExpression.toString() + ") : (" + falseExpression.toString() + ")";
    }
}
