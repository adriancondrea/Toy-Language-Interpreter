package Model.Statement;

import CustomException.CustomException;
import CustomException.TypecheckException;
import Model.AbstractDataTypes.DictionaryInterface;
import Model.AbstractDataTypes.StackInterface;
import Model.Expression.Expression;
import Model.Expression.RelationalExpression;
import Model.Expression.VariableNameExpression;
import Model.ProgramState;
import Model.Type.IntegerType;
import Model.Type.Type;
import Model.Value.Value;

public class ForStatement implements Statement{
    String id;
    Expression expression1, expression2, expression3;
    Statement statement;

    public ForStatement(String id, Expression expr1, Expression expr2, Expression expr3, Statement statement){
        this.id = id;
        this.expression1 = expr1;
        this.expression2 = expr2;
        this.expression3 = expr3;
        this.statement = statement;
    }

    @Override
    public ProgramState execute(ProgramState currentState) throws CustomException {
        StackInterface<Statement> executionStack = currentState.getExecutionStack();
        DictionaryInterface<String, Value> symbolTable = currentState.getSymbolTable();
        if(symbolTable.isDefined(id)){
            executionStack.push(new CompoundStatement(new AssignmentStatement(id, expression1),  new WhileStatement(new RelationalExpression("<", new VariableNameExpression(id), expression2), new CompoundStatement(statement, new AssignmentStatement(id, expression3)))));
        }
        else {
            executionStack.push(new CompoundStatement(new CompoundStatement(new VariableDeclarationStatement(id, new IntegerType()), new AssignmentStatement(id, expression1)), new WhileStatement(new RelationalExpression("<", new VariableNameExpression(id), expression2), new CompoundStatement(statement, new AssignmentStatement(id, expression3)))));
        }
        return null;
    }

    @Override
    public DictionaryInterface<String, Type> typecheck(DictionaryInterface<String, Type> typeEnvironment) {
        /*v, exp1, exp2, and exp3 have
the type int.*/
        boolean definedVariable = typeEnvironment.isDefined(id);
        if(definedVariable) {
            Type variableType = typeEnvironment.lookup(id);
            if(!variableType.equals(new IntegerType())) {
                throw new TypecheckException("variable " + id + " is defined but does not have integer type!");
            }
            }
        else{
            typeEnvironment.add(id, new IntegerType());
        }
        Type expression1Type = expression1.typecheck(typeEnvironment);
        if (expression1Type.equals(new IntegerType())) {
            Type expression2Type = expression2.typecheck(typeEnvironment);
            if (expression2Type.equals(new IntegerType())) {
                Type expression3Type = expression3.typecheck(typeEnvironment);
                if (expression3Type.equals(new IntegerType())) {
                    return typeEnvironment;
                } else throw new TypecheckException("expression3 value not of type int!");
            } else throw new TypecheckException("expression2 value not of type int!");
        } else throw new TypecheckException("expression3 value not of type int!");
        }

    @Override
    public String toString() {
        return "for( " + id + " = " + expression1.toString() + "; " + id + " < " + expression2.toString() + "; " + id + " = " + expression3.toString() + ") {" + statement.toString() + "}";
    }
}
