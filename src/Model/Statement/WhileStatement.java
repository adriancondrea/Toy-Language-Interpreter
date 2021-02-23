package Model.Statement;

import CustomException.CustomException;
import CustomException.TypecheckException;
import CustomException.StatementException;
import Model.AbstractDataTypes.DictionaryInterface;
import Model.AbstractDataTypes.HeapInterface;
import Model.AbstractDataTypes.StackInterface;
import Model.Expression.Expression;
import Model.ProgramState;
import Model.Type.BoolType;
import Model.Type.Type;
import Model.Value.BooleanValue;
import Model.Value.Value;

public class WhileStatement implements Statement {
    private final Expression expression;
    private final Statement statement;

    public WhileStatement(Expression expression, Statement statement) {
        this.expression = expression;
        this.statement = statement;
    }

    @Override
    public ProgramState execute(ProgramState currentState) throws CustomException {
        StackInterface<Statement> executionStack = currentState.getExecutionStack();
        DictionaryInterface<String, Value> symbolTable = currentState.getSymbolTable();
        HeapInterface<Value> heapTable = currentState.getHeapTable();
        Value expressionValue = expression.evaluateExpression(symbolTable, heapTable);
        if(expressionValue.getType().equals(new BoolType())){
            if(expressionValue.equals(new BooleanValue(true))){
                executionStack.push(new WhileStatement(expression, statement));
                executionStack.push(statement);
            }
        }
        else {
            throw new StatementException("Expression of while does not evaluate to a Boolean Value!");
        }
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
            throw new TypecheckException("While statement: condition not of bool type!");
        }
    }

    @Override
    public String toString() {
        return "(WHILE(" + expression.toString() + ") " + statement.toString() + ")";
    }
}
