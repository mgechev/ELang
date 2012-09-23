package org.mgechev.edulang.parser.expressions.symbols.builtinoperators;

import org.mgechev.edulang.parser.expressions.IExpression;
import org.mgechev.edulang.parser.expressions.symbols.BooleanValue;
import org.mgechev.edulang.parser.expressions.symbols.Operator;
import org.mgechev.edulang.parser.expressions.symbols.Value;

public class And extends Operator {
    
    public And() {
        super(2);
    }
    
    public Value<Boolean> evaluate() {
        Boolean arg1 = (Boolean)((IExpression) this.args.pop()).evaluate().getValue();
        Boolean arg2 = (Boolean)((IExpression) this.args.pop()).evaluate().getValue();
        return new BooleanValue(arg1 && arg2);
    }
}
