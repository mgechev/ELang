package org.mgechev.edulang.parser.expressions.symbols.builtinfunctions;

import org.mgechev.edulang.parser.expressions.symbols.Evaluator;

public abstract class BuiltInFunction extends Evaluator {
    
    private int argsCount;
    
    public BuiltInFunction(int argsCount) {
        this.argsCount = argsCount;
    }
    
    public int getArgumentsCount() {
        return this.argsCount;
    }
    
}
