package org.mgechev.edulang.parser.expressions.symbols.builtinoperators;

import org.mgechev.edulang.parser.expressions.IExpression;
import org.mgechev.edulang.parser.expressions.symbols.BooleanValue;
import org.mgechev.edulang.parser.expressions.symbols.Operator;
import org.mgechev.edulang.parser.expressions.symbols.Value;

public class NotEquals extends Operator {
    
    public NotEquals() {
        super(2);
    }
    
    public Value<Boolean> evaluate() {
        Object arg1 = (Object)((IExpression) this.args.pop()).evaluate().getValue();
        Object arg2 = (Object)((IExpression) this.args.pop()).evaluate().getValue();
        return new BooleanValue(!arg1.equals(arg2));
    }
}
