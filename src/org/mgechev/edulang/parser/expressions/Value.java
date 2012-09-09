package org.mgechev.edulang.parser.expressions;

public class Value<T> extends Symbol<T> implements IExpression {
    
    public Value(T val) {
        super(val);
    }
    
    public Value evaluate() {
        return this;
    }
    
}
