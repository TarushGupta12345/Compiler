package ast;

import scanner.*;
import parser.*;
import environment.*;
import emitter.*;
/**
 * Evaluates an expression.
 *
 * @author Tarush Gupta
 * @version 10/25/25
 */
public abstract class Expression
{
    /**
     * Evaluates the expression.
     * @param env the environment
     * @return the result
     */
    public abstract int eval(Environment env);
    
    /**
     * Compiles.
     * @param e the emitter
     */
    public abstract void compile(Emitter e);
}
