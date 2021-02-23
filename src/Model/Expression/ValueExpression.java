package Model.Expression;

import Model.AbstractDataTypes.DictionaryInterface;
import Model.AbstractDataTypes.HeapInterface;
import Model.Type.Type;
import Model.Value.Value;

public class ValueExpression implements Expression{
    private final Value result;
    public  ValueExpression(Value value){
        this.result = value;
    }

    @Override
    public Value evaluateExpression(DictionaryInterface<String, Value> symbolTable, HeapInterface<Value> heapTable) {
        return result;
    }

    @Override
    public Type typecheck(DictionaryInterface<String, Type> typeEnvironment) {
        return result.getType();
    }

    @Override
    public String toString() {
        return result.toString();
    }
}
