package org.mgechev.edulang.interpreter;

import java.util.ArrayList;
import java.util.Iterator;

import org.mgechev.edulang.parser.statements.IStatement;

public class Interpreter {
    private ArrayList<IStatement> statements;
    
    public Interpreter(ArrayList<IStatement> statements) {
        this.statements = statements;
    }
    
    public void interpret() {
        Iterator<IStatement> iter = this.statements.iterator();
        IStatement current;
        while (iter.hasNext()) {
            current = iter.next();
            current.execute();
        }
    }
}
