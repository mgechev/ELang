package org.mgechev.edulang.parser;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Stack;

import org.mgechev.edulang.common.Operators;
import org.mgechev.edulang.common.Program;
import org.mgechev.edulang.parser.expressions.BooleanValue;
import org.mgechev.edulang.parser.expressions.Expression;
import org.mgechev.edulang.parser.expressions.IExpression;
import org.mgechev.edulang.parser.expressions.NumberValue;
import org.mgechev.edulang.parser.expressions.Operator;
import org.mgechev.edulang.parser.expressions.StringValue;
import org.mgechev.edulang.parser.expressions.Symbol;
import org.mgechev.edulang.parser.expressions.Value;
import org.mgechev.edulang.parser.expressions.Variable;
import org.mgechev.edulang.parser.expressions.functions.Read;
import org.mgechev.edulang.parser.expressions.functions.Print;
import org.mgechev.edulang.parser.statements.*;
import org.mgechev.edulang.tokens.*;

public class Parser {
    
    private ArrayList<IStatement> statements;
    private ArrayList<Token> tokens;
    
    private int currentToken;
    
    public Parser(ArrayList<Token> tokens) {
        this.statements = new ArrayList<IStatement>();
        this.tokens = tokens;
        this.currentToken = 0;
    }
    
    public ArrayList<IStatement> getStatements() {
        return this.statements;
    }
    
    public void parse() {
        int tokensCount = this.tokens.size();
        while (currentToken < tokensCount) {
            this.parseBlockLine(this.statements);
            currentToken += 1;
        }
    }
    
    private void parseBlockLine(ArrayList<IStatement> block) {
        Token current;
        current = this.tokens.get(currentToken);
        
        if (this.isVar(current)) {
            this.parseVar(block);
        }
        if (this.isFunction(current)) {
            this.parseFunction(block);
        }
        if (this.isStatement(current)) {
            this.parseStatement(block);
        }
    }
    
    private void parseStatement(ArrayList<IStatement> block) {
        StatementToken statement = (StatementToken)this.tokens.get(currentToken);
        if (statement.value().equals("while")) {
            this.parseWhile(block);
        } else if (statement.value().equals("if")) {
            this.parseIf(block);
        }
    }
    
    private void parseWhile(ArrayList<IStatement> block) {
        Token current = this.tokens.get(currentToken);
        IExpression condition = null;
        ArrayList<IStatement> statements = new ArrayList<IStatement>();
        boolean conditionParsed = false;
        while (!current.value().equals("endwhile")) {
            currentToken += 1;
            current = this.tokens.get(currentToken);
            
            if (!conditionParsed) {
                condition = parseExpression(Operators.CLN);
                currentToken += 1;
                current = this.tokens.get(currentToken);
                conditionParsed = true;
            }            
            this.parseBlockLine(statements);
        }
        block.add(new WhileStatement(condition, statements));
    }
    
    private void parseIf(ArrayList<IStatement> block) {
        Token current = this.tokens.get(currentToken);
        IExpression condition = null;
        ArrayList<IStatement> statements = new ArrayList<IStatement>();
        boolean conditionParsed = false;
        while (!current.value().equals("endif")) {
            currentToken += 1;
            current = this.tokens.get(currentToken);

            if (!conditionParsed) {
                condition = parseExpression(Operators.CLN);
                currentToken += 1;
                current = this.tokens.get(currentToken);
                conditionParsed = true;
            }
                
            this.parseBlockLine(statements);
        }
        block.add(new IfStatement(condition, statements));
    }
    
    private void parseFunction(ArrayList<IStatement> block) {
        String funcName = ((FunctionToken)this.tokens.get(currentToken)).value();
        if (funcName.equals("print")) {
            currentToken += 1;
            block.add(new Statement(new Print(this.parseExpression(Operators.SCL))));
        }
    }
    
    private int getOperatorPriority(Symbol operator) {
        Operators o = ((Operator)operator).getValue();
        if (o == Operators.PLU || o == Operators.MIN || o == Operators.OR || o == Operators.AND) {
            return 2;
        } else {
            return 3;
        }
    }
    
    private Symbol convertToken(Token token) {
        Symbol result = null;
        if (isVar(token)) {
            String varName = (String)token.value();
            if (Program.Get().variableExists(varName)) {
                return new Variable(varName);
            } else {
                throw new RuntimeException("The variable " + varName + "is not declared.");
            }
        } else if (isNumber(token)) {
            return new NumberValue(((NumberToken)token).value());
        } else if (isBoolean(token)) {
            return new BooleanValue(((BooleanToken)token).value());
        } else if (isOperator(token)) {
            return new Operator(((OperatorToken)token).value());
        } else if (isFunction(token)) {
            return new Read();
        } else {
            throw new RuntimeException("Unknown token.");
        }
    }
    
    private IExpression parseExpression(Operators statementEnd) {
        Token current = this.tokens.get(currentToken);
        
        Stack<Symbol> stack = new Stack<Symbol>();
        ArrayList<Symbol> result = new ArrayList<Symbol>();
        Symbol currentSym;
        if (isString(current)) {
            return parseString(statementEnd);
        }
        return parseCalculationExpression(statementEnd);
    }
    
    private IExpression parseString(Operators statementEnd) {
        Token current = this.tokens.get(currentToken);
        currentToken += 1;
        Token temp = this.tokens.get(currentToken);
        
        if (!temp.value().equals(statementEnd)) {
            throw new RuntimeException("You cannot perform operations over strings!");
        }
        return new StringValue((String)current.value());
    }
    
    private IExpression parseCalculationExpression(Operators statementEnd) {
        Token current = this.tokens.get(currentToken);
        
        Stack<Symbol> stack = new Stack<Symbol>();
        ArrayList<Symbol> result = new ArrayList<Symbol>();
        Symbol currentSym;
        
        while (!current.value().equals(statementEnd)) {
            currentSym = convertToken(current);
            if (isVar(current) || isNumber(current) || isBoolean(current)) {
                result.add(currentSym);
            } else {
                Operators op = ((OperatorToken)current).value();
                if (!op.equals(Operators.OB) && !op.equals(Operators.CB)) {
                    
                    while (!stack.isEmpty() && !stack.peek().getValue().equals(Operators.OB) && this.getOperatorPriority(currentSym) <= this.getOperatorPriority(stack.peek())) {
                        if (stack.peek().getValue().equals(Operators.OB)) {
                            stack.pop();
                        } else {
                            result.add(stack.pop());
                        }
                    }
                    stack.push(currentSym);

                } else if (op.equals(Operators.OB)) {
                    stack.push(currentSym);                    
                } else {
                    while (!stack.peek().getValue().equals(Operators.OB)) {
                        currentSym = stack.pop();
                        result.add(currentSym);
                    }
                    stack.pop();
                }
            }

            currentToken += 1;
            current = this.tokens.get(currentToken);
        }
        Operators op;
        while (!stack.empty()) {
            op = (Operators)stack.peek().getValue();
            if (!op.equals(Operators.OB) && !op.equals(Operators.CB)) {
                result.add(stack.pop());
            }
        }

        return new Expression(result);
    }
    
    private void parseVar(ArrayList<IStatement> block) {
        Token current = this.tokens.get(currentToken);
        String name = ((VariableToken)current).value();
        currentToken += 1;
        current = this.tokens.get(currentToken);
        if (isOperator(current)) {
            Operators type = ((OperatorToken)current).value();
            if (type != Operators.EQ) {
                throw new RuntimeException();
            }
            //Equal so we want to move to the next token
            currentToken++;
            current = this.tokens.get(currentToken);
            if (isFunction(current)) {
                block.add(new AssignmentStatement(new Variable(name, null), new Read()));
            } else {
                IExpression expr = this.parseExpression(Operators.SCL);
                block.add(new AssignmentStatement(new Variable(name, null), expr));
            }
        } else {
            throw new RuntimeException("Invalid syntax.");
        }
    }
    
    private boolean isOperator(Token token) {
        return token.getClass().toString().indexOf("Operator") >= 0;
    }
    
    private boolean isVar(Token token) {
        return token.getClass().toString().indexOf("Variable") >= 0;
    }
    
    private boolean isNumber(Token token) {
        return token.getClass().toString().indexOf("Number") >= 0;
    }
    
    private boolean isBoolean(Token token) {
        return token.getClass().toString().indexOf("Boolean") >= 0;
    }
    
    private boolean isString(Token token) {
        return token.getClass().toString().indexOf("String") >= 0;
    }
    
    private boolean isFunction(Token token) {
        return token.getClass().toString().indexOf("Function") >= 0;
    }
    
    private boolean isStatement(Token token) {
        return token.getClass().toString().indexOf("Statement") >= 0;
    }
}
