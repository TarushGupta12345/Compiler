package ast;

import environment.*;

/**
 * If statement.
 * 
 * @author Tarush Gupta
 * @version 10/25/25
 */
public class If extends Statement
{
    private Condition condition;
    private Statement thenStmt;
    
    /**
     * Makes an if statement.
     * @param condition the condition
     * @param thenStmt the stuff to do.
     */
    public If(Condition condition, Statement thenStmt)
    {
        this.condition = condition;
        this.thenStmt = thenStmt;
    }
    
    /**
     * Does the if statement.
     * @param env the environment 
     */
    public void exec(Environment env)
    {
        if (condition.eval(env))
        {
            thenStmt.exec(env);
        }
    }
}
