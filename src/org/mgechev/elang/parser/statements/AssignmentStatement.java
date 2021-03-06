package org.mgechev.elang.parser.statements;

import org.mgechev.elang.parser.statements.IStatement;
import org.mgechev.elang.parser.expressions.IExpression;
import org.mgechev.elang.parser.expressions.symbols.Variable;

public class AssignmentStatement implements IStatement {

    private Variable var;
    private IExpression expression;
    
    public AssignmentStatement(Variable var, IExpression expr) {
        this.var = var;
        this.expression = expr;
    }
    
    public void execute() {
        this.var.setValue(this.expression.evaluate());
    }
    
    public void setExpression(IExpression expr) {
        this.expression = expr;
    }

}
