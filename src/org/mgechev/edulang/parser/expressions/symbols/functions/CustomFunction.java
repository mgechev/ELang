package org.mgechev.edulang.parser.expressions.symbols.functions;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

import org.mgechev.edulang.common.Program;
import org.mgechev.edulang.interpreter.Interpreter;
import org.mgechev.edulang.parser.expressions.IExpression;
import org.mgechev.edulang.parser.expressions.symbols.Function;
import org.mgechev.edulang.parser.expressions.symbols.Value;
import org.mgechev.edulang.parser.expressions.symbols.Variable;
import org.mgechev.edulang.parser.statements.IStatement;
import org.mgechev.edulang.parser.statements.ReturnStatement;
import org.mgechev.edulang.parser.statements.ReturnStatementException;

public class CustomFunction extends Function {
    private ArrayList<Variable> funcArgs;
    private ArrayList<IStatement> statements;
    
    public CustomFunction() {
        super(0);
        this.funcArgs = null;
        this.statements = null;
    }
    
    //funcVars are the variables which must be declared for the function
    //args are the arguments which are put for evaluation
    public CustomFunction(ArrayList<Variable> funcArgs, ArrayList<IStatement> statements) {
        super(funcArgs.size());
        this.funcArgs = funcArgs;
        this.statements = statements;
    }
    
    public Value evaluate() {
        HashMap<String, Value> vars = new HashMap<String, Value>();
        Value result = null;
        Value value;
        String name;
        int current = 0;
        while (!this.args.isEmpty()) {
            value = ((IExpression)this.args.pop()).evaluate();
            name = this.funcArgs.get(current).getValue(); 
            vars.put(name, value);
            current += 1;
        }
        
        Program.Get().pushScope(vars);
        
        try {
            Interpreter interpreter = new Interpreter(this.statements);
            interpreter.interpret();
        } catch (ReturnStatementException e) {
            result = e.getResult();
        }
        
        Program.Get().popScope();
        
        return result;
    }
    
    public void setArguments(ArrayList<Variable> args) {
        this.funcArgs = args;
        this.argsCount = args.size();
    }
    
    public void setStatements(ArrayList<IStatement> statements) {
        this.statements = statements;
    }

}
