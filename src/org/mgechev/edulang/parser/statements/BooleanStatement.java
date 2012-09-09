package org.mgechev.edulang.parser.statements;

import org.mgechev.edulang.parser.expressions.IExpression;

public class BooleanStatement implements IStatement {

    private IExpression expression;
    
    public BooleanStatement(IExpression expression) {
        this.expression = expression;
    }
    
    public void execute() {
        this.expression.evaluate();
    }
    
}
