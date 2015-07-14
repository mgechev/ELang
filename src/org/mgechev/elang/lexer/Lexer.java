package org.mgechev.elang.lexer;
import java.util.ArrayList;

import org.mgechev.elang.common.Program;
import org.mgechev.elang.tokens.BooleanToken;
import org.mgechev.elang.tokens.FunctionToken;
import org.mgechev.elang.tokens.NumberToken;
import org.mgechev.elang.tokens.OperatorToken;
import org.mgechev.elang.tokens.KeyWordToken;
import org.mgechev.elang.tokens.StringToken;
import org.mgechev.elang.tokens.Token;
import org.mgechev.elang.tokens.NameToken;

public class Lexer {

    private String program;
    private int current;
    
    public Lexer(String program) {
        this.current = 0;
        this.program = program;
    }
    
    private String getNumber() {
        String number = "";
        
        while (this.hasNext() && this.isNumber(this.current())) {
            number += this.current();
            this.next();
            if (this.hasNext() && this.current() == '.') {
               number += this.current();
               this.next();
            }
        }

        return number;
    }
    
    private String getString() {
        String var = "";
        
        while (this.hasNext() && this.validVarCharacter(this.current())) {
            var += this.current();
            this.next();
        }
        
        return var;
    }
    
    public ArrayList<Token> lex() {
        String currentSymbol;
        ArrayList<Token> tokens = new ArrayList<Token>();
        Token currentToken;
        while (this.hasNext()) {
            if (this.isWhiteSpace(this.current())) {
                next();
            } else {
                currentSymbol = "";
                currentToken = null;
                if (this.isNumber(this.current())) {
                    currentToken = new NumberToken(this.getNumber());
                } else if (this.isAlphabet(this.current()) || this.isUnderscore(this.current())) {
                    currentSymbol = this.getString();
                    if (Program.Get().isFunction(currentSymbol)) {
                        currentToken = new FunctionToken(currentSymbol);
                    } else if (Program.Get().isStatement(currentSymbol)) {
                        currentToken = new KeyWordToken(currentSymbol);
                    } else if (currentSymbol.matches("(true|false)")) {
                        currentToken = new BooleanToken(currentSymbol);
                    } else if (isOperator(currentSymbol)) {
                        currentToken = new OperatorToken(currentSymbol);
                    } else {
                        currentToken = new NameToken(currentSymbol);
                    }
                } else if (this.isQuote(this.current().toString())) {
                    Character op = this.current();
                    String operator = op.toString();
                    if (operator.equals("'")) {
                        this.next();
                        String str = "";
                        while (!this.current().toString().equals("'")) {
                            str += this.current().toString();
                            this.next();
                        }
                        this.next();
                        currentToken = new StringToken(str);
                    }
                } else if (this.isOperator(this.current().toString())) {
                    Character op = this.current();
                    String operator = op.toString();
                    this.next();
                    if (op == '<' || op == '>' || op == '=' || op == '/') {
                        if (this.current() == '=') {
                            operator += "=";     
                            this.next();
                        }
                    }
                    currentToken = new OperatorToken(operator);                    
                } else {
                    throw new RuntimeException("Unknown token.");
                }
                if (currentToken != null) {
                    tokens.add(currentToken);
                }
            }
        }
        return tokens;
    }
    
    private boolean isNumber(Character arg) {
        return arg.toString().matches("[0-9]");
    }

    private boolean isAlphabet(Character arg) {
        return arg.toString().matches("[a-zA-Z]");
    }

    private boolean validVarCharacter(Character arg) {
        return this.isAlphabet(arg) || this.isNumber(arg) || this.isUnderscore(arg);
    }
    
    private boolean isUnderscore(Character arg) {
        return arg.equals('_');
    }
    
    private boolean isQuote(String arg) {
        return arg.equals("'");
    }
    
    private boolean isOperator(String arg) {
        return arg.matches("([-\\*\\+/\\^=)(;><:]|and|or|not|==|/=|'|,|%)");
    }
    
    private boolean isWhiteSpace(Character arg) {
        return arg.toString().matches("(\\s|\\n)");
    }
    
    private boolean hasNext() {
        return this.current < this.program.length();
    }
    
    private void next() {
        ++this.current;
    }
    
    private Character current() {
        return this.program.charAt(this.current);
    }

}
