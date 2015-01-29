package org.mgechev.elang.parser.expressions.symbols.builtinfunctions;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import org.mgechev.elang.parser.expressions.IExpression;
import org.mgechev.elang.parser.expressions.symbols.BooleanValue;
import org.mgechev.elang.parser.expressions.symbols.NumberValue;
import org.mgechev.elang.parser.expressions.symbols.Symbol;
import org.mgechev.elang.parser.expressions.symbols.Value;

public class Read extends Symbol implements IExpression {
    
    public Read() {
        super(null);
    }

    public Value evaluate() {
        try {
            InputStreamReader reader = new InputStreamReader(System.in);
            BufferedReader in = new BufferedReader(reader);
            String result = in.readLine();
            return this.getParse(result);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }
    
    private Value getParse(String str) {
        IExpression expr;
        if (str.matches("(true|false)")) {
            if (str.equals("true")) {
                return new BooleanValue(true);
            }
            return new BooleanValue(false);
        }
        return new NumberValue(Double.parseDouble(str));
    }
    
}
