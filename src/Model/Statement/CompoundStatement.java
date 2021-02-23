package Model.Statement;

import CustomException.CustomException;
import Model.AbstractDataTypes.DictionaryInterface;
import Model.AbstractDataTypes.StackInterface;
import Model.ProgramState;
import Model.Type.Type;

public class CompoundStatement implements Statement{
    private final Statement first, second;

    public CompoundStatement(Statement first, Statement second){
        this.first = first;
        this.second = second;
    }

    @Override
    public ProgramState execute(ProgramState currentState) throws CustomException {
        StackInterface<Statement> executionStack = currentState.getExecutionStack();
        executionStack.push(second);
        executionStack.push(first);
        return null;
    }

    @Override
    public DictionaryInterface<String, Type> typecheck(DictionaryInterface<String, Type> typeEnvironment) {
        //DictionaryInterface<String, Type> typeEnvironment1 = first.typecheck(typeEnvironment);
        //DictionaryInterface<String, Type> typeEnvironment2 = second.typecheck(typeEnvironment1);
        //return typeEnvironment2;
        return second.typecheck(first.typecheck(typeEnvironment));
    }

    @Override
    public String toString(){
        //return the compound statement in the form (first;second)
        //return "("+first.toString()+";"+second.toString()+")";
        return first.toString() + "; " + second.toString();
    }
}
