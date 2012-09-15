package org.mgechev.edulang.parser.expressions.symbols;

import org.mgechev.edulang.parser.expressions.IExpression;

public class BooleanValue extends Value<Boolean> implements IExpression {

    public BooleanValue(Boolean val) {
        super(val);
    }
    
    public Value evaluate() {
        return this;
    }
    
}
