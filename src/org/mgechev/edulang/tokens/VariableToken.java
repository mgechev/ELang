package org.mgechev.edulang.tokens;

public class VariableToken extends Token<String> {

    public VariableToken(String symbol) {
        this.value = symbol;
    }

}
