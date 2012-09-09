package org.mgechev.edulang.parser.expressions.functions;

import org.mgechev.edulang.parser.expressions.IExpression;
import org.mgechev.edulang.parser.expressions.Value;

public class Print implements IExpression {

    private IExpression toPrint;
    
    public Print(IExpression toPrint) {
        this.toPrint = toPrint;
    }
    
    public Value evaluate() {
        Value result = this.toPrint.evaluate();
        System.out.print(result.getValue());
        return result;
    }
    
}
