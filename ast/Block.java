package ast;

import java.util.List;
import environment.*;
/**
 * Executes a list of statements.
 *
 * @author Tarush Gupta
 * @version 10/25/25
 */
public class Block extends Statement
{
    private List<Statement> stmts;
    
    /**
     * Creates a new block.
     * @param s the list of statements
     */
    public Block(List<Statement> s)
    {
        stmts = s;
    }

    /**
     * Executes the block.
     * @param env the environment
     */
    public void exec(Environment env)
    {
        for (Statement s : stmts)
        {
            s.exec(env);
        }
    }
}
