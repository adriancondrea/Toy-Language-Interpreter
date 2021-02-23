package Model.Statement;

import CustomException.CustomException;
import CustomException.StatementException;
import CustomException.TypecheckException;
import Model.AbstractDataTypes.DictionaryInterface;
import Model.AbstractDataTypes.LatchInterface;
import Model.AbstractDataTypes.StackInterface;
import Model.ProgramState;
import Model.Type.IntegerType;
import Model.Type.Type;
import Model.Value.NumberValue;
import Model.Value.Value;

public class await implements Statement{
    private final String var;

    public await(String var){ this.var = var; }

    @Override
    public ProgramState execute(ProgramState currentState) throws CustomException {
        StackInterface<Statement> executionStack = currentState.getExecutionStack();
        DictionaryInterface<String, Value> symbolTable = currentState.getSymbolTable();
        LatchInterface<Integer> latchTable = currentState.getLatchTable();

        int foundIndex;
        if(symbolTable.isDefined(var) && symbolTable.lookup(var).getType().equals(new IntegerType())) {
            foundIndex = ((NumberValue) symbolTable.lookup(var)).getValue();
            if (latchTable.contains(foundIndex)) {
                if (latchTable.getvalue(foundIndex) != 0) {
                    executionStack.push(new await(var));
                }
                return null;
            }
            else throw new StatementException("Latch table does not contain index " + foundIndex);
        }
        else throw new StatementException(var + " is not defined or does not have type int!");
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
        return "await(" + var + ")";
    }
}
