package org.mgechev.elang.tokens;

public class NameToken extends Token<String> {

    public NameToken(String symbol) {
        this.value = symbol;
    }

}
