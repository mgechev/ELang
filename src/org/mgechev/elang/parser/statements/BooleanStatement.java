package org.mgechev.elang.parser.statements;

import org.mgechev.elang.parser.statements.IStatement;
import org.mgechev.elang.parser.expressions.IExpression;

public class BooleanStatement implements IStatement {

    private IExpression expression;
    
    public BooleanStatement(IExpression expression) {
        this.expression = expression;
    }
    
    public void execute() {
        this.expression.evaluate();
    }
    
}
