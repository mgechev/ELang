package org.mgechev.elang.parser.expressions.symbols.builtinoperators;

import org.mgechev.elang.parser.expressions.symbols.Operator;
import org.mgechev.elang.parser.expressions.symbols.Value;

public class Comma extends Operator {
    
    public Comma() {
        super(0);
    }
    
    public Value<Boolean> evaluate() {
        return null;
    }
}
