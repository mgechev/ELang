package org.mgechev.edulang.parser.expressions.symbols.builtinoperators;

import org.mgechev.edulang.parser.expressions.IExpression;
import org.mgechev.edulang.parser.expressions.symbols.BooleanValue;
import org.mgechev.edulang.parser.expressions.symbols.Operator;
import org.mgechev.edulang.parser.expressions.symbols.Value;

public class Not extends Operator {
    
    public Not() {
        super(1);
    }
    
    public Value<Boolean> evaluate() {
        Boolean arg1 = (Boolean)((IExpression) this.args.pop()).evaluate().getValue();
        return new BooleanValue(!arg1);
    }
}
