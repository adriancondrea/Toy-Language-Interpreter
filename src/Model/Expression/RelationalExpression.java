package Model.Expression;

import CustomException.ExpressionException;
import CustomException.TypecheckException;
import Model.AbstractDataTypes.DictionaryInterface;
import Model.AbstractDataTypes.HeapInterface;
import Model.Type.BoolType;
import Model.Type.IntegerType;
import Model.Type.Type;
import Model.Value.BooleanValue;
import Model.Value.NumberValue;
import Model.Value.Value;

public class RelationalExpression implements Expression{
    private final Expression leftExpression, rightExpression;
    private final int relation;
    public RelationalExpression(String relationString, Expression left, Expression right){
        this.leftExpression = left;
        this.rightExpression = right;
        switch(relationString){
            case "<" ->     relation = 1;
            case "<=" ->    relation = 2;
            case "==" ->    relation = 3;
            case "!=" ->    relation = 4;
            case ">" ->     relation = 5;
            case ">=" ->    relation = 6;
            default ->      relation = -1;
        }
    }
    @Override
    public Value evaluateExpression(DictionaryInterface<String, Value> symbolTable, HeapInterface<Value> heapTable) {
        Value leftValue, rightValue;
        leftValue = leftExpression.evaluateExpression(symbolTable, heapTable);
        if(leftValue.getType().equals(new IntegerType())){
            rightValue = rightExpression.evaluateExpression(symbolTable, heapTable);
            if(rightValue.getType().equals(new IntegerType())){
                int leftNumber, rightNumber;
                leftNumber = ((NumberValue) leftValue).getValue();
                rightNumber =((NumberValue) rightValue).getValue();
                return switch (relation) {
                    case 1 -> new BooleanValue(leftNumber < rightNumber);
                    case 2 -> new BooleanValue(leftNumber <= rightNumber);
                    case 3 -> new BooleanValue(leftNumber == rightNumber);
                    case 4 -> new BooleanValue(leftNumber != rightNumber);
                    case 5 -> new BooleanValue(leftNumber > rightNumber);
                    case 6 -> new BooleanValue(leftNumber >= rightNumber);
                    default -> throw new ExpressionException("unknown comparison relation!");
                };
            }
            else{
                throw new ExpressionException("Right side of relational expression not integer!");
            }
        }
        else {
            throw new ExpressionException("Left side of relational expression not integer!");
        }
    }

    @Override
    public Type typecheck(DictionaryInterface<String, Type> typeEnvironment) {
        Type leftType, rightType;
        leftType = leftExpression.typecheck(typeEnvironment);
        rightType = rightExpression.typecheck(typeEnvironment);
        if (leftType.equals(new IntegerType())){
            if(rightType.equals(new IntegerType())){
                return new BoolType();
            }
            else throw new TypecheckException("Right operand is not an integer!");
        }
        else throw new TypecheckException("Left operand is not an integer!");
    }

    @Override
    public String toString() {
        String[] relations = {"","<", "<=", "==", "!=", ">", ">="};
        return leftExpression.toString() + ' ' + relations[relation] + ' ' + rightExpression.toString();
    }
}
