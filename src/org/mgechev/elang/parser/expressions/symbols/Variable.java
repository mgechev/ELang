package org.mgechev.elang.parser.expressions.symbols;

import org.mgechev.elang.common.Program;
import org.mgechev.elang.parser.expressions.IExpression;

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
