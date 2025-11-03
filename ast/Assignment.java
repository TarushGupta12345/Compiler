package ast;

import environment.*;
/**
 * Assigns a value to a variable.
 *
 * @author Tarush Gupta
 * @version 10/25/25
 */
public class Assignment extends Statement
{
    private String var;
    private Expression exp;

    /**
     * Creates a new assignment.
     * @param v the variable
     * @param e the expression
     */
    public Assignment(String v, Expression e)
    {
        var = v;
        exp = e;
    }
    
    /**
     * Executes the assignment.
     * @param env the environment
     */
    public void exec(Environment env)
    {
        env.setVariable(var, exp.eval(env));
    }
}
