package Model.Type;

import Model.Value.BooleanValue;
import Model.Value.Value;

public class BoolType implements Type{
    //check if an object is of type BoolType
    @Override
    public boolean equals(Object obj) {
        return obj instanceof BoolType;
    }

    @Override
    public String toString() {
        return "bool";
    }

    @Override
    public Value defaultValue() {
        return new BooleanValue();
    }
}
