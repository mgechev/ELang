package org.mgechev.elang.tokens;

import org.mgechev.elang.common.Operators;

public class OperatorToken extends Token<Operators> {

    public OperatorToken(String symbol) {
        this.value = this.getOperatorByString(symbol);
    }
    
    private Operators getOperatorByString(String symbol) {
        if (symbol.equals("+")) {
            return Operators.PLU;
        } else if (symbol.equals("-")) {
            return Operators.MIN;
        } else if (symbol.equals("*")) {
            return Operators.MUL;
        } else if (symbol.equals(";")) {
            return Operators.SCL;
        } else if (symbol.equals("=")) {
            return Operators.EQ;
        } else if (symbol.equals("<")) {
            return Operators.LT;
        } else if (symbol.equals(">")) {
            return Operators.GT;
        } else if (symbol.equals(">=")) {
            return Operators.GTE;
        } else if (symbol.equals("<=")) {
            return Operators.LTE;
        } else if (symbol.equals("/")) {
            return Operators.DIV;
        } else if (symbol.equals(":")) {
            return Operators.CLN;
        } else if (symbol.equals("or")) {
            return Operators.OR;
        } else if (symbol.equals("and")) {
            return Operators.AND;
        } else if (symbol.equals("not")) {
            return Operators.NOT;
        } else if (symbol.equals("==")) {
            return Operators.EQL;
        } else if (symbol.equals("/=")) {
            return Operators.NEQ;
        } else if (symbol.equals("(")) {
            return Operators.OB;
        } else if (symbol.equals(")")) {
            return Operators.CB;
        } else if (symbol.equals("'")) {
            return Operators.QT;
        } else if (symbol.equals(",")) {
            return Operators.CM; 
        } else if (symbol.equals("%")) {
            return Operators.MOD;
        } else {
            throw new RuntimeException("Unknow operator.");
        }
    }
    
}
