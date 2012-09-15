package org.mgechev.edulang.parser.expressions.symbols;

import org.mgechev.edulang.parser.expressions.IExpression;

public class StringValue extends Value<String> implements IExpression {

    public StringValue(String val) {
        super(val);
    }
    
    public Value evaluate() {
        return this;
    }
    
}
