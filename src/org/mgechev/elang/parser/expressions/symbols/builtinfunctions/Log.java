package org.mgechev.elang.parser.expressions.symbols.builtinfunctions;

import org.mgechev.elang.parser.expressions.IExpression;
import org.mgechev.elang.parser.expressions.symbols.Function;
import org.mgechev.elang.parser.expressions.symbols.NumberValue;
import org.mgechev.elang.parser.expressions.symbols.Value;

public class Log extends Function {
    
    public Log() {
        super(1);
    }
    
    public Value<Double> evaluate() {
        Double arg1 = (Double)((IExpression) this.args.pop()).evaluate().getValue();
        return new NumberValue(Math.log(arg1));
    }
}
