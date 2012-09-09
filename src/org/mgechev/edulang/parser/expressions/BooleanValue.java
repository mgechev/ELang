package org.mgechev.edulang.parser.expressions;

public class BooleanValue extends Value<Boolean> implements IExpression {

    public BooleanValue(Boolean val) {
        super(val);
    }
    
    public Value evaluate() {
        return this;
    }
    
}
