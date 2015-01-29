package org.mgechev.elang.parser.expressions.symbols.builtinoperators;

import org.mgechev.elang.parser.expressions.IExpression;
import org.mgechev.elang.parser.expressions.symbols.NumberValue;
import org.mgechev.elang.parser.expressions.symbols.Operator;
import org.mgechev.elang.parser.expressions.symbols.Value;

public class Plus extends Operator {
    
    public Plus() {
        super(2);
    }
    
    public Value<Double> evaluate() {
        Double arg1 = (Double)((IExpression) this.args.pop()).evaluate().getValue();
        Double arg2 = (Double)((IExpression) this.args.pop()).evaluate().getValue();
        return new NumberValue(arg1 + arg2);
    }
}
