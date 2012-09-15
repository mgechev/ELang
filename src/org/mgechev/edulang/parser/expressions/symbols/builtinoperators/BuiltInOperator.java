package org.mgechev.edulang.parser.expressions.symbols.builtinoperators;

import org.mgechev.edulang.parser.expressions.symbols.Evaluator;

public abstract class BuiltInOperator extends Evaluator {
    
    private int argsCount;
    
    public BuiltInOperator(int argsCount) {
        this.argsCount = argsCount;
    }
    
    public int getArgumentsCount() {
        return this.argsCount;
    }
    
}
