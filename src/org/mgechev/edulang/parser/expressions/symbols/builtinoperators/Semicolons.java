package org.mgechev.edulang.parser.expressions.symbols.builtinoperators;

import org.mgechev.edulang.parser.expressions.symbols.Operator;
import org.mgechev.edulang.parser.expressions.symbols.Value;

public class Semicolons extends Operator {
    
    public Semicolons() {
        super(0);
    }
    
    public Value<Boolean> evaluate() {
        return null;
    }
}
