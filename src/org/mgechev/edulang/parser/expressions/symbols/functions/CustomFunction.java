package org.mgechev.edulang.parser.expressions.symbols.functions;

import java.util.ArrayList;
import java.util.HashMap;

import org.mgechev.edulang.common.Program;
import org.mgechev.edulang.interpreter.Interpreter;
import org.mgechev.edulang.parser.expressions.IExpression;
import org.mgechev.edulang.parser.expressions.symbols.Function;
import org.mgechev.edulang.parser.expressions.symbols.Value;
import org.mgechev.edulang.parser.expressions.symbols.Variable;
import org.mgechev.edulang.parser.statements.IStatement;

public class CustomFunction extends Function {
    private ArrayList<Variable> funcArgs;
    private ArrayList<IStatement> statements;
    private IExpression returlValue;
    
    //funcVars are the variables which must be declared for the function
    //args are the arguments which are put for evaluation
    public CustomFunction(ArrayList<Variable> funcArgs, ArrayList<IStatement> statements, IExpression returnValue) {
        super(funcArgs.size());
        this.funcArgs = funcArgs;
        this.statements = statements;
        this.returlValue = returnValue;
    }
    
    public Value evaluate() {
        HashMap<String, Value> vars = new HashMap<String, Value>();
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
        
        Interpreter interpreter = new Interpreter(this.statements);
        interpreter.interpret();
        
        Value result = this.returlValue.evaluate();
        
        Program.Get().popScope();
        
        return result;
    }

}
