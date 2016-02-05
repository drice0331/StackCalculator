package com.drice.stackcalculator;


/**
 * Operand - class that implements MathElement. Represents operands of an 
 * infix expression
 * @author drice
 */
public class Operand implements MathElement
{

    private String operand;
    public Operand(String op)
    {
        if(!isNumeric(op))
        {
            throw new IllegalArgumentException("Not a legal Operand");
        }
        operand = op;
    }
    
    
     /**
     * Tests whether or not the string is numeric for when Operand is 
     * constructed with it
     * @param str
     * @return true if str is a number
     */    
    public static boolean isNumeric(String str)  
    {  
         try  
         {  
            Double.parseDouble(str);  
         }  
         catch(NumberFormatException nfe)  
         {  
             return false;  
         }  
         return true;  
    }
    
     /**
     * Getter method for this operand's value as a string
     * @return operand
     */     
    public String getOperand()
    {
        return operand;
    }
    
    @Override
    public void accept(MathElementVisitor visitor) 
    {
        visitor.visit(this);
    }

    
}
