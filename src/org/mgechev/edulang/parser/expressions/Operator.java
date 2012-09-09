package org.mgechev.edulang.parser.expressions;

import org.mgechev.edulang.common.Operators;

public class Operator extends Symbol<Operators> {
   
    public Operator(Operators operator) {
        super(operator);
    }
    
    public Value evaluate(IExpression arg) {
        return evaluate(null, arg);
    }
    
    //Actually this IExpression here could be a value or variable which is actually an expression with a single value
    public Value evaluate(IExpression arg1, IExpression arg2) {
        Value var2 = arg2.evaluate();
        if (arg1 != null) {
            Value var1 = arg1.evaluate();
            if (this.value == Operators.DIV) {
                return new NumberValue(((NumberValue)var1).getValue() / ((NumberValue)var2).getValue());
            } else if (this.value == Operators.MIN) {
                return new NumberValue(((NumberValue)var1).getValue() - ((NumberValue)var2).getValue());
            } else if (this.value == Operators.PLU) {
                return new NumberValue(((NumberValue)var1).getValue() + ((NumberValue)var2).getValue());
            } else if (this.value == Operators.MUL) {
                return new NumberValue(((NumberValue)var1).getValue() * ((NumberValue)var2).getValue());
            } else if (this.value == Operators.GT) {
                return new BooleanValue(((NumberValue)var1).getValue() > ((NumberValue)var2).getValue());
            } else if (this.value == Operators.LT) {
                return new BooleanValue(((NumberValue)var1).getValue() < ((NumberValue)var2).getValue());
            } else if (this.value == Operators.GTE) {
                return new BooleanValue(((NumberValue)var1).getValue() >= ((NumberValue)var2).getValue());
            } else if (this.value == Operators.LTE) {
                return new BooleanValue(((NumberValue)var1).getValue() <= ((NumberValue)var2).getValue());
            } else if (this.value == Operators.OR) {
                return new BooleanValue(((BooleanValue)var1).getValue() || ((BooleanValue)var2).getValue());
            } else if (this.value == Operators.AND) {
                return new BooleanValue(((BooleanValue)var1).getValue() && ((BooleanValue)var2).getValue());
            } else if (this.value == Operators.NEQ) {
                if (var1.getClass().toString().indexOf("Boolean") >= 0) {
                    return new BooleanValue(!((BooleanValue)var1).getValue().equals(((BooleanValue)var2).getValue()));
                } else {
                    return new BooleanValue(!((NumberValue)var1).getValue().equals(((NumberValue)var2).getValue()));
                }   
            } else if (this.value == Operators.EQL) {
                if (var1.getClass().toString().indexOf("Boolean") >= 0) {
                    return new BooleanValue(((BooleanValue)var1).getValue().equals(((BooleanValue)var2).getValue()));
                } else {
                    return new BooleanValue(((NumberValue)var1).getValue().equals(((NumberValue)var2).getValue()));
                }             
            } else {
                throw new RuntimeException("Unknown operator.");
            }
        }
        if (this.value == Operators.MIN) {
            return new NumberValue(-((NumberValue)var2).getValue());            
        } else if (this.value == Operators.NOT) {
            return new BooleanValue(!((BooleanValue)var2).getValue());
        } else {
            if (var2.getClass().toString().indexOf("Boolean") >= 0) {
                return new BooleanValue(((BooleanValue)var2).getValue());
            }
            return new NumberValue(((NumberValue)var2).getValue());      
        }
    }
    
}
