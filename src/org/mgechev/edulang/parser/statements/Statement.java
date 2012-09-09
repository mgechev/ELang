package org.mgechev.edulang.parser.statements;

import java.util.ArrayList;
import java.util.Iterator;

import org.mgechev.edulang.parser.expressions.IExpression;

public class Statement implements IStatement {

    private ArrayList<IExpression> exprs;
    
    public Statement(IExpression expr) {
        this.exprs = new ArrayList<IExpression>();
        this.exprs.add(expr);
    }
    
    public Statement(ArrayList<IExpression> exprs) {
        this.exprs = exprs;
    }
    
    public void execute() {
        Iterator<IExpression> iter = this.exprs.iterator();
        IExpression currentExpr;
        while (iter.hasNext()) {
            currentExpr = iter.next();
            currentExpr.evaluate();
        }
    }
    
}
