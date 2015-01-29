package org.mgechev.elang.parser.statements;

import org.mgechev.elang.parser.expressions.symbols.Value;

public class ReturnStatementException extends RuntimeException {

    private static final long serialVersionUID = 1L;
    private Value returnValue;
    
    public ReturnStatementException(Value returnValue) {
        this.returnValue = returnValue;
    }
    
    public Value getResult() {
        return this.returnValue;
    }
    
}
