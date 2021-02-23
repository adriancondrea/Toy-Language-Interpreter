package Model.Statement;

import CustomException.CustomException;
import CustomException.TypecheckException;
import Model.AbstractDataTypes.DictionaryInterface;
import Model.AbstractDataTypes.StackInterface;
import Model.ProgramState;
import Model.Type.IntegerType;
import Model.Type.Type;
import Model.Value.NumberValue;
import Model.Value.Value;

public class SleepStatement implements Statement{
    private final Value number;

    public SleepStatement(Value number){
        this.number = number;
    }

    @Override
    public ProgramState execute(ProgramState currentState) throws CustomException {
        StackInterface<Statement> executionStack = currentState.getExecutionStack();
        int numberVal = ((NumberValue) number).getValue();
        if(numberVal > 0){
            executionStack.push(new SleepStatement(new NumberValue(numberVal - 1)));
        }
        return null;
    }

    @Override
    public DictionaryInterface<String, Type> typecheck(DictionaryInterface<String, Type> typeEnvironment) {
        if(number.getType().equals(new IntegerType())){
            return typeEnvironment;
        }
        else{
            throw new TypecheckException("Number is not an integer!");
        }
    }

    @Override
    public String toString() {
        return "Sleep(" + number.toString() + ")";
    }
}
