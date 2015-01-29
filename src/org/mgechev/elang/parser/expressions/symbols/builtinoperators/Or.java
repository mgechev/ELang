package org.mgechev.elang.parser.expressions.symbols.builtinoperators;

import org.mgechev.elang.parser.expressions.IExpression;
import org.mgechev.elang.parser.expressions.symbols.BooleanValue;
import org.mgechev.elang.parser.expressions.symbols.Operator;
import org.mgechev.elang.parser.expressions.symbols.Value;

public class Or extends Operator {
    
    public Or() {
        super(2);
    }
    
    public Value<Boolean> evaluate() {
        Boolean arg1 = (Boolean)((IExpression) this.args.pop()).evaluate().getValue();
        Boolean arg2 = (Boolean)((IExpression) this.args.pop()).evaluate().getValue();
        return new BooleanValue(arg1 || arg2);
    }
}
