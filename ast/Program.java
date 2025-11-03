package ast;

import java.util.*;
import environment.*;
/**
 * Makes a program.
 *
 * @author Tarush Gupta
 * @version 10/25/25
 */
public class Program
{
    private List<ProcedureDeclaration> procedures;
    private Statement stmt;
    
    /**
     * Makes a program.
     * 
     * @param procedures the procedures
     * @param stmt the statement
     */
    public Program(List<ProcedureDeclaration> procedures, Statement stmt)
    {
        this.procedures = procedures;
        this.stmt = stmt;
    }
    
    /**
     * Executes.
     * @param env the environment
     */
    public void exec(Environment env)
    {
        for (ProcedureDeclaration procedure : procedures)
        {
            procedure.exec(env);
        }
        stmt.exec(env);
    }
}
