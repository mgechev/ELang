package org.mgechev.edulang.common;

import java.util.ArrayList;
import java.util.HashMap;

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
        this.functions.add("log");
        this.functions.add("abs");
        this.functions.add("ceil");
        this.functions.add("floor");
        this.functions.add("round");
        
        this.statements = new ArrayList<String>();
        this.statements.add("while");
        this.statements.add("endwhile");
        this.statements.add("if");
        this.statements.add("endif");
        this.statements.add("def");
        this.statements.add("enddef");
        this.statements.add("return");
    }
    
    public ArrayList<String> getFunctions() {
        return this.functions;
    }
    
    public boolean isFunction(String arg) {
        return this.functions.indexOf(arg) >= 0;
    }
    
    public ArrayList<String> getStatements() {
        return this.statements;
    }

    public boolean isStatement(String arg) {
        return this.statements.indexOf(arg) >= 0;
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
