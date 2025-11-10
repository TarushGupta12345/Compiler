package ast;

import environment.*;
import emitter.*;

/**
 * Creates a FOR statement.
 * 
 * @author Tarush Gupta
 * @version 10/25/25
 */
public class For extends Statement
{
    private String varName;
    private Expression startExpr;
    private Expression endExpr;
    private Statement body;
    
    /**
     * Makes a For statement.
     * @param varName the variable name
     * @param startExpr starting expression
     * @param endExpr ending expression
     * @param body the stuff to do
     */
    public For(String varName, Expression startExpr, Expression endExpr, Statement body)
    {
        this.varName = varName;
        this.startExpr = startExpr;
        this.endExpr = endExpr;
        this.body = body;
    }
    
    /**
     * Executes the FOR statement.
     * @param env the environment
     */
    public void exec(Environment env)
    {
        int start = startExpr.eval(env);
        int end = endExpr.eval(env);
        
        for (int i = start; i <= end; i++)
        {
            env.setVariable(varName, i);
            body.exec(env);
        }
    }
    
    /**
     * Compiles.
     * @param e the emitter
     */
    public void compile(Emitter e)
    {
        //nothing for now lol
    }
}
