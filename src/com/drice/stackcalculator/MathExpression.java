package com.drice.stackcalculator;

/**
 * MathExpression - class that implements MathElement. Represents a full mathExpression 
 * expression
 * @author drice
 */
public class MathExpression implements MathElement
{

    private MathElement [] elements;
    private String [] elementsAsString;
    
    public MathExpression(String mathExpression)
    {
        //gets number of operators and operands of expression, setting up size
        //of MathElement and String arrays of parts of this expression
        elementsAsString = new String [countElements(mathExpression)];
        String elem;
        elements = new MathElement [elementsAsString.length];
        
        int nextindex;
        int index = 0;

        //allows mathExpression expression to have decimal points through checking if the
        //next string index to be checked is a decimal point, if it is and the
        //previous index was a decimal point, an exception is thrown        
        
        for(int a = 0; a < mathExpression.length(); a++)
        {
            if(isOperand(mathExpression.substring(a,a+1)))
            {
                //while loop and nextindex are for instances when operand is
                //number with more than one digit
                nextindex = a+1;
               while((nextindex+1 <= mathExpression.length()) && 
                    isOperand(mathExpression.substring(nextindex, nextindex+1)))
                {
                    nextindex += 1;
                }
               elem = String.valueOf(mathExpression.substring(a, nextindex));               
               
               elements[index] = new Operand(elem);
               elementsAsString[index] = elem;
               a = nextindex-1;     
               
            }
            else if(isOperator(mathExpression.substring(a,a+1)))
            {
                elem = String.valueOf(mathExpression.substring(a,a+1));
                elements[index] = new Operator(elem);
                elementsAsString[index] = elem;
               
            }
            else
            {
                throw new IllegalArgumentException("Incompatible charactor in "
                        + "mathExpression expression");
            }
            index++;
        }
    }
    
     /**
     * Tests whether a string from expression is an operator
     * @param op
     * @return true if op is an operator
     */      
    public static boolean isOperator(String op)
    {
        if(op.equals("(") || op.equals(")") || op.equals("^") || op.equals("*") || 
           op.equals("/") || op.equals("-") || op.equals("+"))
        {
            return true;
        }
        return false;
    }
    
     /**
     * Tests whether a string from expression is an operand by testing if it's
     * a number
     * @param str
     * @return true if str is an operand
     */     
    public static boolean isOperand(String str)  
    {  
         try  
         {  
            Integer.parseInt(str);  
         }  
         catch(NumberFormatException nfe)  
         {  
             return false;  
         }  
         return true;  
    }
     /**
     * Getter method for array of MathElements in this mathExpression expression, in
     * string value
     * @return elementAsString
     */ 
    public String [] getInfixArrayString()
    {
        return elementsAsString;
    }
     /**
     * Getter method for array of MathElements in this mathExpression expression
     * @return elements
     */     
    public MathElement [] getInfixArrayMathElement()
    {
        return elements;
    }
     /**
     * Getter method for this mathExpression expression as a full string
     * @return mathExpression expression as string
     */     
    public String getMathExpression()
    {
       String exp = "";
       for(String elem : elementsAsString)
       {
           exp = exp.concat(elem);
       }
       return exp;
    }
     /**
     * Gets number of calcElements in aa mathExpression expression (as a string)
     * @param mathExpression
     * @return number of MathElements in mathExpression
     */       
    public static int countElements(String mathExpression)
    {
        int nextindex;
        int count = 0;
        
        for(int a = 0; a < mathExpression.length(); a++)
        {
            
            if(isOperand(mathExpression.substring(a,a+1)))
            {
               nextindex = a+1;
               while((nextindex < mathExpression.length()) && 
                  (isOperand(mathExpression.substring(nextindex, nextindex+1))))
                {
                    nextindex += 1;
                }
               a = nextindex-1;
               
            }
            count++;
        }
        return count;
    }
    
    @Override
    public void accept(MathElementVisitor visitor) 
    {
       for(MathElement elem : elements)
       {
           elem.accept(visitor);
       }        
        visitor.visit(this);
    }

}

