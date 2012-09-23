package org.mgechev.edulang.parser.expressions.symbols.builtinoperators;

import org.mgechev.edulang.parser.expressions.symbols.Operator;
import org.mgechev.edulang.parser.expressions.symbols.Value;

public class Colon extends Operator {
    
    public Colon() {
        super(0);
    }
    
    public Value<Boolean> evaluate() {
        return null;
    }
}
