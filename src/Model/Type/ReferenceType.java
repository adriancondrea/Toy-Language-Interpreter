package Model.Type;

import Model.Value.ReferenceValue;
import Model.Value.Value;

public class ReferenceType implements Type{
    private final Type inner;

    public ReferenceType() {
        inner = new IntegerType();
    }

    public ReferenceType(Type inner){
        this.inner = inner;
    }

    public Type getInner(){
        return inner;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof ReferenceType){
            return inner.equals(((ReferenceType) obj).getInner());
        }
        else {
            return false;
        }
    }

    @Override
    public Value defaultValue() {
        return new ReferenceValue(0, inner);
    }

    @Override
    public String toString() {
        return "Reference("+inner.toString()+")";
    }
}
