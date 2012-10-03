package org.mgechev.edulang.parser;

import java.util.ArrayList;
import java.util.Stack;

import org.mgechev.edulang.common.Operators;
import org.mgechev.edulang.common.Program;
import org.mgechev.edulang.parser.expressions.Expression;
import org.mgechev.edulang.parser.expressions.IExpression;
import org.mgechev.edulang.parser.expressions.symbols.BooleanValue;
import org.mgechev.edulang.parser.expressions.symbols.Evaluator;
import org.mgechev.edulang.parser.expressions.symbols.NumberValue;
import org.mgechev.edulang.parser.expressions.symbols.Operator;
import org.mgechev.edulang.parser.expressions.symbols.StringValue;
import org.mgechev.edulang.parser.expressions.symbols.Symbol;
import org.mgechev.edulang.parser.expressions.symbols.Variable;
import org.mgechev.edulang.parser.expressions.symbols.builtinfunctions.Abs;
import org.mgechev.edulang.parser.expressions.symbols.builtinfunctions.Ceil;
import org.mgechev.edulang.parser.expressions.symbols.builtinfunctions.Cos;
import org.mgechev.edulang.parser.expressions.symbols.builtinfunctions.Cotan;
import org.mgechev.edulang.parser.expressions.symbols.builtinfunctions.Floor;
import org.mgechev.edulang.parser.expressions.symbols.builtinfunctions.Log;
import org.mgechev.edulang.parser.expressions.symbols.builtinfunctions.Pow;
import org.mgechev.edulang.parser.expressions.symbols.builtinfunctions.Print;
import org.mgechev.edulang.parser.expressions.symbols.builtinfunctions.Read;
import org.mgechev.edulang.parser.expressions.symbols.builtinfunctions.Round;
import org.mgechev.edulang.parser.expressions.symbols.builtinfunctions.Sin;
import org.mgechev.edulang.parser.expressions.symbols.builtinfunctions.Tan;
import org.mgechev.edulang.parser.expressions.symbols.builtinoperators.And;
import org.mgechev.edulang.parser.expressions.symbols.builtinoperators.Colon;
import org.mgechev.edulang.parser.expressions.symbols.builtinoperators.Comma;
import org.mgechev.edulang.parser.expressions.symbols.builtinoperators.Division;
import org.mgechev.edulang.parser.expressions.symbols.builtinoperators.Equals;
import org.mgechev.edulang.parser.expressions.symbols.builtinoperators.GreaterThan;
import org.mgechev.edulang.parser.expressions.symbols.builtinoperators.GreaterThanOrEqual;
import org.mgechev.edulang.parser.expressions.symbols.builtinoperators.IsEqual;
import org.mgechev.edulang.parser.expressions.symbols.builtinoperators.LeftParenthesis;
import org.mgechev.edulang.parser.expressions.symbols.builtinoperators.LessThan;
import org.mgechev.edulang.parser.expressions.symbols.builtinoperators.LessThanOrEqual;
import org.mgechev.edulang.parser.expressions.symbols.builtinoperators.Minus;
import org.mgechev.edulang.parser.expressions.symbols.builtinoperators.Modulus;
import org.mgechev.edulang.parser.expressions.symbols.builtinoperators.Multiplication;
import org.mgechev.edulang.parser.expressions.symbols.builtinoperators.Not;
import org.mgechev.edulang.parser.expressions.symbols.builtinoperators.NotEquals;
import org.mgechev.edulang.parser.expressions.symbols.builtinoperators.Or;
import org.mgechev.edulang.parser.expressions.symbols.builtinoperators.Plus;
import org.mgechev.edulang.parser.expressions.symbols.builtinoperators.Quote;
import org.mgechev.edulang.parser.expressions.symbols.builtinoperators.RightParenthesis;
import org.mgechev.edulang.parser.expressions.symbols.builtinoperators.Semicolons;
import org.mgechev.edulang.parser.expressions.symbols.builtinoperators.UnaryMinus;
import org.mgechev.edulang.parser.expressions.symbols.builtinoperators.UnaryPlus;
import org.mgechev.edulang.parser.expressions.symbols.functions.CustomFunction;
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
        KeyWordToken statement = (KeyWordToken)this.tokens.get(currentToken);
        if (statement.value().equals("while")) {
            this.parseWhile(block);
        } else if (statement.value().equals("if")) {
            this.parseIf(block);
        } else if (statement.value().equals("return")) {
            this.parseReturn(block);
        } else if (statement.value().equals("def")) {
            this.parseFunctionStatement();
        }
    }
    
    private void parseReturn(ArrayList<IStatement> block) {
        currentToken += 1;
        block.add(new ReturnStatement(this.parseExpression(Operators.SCL)));
    }
    
    private ArrayList<Variable> getFunctionArguments() {
        ArrayList<Variable> funcArgs = new ArrayList<Variable>();
        
        Token current = this.tokens.get(currentToken);
        
        while (!current.value().equals(Operators.CB)) {
            currentToken += 1;
            current = this.tokens.get(currentToken);

            if (current instanceof NameToken) {
                funcArgs.add(new Variable(current.value().toString()));
            }
        }
        return funcArgs;
    }
    
    private ArrayList<IStatement> getFunctionStatements() {
        ArrayList<IStatement> statements = new ArrayList<IStatement>();
        int tokensCount = this.tokens.size();
        Token current = this.tokens.get(currentToken);
        //Here I'm still on the closing parenthesis so the incrementation is first
        while (currentToken < tokensCount && !current.value().equals("enddef")) {
            currentToken += 1;
            current = this.tokens.get(currentToken);
            
            this.parseBlockLine(statements);
        }
        return statements;
    }
    
    private void parseFunctionStatement() {
        currentToken += 1;
        String name = this.tokens.get(currentToken).value().toString();
        
        CustomFunction function = new CustomFunction();
        //Adding the function instantly because of the recursion
        Program.Get().addFunction(name, function);
        currentToken += 1;
        Token current = this.tokens.get(currentToken);
        if (!current.value().equals(Operators.OB)) {
            throw new RuntimeException("The function is not defined currectly!");
        }
        
        ArrayList<Variable> funcArgs = this.getFunctionArguments();
        ArrayList<IStatement> statements = this.getFunctionStatements();

        function.setArguments(funcArgs);
        function.setStatements(statements);
      /*  current = this.tokens.get(currentToken);
        if (!current.value().equals("return")) {
            throw new RuntimeException("The function is not defined correctly!");
        }
        //Incrementing because the current token is the return statement.
        currentToken += 1;
        
        IExpression returnValue = this.parseExpression(Operators.SCL);
        */
        
        currentToken += 1;
        current = this.tokens.get(currentToken);
        if (!current.value().equals(Operators.SCL)) {
            throw new RuntimeException("After the end of the function definition you must set semicolons.");
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
        ArrayList<IStatement> elseStatements = new ArrayList<IStatement>();
        boolean conditionParsed = false;
        boolean parseElse = false;
        while (!current.value().equals("endif")) {
            currentToken += 1;
            current = this.tokens.get(currentToken);

            if (!conditionParsed) {
                condition = parseExpression(Operators.CLN);
                currentToken += 1;
                current = this.tokens.get(currentToken);
                conditionParsed = true;
            }
            
            if (current.value().equals("else")) {
                currentToken += 2; //for the colons
                parseElse = true;
            }

            if (parseElse) {
                this.parseBlockLine(elseStatements);
            } else {
                this.parseBlockLine(statements);
            }
        }
        block.add(new IfStatement(condition, statements, elseStatements));
    }
    
    private void parseFunction(ArrayList<IStatement> block) {
        String funcName = ((FunctionToken)this.tokens.get(currentToken)).value();
        if (funcName.equals("print")) {
            currentToken += 1;
            block.add(new Statement(new Print(this.parseExpression(Operators.SCL))));
        }
    }
    
    private int getOperatorPriority(Symbol operator) {
        if (this.symbolIsOperator(operator)) {
            if (operator instanceof Plus || operator instanceof Minus || operator instanceof Or || operator instanceof And) {
                return 2;
            } else {
                return 3;
            }   
        }
        return 4;
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
        Symbol currentSym = null;
        
        while (!current.value().equals(statementEnd)) {
            currentSym = this.convertToken(current, currentSym);
            if (this.isVar(current) || this.isNumber(current) || this.isBoolean(current)) {
                result.add(currentSym);
            } else if (this.isFunction(current)) {
                stack.push(currentSym);
            } else if (this.isComma(current)) {
                while (!(stack.peek() instanceof LeftParenthesis)) {
                    if (stack.isEmpty()) {
                        throw new RuntimeException("Parenthesis error!");
                    }
                    result.add(stack.pop());
                }
            } else if (this.isOperator(current) && !current.value().equals(Operators.OB) && !current.value().equals(Operators.CB)) {
                while (!stack.isEmpty() && this.symbolIsOperator(stack.peek()) && !(stack.peek() instanceof LeftParenthesis) && this.getOperatorPriority(currentSym) <= this.getOperatorPriority(stack.peek())) {
                    if (stack.peek() instanceof LeftParenthesis) {
                        stack.pop();
                    } else {
                        result.add(stack.pop());
                    }
                }
                stack.push(currentSym);
            } else if (currentSym instanceof LeftParenthesis) {
                stack.push(currentSym);
            } else if (currentSym instanceof RightParenthesis) {
                while (!stack.isEmpty() && this.symbolIsOperator(stack.peek()) && !(stack.peek() instanceof LeftParenthesis)) {
                    result.add(stack.pop());
                }
                if (!stack.isEmpty() && stack.peek() instanceof LeftParenthesis) {
                    stack.pop();
                }
                if (!stack.isEmpty() && stack.peek() instanceof Evaluator && 
                        !(stack.peek() instanceof LeftParenthesis || stack.peek() instanceof RightParenthesis)) {
                    result.add(stack.pop());
                }
            }
            currentToken += 1;
            current = this.tokens.get(currentToken);
        }
        
 /* 
        Operators op; */
        while (!stack.empty()) {
            //op = (Operators)stack.peek().getValue();
            //if (!op.equals(Operators.OB) && !op.equals(Operators.CB)) {
                result.add(stack.pop());
            //}
        }

        return new Expression(result);
    }
       
    private void parseVar(ArrayList<IStatement> block) {
        Token current = this.tokens.get(currentToken);
        String name = ((NameToken)current).value();
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
            if (isFunction(current) && current.value().equals("read")) {
                block.add(new AssignmentStatement(new Variable(name, null), new Read()));
            } else {
                IExpression expr = this.parseExpression(Operators.SCL);
                block.add(new AssignmentStatement(new Variable(name, null), expr));
            }
        } else {
            throw new RuntimeException("Invalid syntax.");
        }
    }
    
    private Symbol convertToken(Token token, Symbol lastSymbol) {
        if (this.isOperator(token)) {
            if (lastSymbol == null) {
                return this.getOperator((Operators)token.value(), true);
            } else {
                if (this.symbolIsOperator(lastSymbol) && !(lastSymbol instanceof RightParenthesis)) {
                    return this.getOperator((Operators)token.value(), true);
                }
            }
        }
        return this.convertToken(token);
    }
    
    private Symbol convertToken(Token token) {
        Symbol result = null;
        String name = token.value().toString();
        if (isVar(token) && !Program.Get().functionExists(name)) {
        //    if (Program.Get().variableExists(varName)) {
                return new Variable(name);
        //    } else {
        //        throw new RuntimeException("The variable " + varName + "is not declared.");
        //    }
        } else if (isNumber(token)) {
            return new NumberValue(((NumberToken)token).value());
        } else if (isBoolean(token)) {
            return new BooleanValue(((BooleanToken)token).value());
        } else if (isOperator(token)) {
            return this.getOperator(((OperatorToken)token).value());
        } else if (isFunction(token)) {
            return this.getFunction(name);
        } else {
            throw new RuntimeException("Unknown token.");
        }
    }
    
    private Symbol getOperator(Operators operator) {
        return this.getOperator(operator, false);
    }
    
    private Symbol getOperator(Operators operator, boolean unary) {
        switch (operator) {
        case PLU:
            if (unary) {
                return new UnaryPlus();
            } else {
                return new Plus();    
            }
        case MIN:
            if (unary) {
                return new UnaryMinus();
            } else {
                return new Minus();
            }
        case OB:
            return new LeftParenthesis();
        case AND:
            return new And();
        case CB:
            return new RightParenthesis();
        case CLN:
            return new Colon();
        case CM:
            return new Comma();
        case DIV:
            return new Division();
        case EQ:
            return new Equals();
        case EQL:
            return new IsEqual();
        case GT:
            return new GreaterThan();
        case GTE:
            return new GreaterThanOrEqual();
        case LT:
            return new LessThan();
        case LTE:
            return new LessThanOrEqual();
        case MOD:
            return new Modulus();
        case MUL:
            return new Multiplication();
        case NEQ:
            return new NotEquals();
        case NOT:
            return new Not();
        case OR:
            return new Or();
        case QT:
            return new Quote();
        case SCL:
            return new Semicolons();
            default:
                throw new RuntimeException("Unknown operator.");
        }
    }
    
    private boolean symbolIsOperator(Symbol symbol) {
        if (symbol instanceof Operator) {
            return true;
        }
        return false;
    }
    
    private Symbol getFunction(String func) {
        if (func.equals("cos")) {
            return new Cos();
        } else if (func.equals("sin")) {
            return new Sin();
        } else if (func.equals("tan")) {
            return new Tan();
        } else if (func.equals("cotan")) {
            return new Cotan();
        } else if (func.equals("pow")) {
            return new Pow();
        } else if (func.equals("log")) {
            return new Log();
        } else if (func.equals("abs")) {
            return new Abs();
        } else if (func.equals("ceil")) {
            return new Ceil();
        } else if (func.equals("floor")) {
            return new Floor();
        } else if (func.equals("round")) {
            return new Round();
        } else if (func.equals("read")) {
            return new Read();
        } else {
            return Program.Get().getFunction(func);
        }
    }
    
    private boolean isComma(Token token) {
        if (this.isOperator(token)) {
            if (token.value().equals(Operators.CM)) {
                return true;
            }
        }
        return false;
    }
    
    private boolean isOperator(Token token) {
        if (token instanceof OperatorToken) {
            return true;
        }
        return false;
    }
    
    private boolean isVar(Token token) {
        if (token instanceof NameToken && !Program.Get().functionExists(token.value().toString())) {
            return true;
        }
        return false;
    }
    
    private boolean isNumber(Token token) {
        if (token instanceof NumberToken) {
            return true;
        }
        return false;
    }
    
    private boolean isBoolean(Token token) {
        if (token instanceof BooleanToken) {
            return true;
        }
        return false;
    }
    
    private boolean isString(Token token) {
        if (token instanceof StringToken) {
            return true;
        }
        return false;
    }
    
    private boolean isFunction(Token token) {
        if ((token instanceof FunctionToken) || Program.Get().functionExists(token.value().toString())) {
            return true;
        }
        return false;
    }
    
    private boolean isStatement(Token token) {
        if (token instanceof KeyWordToken) {
            return true;
        }
        return false;
    }
}
