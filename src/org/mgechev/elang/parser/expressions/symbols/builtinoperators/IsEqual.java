package org.mgechev.elang.parser.expressions.symbols.builtinoperators;

import org.mgechev.elang.parser.expressions.IExpression;
import org.mgechev.elang.parser.expressions.symbols.BooleanValue;
import org.mgechev.elang.parser.expressions.symbols.Operator;
import org.mgechev.elang.parser.expressions.symbols.Value;

public class IsEqual extends Operator {
    
    public IsEqual() {
        super(2);
    }
    
    public Value<Boolean> evaluate() {
        Object arg1 = (Object)((IExpression) this.args.pop()).evaluate().getValue();
        Object arg2 = (Object)((IExpression) this.args.pop()).evaluate().getValue();
        return new BooleanValue(arg1.equals(arg2));
    }
}
