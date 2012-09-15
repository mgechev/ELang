package org.mgechev.edulang.parser.expressions.symbols;

import java.util.Stack;

import org.mgechev.edulang.parser.expressions.IExpression;

public abstract class Evaluator<T> extends Symbol<T> implements IExpression {
    
    protected Stack<IExpression> args;
    
    public Evaluator() {
        super(null);
        this.args = new Stack<IExpression>();
    }
    
    public void setOperand(IExpression arg) {
        this.args.push(arg);
    }
    
    public abstract Value evaluate();
}
