package org.mgechev.elang.parser.expressions.symbols;

import org.mgechev.elang.parser.expressions.IExpression;

public class StringValue extends Value<String> implements IExpression {

    public StringValue(String val) {
        super(val);
    }
    
    public Value evaluate() {
        return this;
    }
    
}
