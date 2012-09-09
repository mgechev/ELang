package org.mgechev.edulang.parser.statements;

import java.util.ArrayList;
import java.util.Iterator;

import org.mgechev.edulang.parser.expressions.BooleanValue;
import org.mgechev.edulang.parser.expressions.IExpression;

public class IfStatement implements IStatement {

    private IExpression condition;
    private ArrayList<IStatement> statements;
    
    public IfStatement(IExpression condition, ArrayList<IStatement> statements) {
        this.condition = condition;
        this.statements = statements;
    }
    
    public void execute() {
        BooleanValue result = (BooleanValue)condition.evaluate();
        if (result.getValue()) {
            Iterator<IStatement> iter = statements.iterator();
            IStatement current;
            while (iter.hasNext()) {
                current = iter.next();
                current.execute();
            }
        }
    }
    
}
