package org.mgechev.elang.parser.expressions.symbols;

import org.mgechev.elang.parser.expressions.symbols.Value;
import org.mgechev.elang.parser.expressions.IExpression;

public class BooleanValue extends Value<Boolean> implements IExpression {

    public BooleanValue(Boolean val) {
        super(val);
    }
    
    public Value evaluate() {
        return this;
    }
    
}
