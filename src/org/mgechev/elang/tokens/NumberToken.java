package org.mgechev.elang.tokens;

public class NumberToken extends Token<Double> {
    
    public NumberToken(String symbol) {
        this.value = Double.parseDouble(symbol);
    }

}
