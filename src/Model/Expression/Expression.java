package Model.Expression;

import CustomException.CustomException;
import Model.AbstractDataTypes.DictionaryInterface;
import Model.AbstractDataTypes.HeapInterface;
import Model.Type.Type;
import Model.Value.Value;

public interface Expression {
    Value evaluateExpression(DictionaryInterface<String, Value> symbolTable, HeapInterface<Value> heapTable) throws CustomException;
    Type typecheck(DictionaryInterface<String, Type> typeEnvironment);
}
