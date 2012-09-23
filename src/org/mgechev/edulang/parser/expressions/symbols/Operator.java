package org.mgechev.edulang.parser.expressions.symbols;


public abstract class Operator extends Evaluator {
    
    private int argsCount;
    
    public Operator(int argsCount) {
        this.argsCount = argsCount;
    }
    
    public int getArgumentsCount() {
        return this.argsCount;
    }
    
}
