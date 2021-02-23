package Model.Statement;

import CustomException.CustomException;
import CustomException.TypecheckException;
import CustomException.StatementException;
import Model.AbstractDataTypes.DictionaryInterface;
import Model.AbstractDataTypes.HeapInterface;
import Model.Expression.Expression;
import Model.ProgramState;
import Model.Type.Type;
import Model.Value.Value;

public class AssignmentStatement implements Statement{
    private final String id;
    private final Expression expression;

    public AssignmentStatement(String id, Expression expression){
        this.id = id;
        this.expression = expression;
    }

    @Override
    public ProgramState execute(ProgramState currentState) throws CustomException {
        DictionaryInterface<String, Value> symbolTable = currentState.getSymbolTable();
        HeapInterface<Value> heapTable = currentState.getHeapTable();
        if(symbolTable.isDefined(id)){
            Value expressionValue = expression.evaluateExpression(symbolTable, heapTable);
            if(expressionValue.getType().equals(symbolTable.lookup(id).getType()))
                symbolTable.update(id, expressionValue);
            else throw new StatementException("Type of expression and type of variable do not match!\n");
        }
        else throw new StatementException("Variable Id is not declared!\n");
        return null;
    }

    @Override
    public DictionaryInterface<String, Type> typecheck(DictionaryInterface<String, Type> typeEnvironment) {
        Type variableType = typeEnvironment.lookup(id);
        Type expressionType = expression.typecheck(typeEnvironment);
        if(variableType.equals(expressionType)){
            return typeEnvironment;
        }
        else{
            throw new TypecheckException("Asssignment: right hand side and left hand side have different types!");
        }
    }

    @Override
    public String toString() {
        return id + " = " + expression.toString();
    }
}
