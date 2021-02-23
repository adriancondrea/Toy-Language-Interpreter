package Model.Expression;

import CustomException.TypecheckException;
import CustomException.ExpressionException;
import Model.AbstractDataTypes.DictionaryInterface;
import Model.AbstractDataTypes.HeapInterface;
import Model.Type.IntegerType;
import Model.Type.Type;
import Model.Value.NumberValue;
import Model.Value.Value;

public class ArithmeticExpression implements Expression{
    private final Expression leftExpression;
    private final Expression rightExpression;
    private final int operation;
    /* operations legend:
        1 = *
        2 = /
        3 = +
        4 = -
        -1 = invalid operation
     */

    public ArithmeticExpression(char operation, Expression leftExpression, Expression rightExpression){
        this.leftExpression = leftExpression;
        this.rightExpression = rightExpression;
        switch (operation) {
            case '*' -> this.operation = 1;
            case '/' -> this.operation = 2;
            case '+' -> this.operation = 3;
            case '-' -> this.operation = 4;
            default -> this.operation = -1;
        }
    }

    @Override
    public String toString() {
        if(operation == -1)
            return leftExpression.toString() + " unknown operation " + rightExpression.toString();
        char[] operationsArray = {'n','*','/','+','-'};
        return leftExpression.toString() + ' ' + operationsArray[operation] + ' ' + rightExpression.toString();
    }

    @Override
    public Value evaluateExpression(DictionaryInterface<String, Value> symbolTable, HeapInterface<Value> heapTable) {
        Value leftValue, rightValue;
        leftValue = leftExpression.evaluateExpression(symbolTable, heapTable);
        if(leftValue.getType().equals(new IntegerType())){
            rightValue = rightExpression.evaluateExpression(symbolTable, heapTable);
            if(rightValue.getType().equals(new IntegerType())){
                NumberValue val1 = (NumberValue) leftValue;
                NumberValue val2 = (NumberValue) rightValue;
                int leftNumber, rightNumber;
                leftNumber =val1.getValue();
                rightNumber = val2.getValue();
                switch (operation) {
                    case 1:
                        return new NumberValue(leftNumber * rightNumber);
                    case 2:
                        if (rightNumber == 0)
                            throw new ExpressionException("Division by zero!\n");
                        return new NumberValue(leftNumber / rightNumber);
                    case 3:
                        return new NumberValue(leftNumber + rightNumber);
                    case 4:
                        return new NumberValue(leftNumber - rightNumber);
                    default:
                        throw new ExpressionException("unknown operation!\n");
                }
            }
            else
                throw new ExpressionException("Operand2 is not an integer!\n");
        }
        else
            throw new ExpressionException("Operand1 is not an integer!\n");
    }

    @Override
    public Type typecheck(DictionaryInterface<String, Type> typeEnvironment) {
        Type leftType, rightType;
        leftType = leftExpression.typecheck(typeEnvironment);
        rightType = rightExpression.typecheck(typeEnvironment);
        if (leftType.equals(new IntegerType())){
            if(rightType.equals(new IntegerType())){
                return new IntegerType();
            }
            else throw new TypecheckException("Right operand is not an integer!");
        }
        else throw new TypecheckException("Left operand is not an integer!");
    }
}
