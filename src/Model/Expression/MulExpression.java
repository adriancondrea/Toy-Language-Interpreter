package Model.Expression;

import CustomException.CustomException;
import CustomException.TypecheckException;
import Model.AbstractDataTypes.DictionaryInterface;
import Model.AbstractDataTypes.HeapInterface;
import Model.Type.IntegerType;
import Model.Type.Type;
import Model.Value.NumberValue;
import Model.Value.Value;

public class MulExpression implements Expression{
    private final Expression expression1, expression2;

    public MulExpression(Expression expression1, Expression expression2){
        this.expression1 = expression1;
        this.expression2 = expression2;
    }

    @Override
    public Value evaluateExpression(DictionaryInterface<String, Value> symbolTable, HeapInterface<Value> heapTable) throws CustomException {
        Value expression1Value, expression2Value;
        expression1Value = expression1.evaluateExpression(symbolTable, heapTable);
        expression2Value = expression2.evaluateExpression(symbolTable, heapTable);
        int value1 = ((NumberValue) expression1Value).getValue();
        int value2 = ((NumberValue)expression2Value).getValue();
        return new NumberValue((value1 * value2) - (value1 + value2));
    }

    @Override
    public Type typecheck(DictionaryInterface<String, Type> typeEnvironment) {
        Type expression1Type, expression2Type;
        expression1Type = expression1.typecheck(typeEnvironment);
        if(expression1Type.equals(new IntegerType())){
            expression2Type = expression2.typecheck(typeEnvironment);
            if(expression2Type.equals(new IntegerType())){
                return new IntegerType();
            }
            else throw new TypecheckException("MulExpression: Expression2 not of Integer type!");
        }
        else throw new TypecheckException("MulExpression: Expression1 not of Integer type!");
    }

    @Override
    public String toString() {
        return "MUL(" + expression1.toString() + ", " + expression2.toString() + ")";
    }
}
