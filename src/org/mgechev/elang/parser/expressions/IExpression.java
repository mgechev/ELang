package org.mgechev.elang.parser.expressions;

import org.mgechev.elang.parser.expressions.symbols.Value;

public interface IExpression {

    Value evaluate();
   
}
