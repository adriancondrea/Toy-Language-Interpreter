package Model.Statement;

import CustomException.CustomException;
import Model.AbstractDataTypes.DictionaryInterface;
import Model.AbstractDataTypes.ListInterface;
import Model.Expression.Expression;
import Model.ProgramState;
import Model.Type.Type;
import Model.Value.Value;

public class PrintStatement implements Statement{
    private final Expression expression;

    public PrintStatement(Expression expression){
        this.expression = expression;
    }

    @Override
    public ProgramState execute(ProgramState currentState) throws CustomException {
        ListInterface<Value> outputList = currentState.getOutputList();
        outputList.add(expression.evaluateExpression(currentState.getSymbolTable(), currentState.getHeapTable()));
        return null;
    }

    @Override
    public DictionaryInterface<String, Type> typecheck(DictionaryInterface<String, Type> typeEnvironment) {
        expression.typecheck(typeEnvironment);
        return typeEnvironment;
    }

    @Override
    public String toString() {
        return "print(" + expression.toString() + ")";
    }
}
