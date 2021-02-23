package Model.Statement;

import CustomException.CustomException;
import CustomException.TypecheckException;
import CustomException.StatementException;
import Model.AbstractDataTypes.DictionaryInterface;
import Model.AbstractDataTypes.HeapInterface;
import Model.AbstractDataTypes.LatchInterface;
import Model.Expression.Expression;
import Model.ProgramState;
import Model.Type.IntegerType;
import Model.Type.Type;
import Model.Value.NumberValue;
import Model.Value.Value;

public class newLatch implements Statement{
    private final String var;
    private final Expression exp;

    public newLatch(String var, Expression exp){ this.var = var; this.exp = exp; }

    @Override
    public ProgramState execute(ProgramState currentState) throws CustomException {
        DictionaryInterface<String, Value> symbolTable = currentState.getSymbolTable();
        HeapInterface<Value> heapTable = currentState.getHeapTable();
        LatchInterface<Integer> latchTable = currentState.getLatchTable();

        Value expressionValue = exp.evaluateExpression(symbolTable, heapTable);
        if(expressionValue.getType().equals(new IntegerType())) {
            NumberValue numberValue = (NumberValue) expressionValue;
            int num1 = numberValue.getValue();
            int newfreelocation = latchTable.allocate(num1);

            if(symbolTable.isDefined(var) && symbolTable.lookup(var).getType().equals(new IntegerType())) {
                symbolTable.update(var, new NumberValue(newfreelocation));
                return null;
            }
            else{
                throw new StatementException(var + " is not of type int!");
            }
        }
        else{
            throw new StatementException(expressionValue.toString() + " is not an integer!");
        }
    }

    @Override
    public DictionaryInterface<String, Type> typecheck(DictionaryInterface<String, Type> typeEnvironment) {
        Type expressionType = exp.typecheck(typeEnvironment);
        if(expressionType.equals(new IntegerType())) {
            if (typeEnvironment.isDefined(var)) {
                Type variableType = typeEnvironment.lookup(var);
                if (variableType.equals(new IntegerType())) {
                    return typeEnvironment;
                } else {
                    throw new TypecheckException(var + " is not an integer!");
                }
            } else throw new TypecheckException(var + " is not defined!");
        }
        else {
            throw new TypecheckException(exp.toString() + "is not evaluated to an integer!");
        }
    }

    @Override
    public String toString() {
        return "newLatch(" + var + ", " + exp.toString() + ")";
    }
}
