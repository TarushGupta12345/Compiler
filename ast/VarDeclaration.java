package ast;

import java.util.Map;
import java.util.HashMap;

/**
 * Makes a variable declaration.
 * 
 * @author Tarush Gupta
 * @version 11/9/25
 */
public class VarDeclaration
{
    private Map<String, Integer> variables;
    
    /**
     * Creates a new variable declaration.
     */
    public VarDeclaration()
    {
        variables = new HashMap<>();
    }
    
    /**
     * Adds a variable.
     * @param name the name
     */
    public void addVariable(String name)
    {
        variables.put(name, 0);
    }
    
    /**
     * Adds a variable with a value.
     * @param name the name
     * @param value the value
     */
    public void addVariable(String name, int value)
    {
        variables.put(name, value);
    }
    
    /**
     * Gets varibales.
     * @return map of names to values
     */
    public Map<String, Integer> getVariables()
    {
        return variables;
    }
}

