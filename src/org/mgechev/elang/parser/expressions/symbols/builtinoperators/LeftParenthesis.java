package org.mgechev.elang.parser.expressions.symbols.builtinoperators;

import org.mgechev.elang.parser.expressions.symbols.Operator;
import org.mgechev.elang.parser.expressions.symbols.Value;

public class LeftParenthesis extends Operator {
    
    public LeftParenthesis() {
        super(0);
    }
    
    public Value<Boolean> evaluate() {
        return null;
    }
}
