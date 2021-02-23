package Model.Statement;

import CustomException.CustomException;
import CustomException.TypecheckException;
import Model.AbstractDataTypes.DictionaryInterface;
import Model.AbstractDataTypes.StackInterface;
import Model.Expression.Expression;
import Model.ProgramState;
import Model.Type.Type;
import Model.Value.Value;

public class SwitchStatement implements Statement{
    private final Expression expression, expression1, expression2;
    private final Statement firstCase, secondCase, defaultCase;

   public SwitchStatement(Expression expression, Expression expression1, Statement firstCase, Expression expression2, Statement secondCase,
                          Statement defaultCase){
       this.expression = expression;
       this.expression1 = expression1;
       this.firstCase = firstCase;
       this.expression2 = expression2;
       this.secondCase = secondCase;
       this.defaultCase = defaultCase;
   }

    @Override
    public ProgramState execute(ProgramState currentState) throws CustomException {
        StackInterface<Statement> executionStack = currentState.getExecutionStack();
        Value expressionValue = expression.evaluateExpression(currentState.getSymbolTable(), currentState.getHeapTable());
        Value expression1Value = expression1.evaluateExpression(currentState.getSymbolTable(), currentState.getHeapTable());
        if(expressionValue.equals(expression1Value))
            executionStack.push(firstCase);
        else {
            Value expression2Value = expression2.evaluateExpression(currentState.getSymbolTable(), currentState.getHeapTable());
            if(expressionValue.equals(expression2Value))
                executionStack.push(secondCase);
            else
                executionStack.push(defaultCase);
        }
        return null;
    }

    @Override
    public DictionaryInterface<String, Type> typecheck(DictionaryInterface<String, Type> typeEnvironment) {
        Type expressionType = expression.typecheck(typeEnvironment);
        Type expression1Type = expression1.typecheck(typeEnvironment);
        Type expression2Type = expression2.typecheck(typeEnvironment);
        if(expressionType.equals(expression1Type) && expressionType.equals(expression2Type)){
            firstCase.typecheck(typeEnvironment.deepCopy());
            secondCase.typecheck(typeEnvironment.deepCopy());
            defaultCase.typecheck(typeEnvironment.deepCopy());
            return typeEnvironment;
        }
        else {
            throw new TypecheckException("Switch Statement: The types of exp, exp1 and exp2 are not the same!");
        }
    }

    @Override
    public String toString() {
        return "Switch(" + expression.toString() + ") (case (" + expression1.toString() + "): {" + firstCase.toString() + "}) (case (" +
                expression2.toString() + "): {" + secondCase.toString() + "}) (default: {" + defaultCase.toString() + "})";
    }
}
