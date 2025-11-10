package ast;

import environment.*;
import emitter.*;

/**
 * Creates a condition.
 * 
 * @author Tarush Gupta
 * @version 1/12/25
 */
public class Condition
{
    private Expression left;
    private String relop;
    private Expression right;
    
    /**
     * Makes a condition.
     * @param left the left expression
     * @param relop =, <>, <, >, <=, >=
     * @param right the right expression
     */
    public Condition(Expression left, String relop, Expression right)
    {
        this.left = left;
        this.relop = relop;
        this.right = right;
    }
    
    /**
     * Evaluates the condition.
     * @param env the environment
     * @return true if satisfied, else false
     */
    public boolean eval(Environment env)
    {
        int leftVal = left.eval(env);
        int rightVal = right.eval(env);
        
        //i want to use a switch case here, but checkstyle is saying no. so i made ifs :(
        
        if (relop.equals("="))
        {
            return leftVal == rightVal;
        }
        else if (relop.equals("<>"))
        {
            return leftVal != rightVal;
        }
        else if (relop.equals("<"))
        {
            return leftVal < rightVal;
        }
        else if (relop.equals(">"))
        {
            return leftVal > rightVal;
        }
        else if (relop.equals("<="))
        {
            return leftVal <= rightVal;
        }
        else if (relop.equals(">="))
        {
            return leftVal >= rightVal;
        }
        throw new RuntimeException("don't know what ts is: " + relop);
        
        /**
         * switch (relop)
        {
            case "=":
                return leftVal == rightVal;
            case "<>":
                return leftVal != rightVal;
            case "<":
                return leftVal < rightVal;
            case ">":
                return leftVal > rightVal;
            case "<=":
                return leftVal <= rightVal;
            case ">=":
                return leftVal >= rightVal;
            default:
                throw new RuntimeException("don't know what ts is: " + relop);
        }
         */
        
    }
    
    /**
     * Compiles. 
     * @param e the emitter
     * @param targetLabel branch here if false
     */
    public void compile(Emitter e, String targetLabel)
    {
        left.compile(e);
        e.emitPush("$v0");
        right.compile(e);
        e.emitPop("$t0");
        
        if (relop.equals("="))
        {
            e.emit("bne $t0 $v0 " + targetLabel + "\t#branch if not equal");
        }
        else if (relop.equals("<>"))
        {
            e.emit("beq $t0 $v0 " + targetLabel + "\t#branch if equal");
        }
        else if (relop.equals("<"))
        {
            e.emit("bge $t0 $v0 " + targetLabel + "\t#branch if >=");
        }
        else if (relop.equals(">"))
        {
            e.emit("ble $t0 $v0 " + targetLabel + "\t#branch if <=");
        }
        else if (relop.equals("<="))
        {
            e.emit("bgt $t0 $v0 " + targetLabel + "\t#branch if >");
        }
        else if (relop.equals(">="))
        {
            e.emit("blt $t0 $v0 " + targetLabel + "\t#branch if <");
        }
        else
        {
            throw new RuntimeException("Unknown relop: " + relop);
        }
    }
}