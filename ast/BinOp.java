package ast;

import environment.*;
/**
 * Does a binary operation.
 *
 * @author Tarush Gupta
 * @version 10/25/25
 */
public class BinOp extends Expression
{
    private String op;
    private Expression exp1;
    private Expression exp2;
    
    /**
     * Creates a new binary operation.
     * @param o the operator
     * @param one the first expression
     * @param two the second expression
     */
    public BinOp(String o, Expression one, Expression two)
    {
        op = o;
        exp1 = one;
        exp2 = two;
    }
    
    /**
     * Evaluates the binary operation.
     * @param env the environment
     * @return the result
     */
    public int eval(Environment env)
    {
        int val1 = exp1.eval(env);
        int val2 = exp2.eval(env);
        
        if (op.equals("+"))
        {
            return val1 + val2;
        }
        if (op.equals("-"))
        {
            return val1 - val2;
        }
        if (op.equals("*"))
        {
            return val1 * val2;
        }
        if (op.equals("/"))
        {
            return val1 / val2;
        }
        throw new RuntimeException("Unexpected operator");
    }
}
