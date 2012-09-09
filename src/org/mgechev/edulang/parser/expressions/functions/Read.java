package org.mgechev.edulang.parser.expressions.functions;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import org.mgechev.edulang.parser.expressions.BooleanValue;
import org.mgechev.edulang.parser.expressions.IExpression;
import org.mgechev.edulang.parser.expressions.NumberValue;
import org.mgechev.edulang.parser.expressions.Symbol;
import org.mgechev.edulang.parser.expressions.Value;

public class Read extends Symbol implements IExpression {
    
    public Read() {
        super(null);
    }

    public Value evaluate() {
        try {
            InputStreamReader reader = new InputStreamReader(System.in);
            BufferedReader in = new BufferedReader(reader);
            String result = in.readLine();
            in.close();
            reader.close();
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
