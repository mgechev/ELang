package org.mgechev.edulang.parser.expressions.symbols.builtinfunctions;

import org.mgechev.edulang.parser.expressions.IExpression;
import org.mgechev.edulang.parser.expressions.symbols.Function;
import org.mgechev.edulang.parser.expressions.symbols.NumberValue;
import org.mgechev.edulang.parser.expressions.symbols.Value;

public class Tan extends Function {
    
    public Tan() {
        super(1);
    }
    
    public Value<Double> evaluate() {
        Double arg1 = (Double)((IExpression) this.args.pop()).evaluate().getValue();
        return new NumberValue(Math.tan(arg1));
    }
}
