package ast;

import scanner.*;
import parser.*;
import environment.*;
/**
 * Executes a statement.
 *
 * @author Tarush Gupta
 * @version 10/25/25
 */
public abstract class Statement
{
    /**
     * Executes the statement.
     * @param env the environment
     */
    public abstract void exec(Environment env);
}
