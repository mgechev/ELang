package org.mgechev.edulang.parser.expressions;

import org.mgechev.edulang.common.Program;

public class Variable extends Symbol<String> implements IExpression {
    
    public Variable(String name, Value value) {
        this(name);
        Program.Get().setVal(this.value, value);
    }
    
    public Variable(String name) {
        super(name);
    }
    
    public Value evaluate() {
        return Program.Get().getVar(this.value);
    }
    
    public void setValue(Value val) {
        Program.Get().setVal(this.value, val);
    }
    
}
