package com.drice.stackcalculator;

/**
 * Operator - class that implements MathElement. Represents operator of an 
 * infix expression
 * @author drice
 */
public class Operator implements MathElement
{
    private String operator;
    public Operator(String op)
    {
        if(op.equals("(") || op.equals(")") || op.equals("*") || 
           op.equals("/") || op.equals("-") || op.equals("+")
           || op.equals("^"))
        {
            operator = op;
        }
        else
        {
            throw new IllegalArgumentException("not a valid operator");
        }
    }

     /**
     * Getter method for this operator's value as a string
     * @return operator
     */         
    public String getOperator()
    {
        return operator;
    }
    
    @Override    
    public void accept(MathElementVisitor visitor) 
    {
        visitor.visit(this);
    }
    
}

