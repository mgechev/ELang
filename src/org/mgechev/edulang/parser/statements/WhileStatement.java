package org.mgechev.edulang.parser.statements;

import java.util.ArrayList;
import java.util.Iterator;

import org.mgechev.edulang.parser.expressions.IExpression;
import org.mgechev.edulang.parser.expressions.symbols.BooleanValue;

public class WhileStatement implements IStatement {

    private ArrayList<IStatement> statements;
    private IExpression condition;
    
    public WhileStatement(IExpression condition, ArrayList<IStatement> statements) {
        this.statements = statements;
        this.condition = condition;
    }
    
    public void execute() {
        BooleanValue currentCondition = (BooleanValue)condition.evaluate();
        while (currentCondition.getValue()) {
            Iterator<IStatement> iter = statements.iterator();
            IStatement current;
            while (iter.hasNext()) {
                current = iter.next();
                current.execute();
            }
            currentCondition = (BooleanValue)condition.evaluate();
        }
    }
    
}
