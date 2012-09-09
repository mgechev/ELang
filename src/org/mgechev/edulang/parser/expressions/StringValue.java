package org.mgechev.edulang.parser.expressions;

public class StringValue extends Value<String> implements IExpression {

    public StringValue(String val) {
        super(val);
    }
    
    public Value evaluate() {
        return this;
    }
    
}
