package org.mgechev.edulang.tokens;

public abstract class Token<T> {

    protected T value;
    
    public T value() {
        return value;
    }
    
}
