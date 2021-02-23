package Model.Value;

import Model.Type.BoolType;
import Model.Type.Type;

public class BooleanValue implements Value{
    Boolean value;
    public BooleanValue(boolean value){ this.value = value; }
    public BooleanValue() {this.value = false; }
    @Override
    public Type getType() {
        return new BoolType();
    }

    public Boolean getValue() {
        return this.value;
    }

    @Override
    public String toString() {
        return value.toString();
    }

    @Override
    public boolean equals(Object obj) {
        if(obj instanceof BooleanValue) {
            Boolean val = ((BooleanValue) obj).getValue();
            return val == this.getValue();
        }
        return false;
    }
}
