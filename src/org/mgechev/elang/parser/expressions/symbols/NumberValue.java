package org.mgechev.elang.parser.expressions.symbols;

import org.mgechev.elang.parser.expressions.IExpression;

public class NumberValue extends Value<Double> implements IExpression {

    
    public NumberValue(Double val) {
        super(val);
    }

    public Value evaluate() {
        return this;
    }
    
}
