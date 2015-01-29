package org.mgechev.elang.parser.expressions.symbols.builtinoperators;

import org.mgechev.elang.parser.expressions.symbols.Operator;
import org.mgechev.elang.parser.expressions.symbols.Value;

public class Equals extends Operator {
    
    public Equals() {
        super(0);
    }
    
    public Value<Boolean> evaluate() {
        return null;
    }
}
