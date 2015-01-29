package org.mgechev.elang.tokens;

public abstract class Token<T> {

    protected T value;
    
    public T value() {
        return value;
    }
    
}
