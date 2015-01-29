package org.mgechev.elang.tokens;

public class KeyWordToken extends Token<String> {
    
    public KeyWordToken(String symbol) {
        this.value = symbol;
    }
    
}