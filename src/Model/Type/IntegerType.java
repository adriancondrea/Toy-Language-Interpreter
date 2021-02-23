package Model.Type;

import Model.Value.NumberValue;
import Model.Value.Value;

public class IntegerType implements Type{
    //check if an object is of type IntegerType
    @Override
    public boolean equals(Object obj) {
        return obj instanceof IntegerType;
    }

    @Override
    public String toString() {
        return "int";
    }

    @Override
    public Value defaultValue() {
        return new NumberValue();
    }
}
