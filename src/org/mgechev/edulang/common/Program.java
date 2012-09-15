package org.mgechev.edulang.common;

import java.util.ArrayList;
import java.util.HashMap;

import org.mgechev.edulang.parser.expressions.IExpression;
import org.mgechev.edulang.parser.expressions.symbols.Value;

public class Program {

    private static Program INSTANCE = null;
    private ArrayList<String> functions;
    private ArrayList<String> statements;
    private HashMap<String, Value> vars;
    
    private Program() {
        this.vars = new HashMap<String, Value>();
        
        this.functions = new ArrayList<String>();
        this.functions.add("print");
        this.functions.add("read");
        this.functions.add("sin");
        this.functions.add("cos");
        this.functions.add("pow");
        this.functions.add("cotan");
        this.functions.add("tan");
        
        this.statements = new ArrayList<String>();
        this.statements.add("while");
        this.statements.add("endwhile");
        this.statements.add("if");
        this.statements.add("endif");
    }
    
    public ArrayList<String> getFunctions() {
        return this.functions;
    }
    
    public ArrayList<String> getStatements() {
        return this.statements;
    }
    
    public static Program Get() {
        if (INSTANCE == null) {
            INSTANCE = new Program();
        }
        return INSTANCE;
    }
    
    public Value getVar(String name) {
        if (this.vars.containsKey(name)) {
            return this.vars.get(name);
        }
        throw new RuntimeException("The variable " + name + " is not declared.");
    }
    
    public void setVal(String name, Value value) {
        this.vars.put(name, value);
    }
    
    public boolean variableExists(String name) {
        return this.vars.containsKey(name);
    }
    
}
