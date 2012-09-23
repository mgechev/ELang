package org.mgechev.edulang.parser.expressions.symbols.builtinoperators;

import org.mgechev.edulang.parser.expressions.symbols.Operator;
import org.mgechev.edulang.parser.expressions.symbols.Value;

public class RightParenthesis extends Operator {
    
    public RightParenthesis() {
        super(0);
    }
    
    public Value<Boolean> evaluate() {
        return null;
    }
}
