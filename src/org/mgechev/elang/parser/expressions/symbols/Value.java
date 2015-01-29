package org.mgechev.elang.parser.expressions.symbols;

import org.mgechev.elang.parser.expressions.IExpression;

public class Value<T> extends Symbol<T> implements IExpression {
    
    public Value(T val) {
        super(val);
    }
    
    public Value evaluate() {
        return this;
    }
    
}
