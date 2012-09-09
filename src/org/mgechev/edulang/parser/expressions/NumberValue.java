package org.mgechev.edulang.parser.expressions;

public class NumberValue extends Value<Double> implements IExpression {

    
    public NumberValue(Double val) {
        super(val);
    }

    public Value evaluate() {
        return this;
    }
    
}
