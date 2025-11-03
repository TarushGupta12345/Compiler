package environment;

import java.util.Map;
import java.util.HashMap;
import ast.*;
/**
 * Instantiates an environment.
 *
 * @author Tarush Gupta
 * @version 10/25/25
 */
public class Environment
{
    private Map<String, Integer> vars;
    private Map<String, ProcedureDeclaration> procedures;
    private Environment parent;
    
    /**
     * Creates a new global environment.
     */
    public Environment()
    {
        this.vars = new HashMap<>();
        this.procedures = new HashMap<>();
        this.parent = null;
    }
    
    /**
     * Creates a new environment with a parent.
     * @param parent the parent environment
     */
    public Environment(Environment parent)
    {
        this.vars = new HashMap<>();
        this.procedures = new HashMap<>();
        this.parent = parent;
    }
    
    /**
     * Gets the global (root) environment.
     * @return the global environment
     */
    public Environment getGlobal()
    {
        Environment current = this;
        while (current.parent != null)
        {
            current = current.parent;
        }
        return current;
    }
    
    /**
     * Declares a variable in this environment
     * @param variable the variable 
     * @param value the value
     */
    public void declareVariable(String variable, int value)
    {
        vars.put(variable, value);
    }
    
    /**
     * Sets a variable. If the variable already exists (in current or parents), 
     * set it there. If not, creates a new one and sets it here.
     * @param variable the variable
     * @param value the value
     */
    public void setVariable(String variable, int value)
    {
        if (vars.containsKey(variable))
        {
            vars.put(variable, value);
            return;
        }
        Environment current = parent;
        while (current != null)
        {
            if (current.vars.containsKey(variable))
            {
                current.vars.put(variable, value);
                return;
            }
            current = current.parent;
        }
        vars.put(variable, value);
    }
    
    /**
     * Gets a variable. Checks current, then parents.
     * @param variable the variable name
     * @return the value
     */
    public int getVariable(String variable)
    {
        if (vars.containsKey(variable))
        {
            return vars.get(variable);
        }
        Environment current = parent;
        while (current != null)
        {
            if (current.vars.containsKey(variable))
            {
                return current.vars.get(variable);
            }
            current = current.parent;
        }
        return 0;
    }
    
    /**
     * Sets a procedure in global env.
     * @param name the name
     * @param procedure the procedure declaration
     */
    public void setProcedure(String name, ProcedureDeclaration procedure)
    {
        getGlobal().procedures.put(name, procedure);
    }
    
    /**
     * Gets a procedure from the global env.
     * @param name the name
     * @return the procedure declaration
     */
    public ProcedureDeclaration getProcedure(String name)
    {
        return getGlobal().procedures.get(name);
    }
}

