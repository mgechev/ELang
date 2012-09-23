package org.mgechev.edulang.parser.expressions.symbols.builtinoperators;

import org.mgechev.edulang.parser.expressions.IExpression;
import org.mgechev.edulang.parser.expressions.symbols.NumberValue;
import org.mgechev.edulang.parser.expressions.symbols.Operator;
import org.mgechev.edulang.parser.expressions.symbols.Value;

public class Division extends Operator {
    
    public Division() {
        super(2);
    }
    
    public Value<Double> evaluate() {
        Double arg1 = (Double)((IExpression) this.args.pop()).evaluate().getValue();
        Double arg2 = (Double)((IExpression) this.args.pop()).evaluate().getValue();
        return new NumberValue(arg1 / arg2);
    }
}
