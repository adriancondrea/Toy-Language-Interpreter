package Model.Statement;

import CustomException.CustomException;
import CustomException.TypecheckException;
import Model.AbstractDataTypes.DictionaryInterface;
import Model.AbstractDataTypes.StackInterface;
import Model.Expression.Expression;
import Model.Expression.LogicExpression;
import Model.ProgramState;
import Model.Type.BoolType;
import Model.Type.Type;

/*!README: in order to implement this RepeatUntilStatement, I had to update LogicExpression so that it supports
the negation unary operator !. This implies multiple changes in the constructor, execute, typecheck and toString
methods. Proceed with caution. If anything doesn't work, refer to the previous structure of LogicExpression.
 */

public class RepeatUntilStatement implements Statement{
    private final Statement statement;
    private final Expression expression;

    public RepeatUntilStatement(Statement statement, Expression expression){
        this.statement = statement;
        this.expression = expression;
    }

    @Override
    public ProgramState execute(ProgramState currentState) throws CustomException {
        StackInterface<Statement> executionStack = currentState.getExecutionStack();
        executionStack.push(new CompoundStatement(statement, new WhileStatement(new LogicExpression('!', expression), statement)));
        return null;
    }

    @Override
    public DictionaryInterface<String, Type> typecheck(DictionaryInterface<String, Type> typeEnvironment) {
        Type expressionType = expression.typecheck(typeEnvironment);
        if(expressionType.equals(new BoolType())){
            statement.typecheck(typeEnvironment.deepCopy());
            return typeEnvironment;
        }
        else{
            throw new TypecheckException("Repeat until statement: condition not of bool type!");
        }
    }

    @Override
    public String toString() {
        return "repeat {" + statement.toString() + "} until (" + expression.toString() + ")";
    }
}
