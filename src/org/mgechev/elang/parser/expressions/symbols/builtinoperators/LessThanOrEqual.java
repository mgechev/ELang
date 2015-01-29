package org.mgechev.elang.parser.expressions.symbols.builtinoperators;

import org.mgechev.elang.parser.expressions.IExpression;
import org.mgechev.elang.parser.expressions.symbols.BooleanValue;
import org.mgechev.elang.parser.expressions.symbols.Operator;
import org.mgechev.elang.parser.expressions.symbols.Value;

public class LessThanOrEqual extends Operator {
    
    public LessThanOrEqual() {
        super(2);
    }
    
    public Value<Boolean> evaluate() {
        Double arg1 = (Double)((IExpression) this.args.pop()).evaluate().getValue();
        Double arg2 = (Double)((IExpression) this.args.pop()).evaluate().getValue();
        return new BooleanValue(arg1 <= arg2);
    }
}
