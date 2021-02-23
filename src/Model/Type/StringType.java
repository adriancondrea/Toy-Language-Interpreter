package Model.Type;

import Model.Value.Value;
import Model.Value.StringValue;

public class StringType implements Type{
    @Override
    public boolean equals(Object obj) {
        return obj instanceof StringType;
    }

    @Override
    public String toString() {
        return "string";
    }

    @Override
    public Value defaultValue() {
        return new StringValue();
    }
}
