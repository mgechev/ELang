package org.mgechev.elang.parser.expressions;

import java.util.ArrayList;
import java.util.Stack;

import org.mgechev.elang.parser.expressions.symbols.Evaluator;
import org.mgechev.elang.parser.expressions.symbols.Function;
import org.mgechev.elang.parser.expressions.symbols.Symbol;
import org.mgechev.elang.parser.expressions.symbols.Value;

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
            if (!isEvaluator(current)) {
                stack.push(current);
            } else {                
                Evaluator currentEvaluator = (Evaluator)current;
                if (currentEvaluator instanceof Function) {
                    Function func = ((Function) currentEvaluator);
                    int currentArg = 0;
                    while (currentArg < func.getArgumentsCount()) {
                        temp = (IExpression)stack.pop();
                        func.setOperand(temp);
                        currentArg += 1;
                    }
                } else {
                    temp = (IExpression)stack.pop();
                    currentEvaluator.setOperand(temp);
                    if (!stack.isEmpty()) {  //Prefix operator like -a
                        temp = (IExpression)stack.pop();
                        currentEvaluator.setOperand(temp);
                    }
                }
                stack.push(currentEvaluator.evaluate());
            }
            i += 1;
        }
        result = ((IExpression)stack.pop()).evaluate();
        return result;
    }
    
    private boolean isEvaluator(Symbol sym) {
        if (!(sym instanceof Evaluator)) {
            return false;
        }
        return true;
    }
    
}
