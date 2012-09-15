package org.mgechev.edulang.parser.expressions.symbols;

public abstract class Symbol<T> {

    protected T value;
    
    public Symbol(T sym) {
        this.value = sym;
    }
    
    public T getValue() {
        return this.value;
    }
    
}
