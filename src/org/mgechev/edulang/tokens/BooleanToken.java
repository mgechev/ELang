package org.mgechev.edulang.tokens;

public class BooleanToken extends Token<Boolean> {

    public BooleanToken(String symbol) {
        if (symbol.equals("true")) {
            this.value = true;
        } else {
            this.value = false;
        }
    }
    
}
