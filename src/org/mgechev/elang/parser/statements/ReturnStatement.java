package org.mgechev.elang.parser.statements;

import org.mgechev.elang.parser.expressions.IExpression;
import org.mgechev.elang.parser.expressions.symbols.Value;
import org.mgechev.elang.parser.expressions.symbols.functions.CustomFunction;

public class ReturnStatement implements IStatement {

    private IExpression expr;
    private Value result;
    
    public ReturnStatement(IExpression expr) {
        this.expr = expr;
    }
    
    public void execute() {
        this.result = this.expr.evaluate();
        throw new ReturnStatementException(result);
    }
    
}
