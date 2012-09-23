package org.mgechev.edulang.parser.statements;

import java.util.ArrayList;
import java.util.Iterator;

import org.mgechev.edulang.interpreter.Interpreter;
import org.mgechev.edulang.parser.expressions.IExpression;
import org.mgechev.edulang.parser.expressions.symbols.BooleanValue;

public class IfStatement implements IStatement {

    private IExpression condition;
    private ArrayList<IStatement> statements;
    private ArrayList<IStatement> elseStatements;
    
    public IfStatement(IExpression condition, ArrayList<IStatement> statements) {
        this.condition = condition;
        this.statements = statements;
    }
    
    public IfStatement(IExpression condition, ArrayList<IStatement> statements, ArrayList<IStatement> elseStatements) {
        this(condition, statements);
        this.elseStatements = elseStatements;
    }
    
    public void execute() {
        BooleanValue result = (BooleanValue)condition.evaluate();
        ArrayList<IStatement> toExecute;
        if (result.getValue()) {
            toExecute = this.statements;
        } else {
            toExecute = this.elseStatements;
        }
        if (toExecute != null) {
            Interpreter interpreter = new Interpreter(toExecute);
            interpreter.interpret();
        }
    }
    
}
