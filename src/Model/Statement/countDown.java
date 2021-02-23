package Model.Statement;

import CustomException.CustomException;
import CustomException.TypecheckException;
import CustomException.StatementException;
import Model.AbstractDataTypes.DictionaryInterface;
import Model.AbstractDataTypes.LatchInterface;
import Model.AbstractDataTypes.ListInterface;
import Model.AbstractDataTypes.StackInterface;
import Model.ProgramState;
import Model.Type.IntegerType;
import Model.Type.Type;
import Model.Value.NumberValue;
import Model.Value.Value;

public class countDown implements Statement{
    private final String var;

    public countDown(String var) { this.var = var; }

    @Override
    public ProgramState execute(ProgramState currentState) throws CustomException {
        StackInterface<Statement> executionStack = currentState.getExecutionStack();
        DictionaryInterface<String, Value> symbolTable = currentState.getSymbolTable();
        LatchInterface<Integer> latchTable = currentState.getLatchTable();
        ListInterface<Value> outputList = currentState.getOutputList();

        int foundIndex;
        if(symbolTable.isDefined(var) && symbolTable.lookup(var).getType().equals(new IntegerType())) {
            foundIndex = ((NumberValue) symbolTable.lookup(var)).getValue();
            if (latchTable.contains(foundIndex)) {
                int val = latchTable.getvalue(foundIndex);
                if (val > 0) {
                    latchTable.setValue(foundIndex, val - 1);
                }
                outputList.add(new NumberValue(currentState.id));
                return null;
            }
            else throw new StatementException("Latch table does not contain index " + foundIndex);
        }
        else throw new StatementException(var + " is not defined or not of type int!");
    }

    @Override
    public DictionaryInterface<String, Type> typecheck(DictionaryInterface<String, Type> typeEnvironment) {
        if(typeEnvironment.isDefined(var)) {
            Type variableType = typeEnvironment.lookup(var);
            if (variableType.equals(new IntegerType())) {
                return typeEnvironment;
            } else {
                throw new TypecheckException(var + " is not an integer!");
            }
        }
        else throw new TypecheckException(var + " is not defined!");
    }

    @Override
    public String toString() {
        return "countDown(" + var + ")";
    }
}
