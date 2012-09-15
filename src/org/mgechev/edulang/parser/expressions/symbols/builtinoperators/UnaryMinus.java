package org.mgechev.edulang.parser.expressions.symbols.builtinoperators;

import org.mgechev.edulang.parser.expressions.IExpression;
import org.mgechev.edulang.parser.expressions.symbols.NumberValue;
import org.mgechev.edulang.parser.expressions.symbols.Value;

public class UnaryMinus extends BuiltInOperator {
    
    public UnaryMinus() {
        super(1);
    }
    
    public Value<Double> evaluate() {
        Double arg1 = (Double)((IExpression) this.args.pop()).evaluate().getValue();
        return new NumberValue(- arg1);
    }
}
