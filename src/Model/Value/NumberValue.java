package Model.Value;

import Model.Type.IntegerType;
import Model.Type.Type;

public class NumberValue implements Value{
    private final int value;

    public NumberValue(int value){
        this.value = value;
    }

    //default constructor
    public NumberValue(){
        value = 0;
    }

    public int getValue(){
        return value;
    }

    @Override
    public Type getType() {
        return new IntegerType();
    }

    @Override
    public String toString() {
        return String.valueOf(value);
    }

    @Override
    public boolean equals(Object obj) {
        if(obj instanceof NumberValue){
            int objValue = ((NumberValue) obj).getValue();
            return objValue == this.getValue();
        }
        return false;
    }
}
