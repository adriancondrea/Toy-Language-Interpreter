package Model.Statement;

import CustomException.CustomException;
import CustomException.TypecheckException;
import CustomException.StatementException;
import Model.AbstractDataTypes.DictionaryInterface;
import Model.AbstractDataTypes.StackInterface;
import Model.Expression.Expression;
import Model.ProgramState;
import Model.Type.BoolType;
import Model.Type.Type;
import Model.Value.BooleanValue;
import Model.Value.Value;

public class ConditionalStatement implements Statement{
    private final Expression condition;
    private final Statement trueBranch, falseBranch;

    public ConditionalStatement(Expression condition, Statement trueBranch, Statement falseBranch){
        this.condition = condition;
        this.trueBranch = trueBranch;
        this.falseBranch = falseBranch;
    }

    @Override
    public ProgramState execute(ProgramState currentState) throws CustomException {
        StackInterface<Statement> executionStack = currentState.getExecutionStack();
        Value expressionValue = condition.evaluateExpression(currentState.getSymbolTable(), currentState.getHeapTable());
        if(!expressionValue.getType().equals(new BoolType()))
            throw new StatementException("Conditional expression is not a boolean!\n");
        else {
            if (expressionValue.equals(new BooleanValue(true)))
                executionStack.push(trueBranch);
            else
                executionStack.push(falseBranch);
            return null;
        }
    }

    @Override
    public DictionaryInterface<String, Type> typecheck(DictionaryInterface<String, Type> typeEnvironment) {
        Type expressionType = condition.typecheck(typeEnvironment);
        if(expressionType.equals(new BoolType())) {
            trueBranch.typecheck(typeEnvironment.deepCopy());
            falseBranch.typecheck(typeEnvironment.deepCopy());
            return typeEnvironment;
        }
        else {
            throw new TypecheckException("ConditionalStatement: The condition is not of bool type!");
        }
    }

    @Override
    public String toString() {
        return "(IF(" + condition.toString() + ") THEN(" + trueBranch.toString() +")ELSE (" + falseBranch.toString() + "))";
    }
}
