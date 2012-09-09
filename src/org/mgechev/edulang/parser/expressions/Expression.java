package org.mgechev.edulang.parser.expressions;

import java.util.ArrayList;
import java.util.Stack;

public class Expression implements IExpression {

    private ArrayList<Symbol> expression;
    
    public Expression(ArrayList<Symbol> result) {
        this.expression = result;
    }
    
    public Value evaluate() {
        Value result = null;
        int i = 0;
        int exprSize = this.expression.size();
        Stack<Symbol> stack = new Stack<Symbol>();
        Symbol current;
        IExpression temp;
        Value tempResult;
        while (i < exprSize) {
            current = this.expression.get(i);
            if (!isOperator(current)) {
                stack.push(current);
            } else {
                temp = (IExpression)stack.pop();
                if (stack.isEmpty()) {  //Prefix operator like -a
                    tempResult = ((Operator)current).evaluate(temp);
                } else {
                    tempResult = ((Operator)current).evaluate((IExpression)stack.pop(), temp);
                }
                stack.push(tempResult);
            }
            i += 1;
        }
        result = ((IExpression)stack.pop()).evaluate();
        return result;
    }
    
    private boolean isOperator(Symbol sym) {
        if (sym.getClass().toString().indexOf("Operator") >= 0) {
            return true;
        }
        return false;
    }
    
}
