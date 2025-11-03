package ast;

import environment.*;
/**
 * Writes a value.
 *
 * @author Tarush Gupta
 * @version 10/25/25
 */
public class Writeln extends Statement
{
    private Expression exp;

    /**
     * Makes a Writeln statement.
     * @param exp the expression
     */
    public Writeln(Expression exp)
    {
        this.exp = exp;
    }
    
    /**
     * Executes the writeln.
     * @param env the environment
     */
    public void exec(Environment env)
    {
        System.out.println(exp.eval(env));
    }
}
