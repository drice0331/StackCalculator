package com.drice.stackcalculator;

public class Calculator {
    
    private static String expression;
    private static MathExpression mathexpression;
    private static String calcresult;
    public Calculator()
    {
        expression = "";
    }
    
    /**
     * Setter method for expression and infixExpression
     */     
    public void setExpression(String newExpression)
    {
        //exception will be thrown if newExpression is not a valid
        //infix expression
        mathexpression = new MathExpression(newExpression);
        expression = newExpression;
    }
        
    /**
     * 
     * @param line
     * @return 
     */
    public String checkBCCommand(String line) {
        
        String bcCheck;
        String firstQuoteMarkCheck;
        String secondQuoteMarkCheck;
        String expression;
        
        //5 is min length for a valid bc expression on command line (2 quotation
        //marks, bc, and a space
        if(line.length() >= 5) {
            bcCheck = line.substring(0, 3);
            
            //check for "bc " in first 3 characters of the line
            if(bcCheck.equals(Constants.BC_CHECK))
            {
                firstQuoteMarkCheck = line.substring(3, 4);
                secondQuoteMarkCheck = line.substring(line.length() - 1, 
                        line.length());
                
                //check for quotation marks as 4th charactor and last charactor
                //of line
                if(firstQuoteMarkCheck.equals(Constants.QUOTE_MARK_CHECK) && 
                      secondQuoteMarkCheck.equals(Constants.QUOTE_MARK_CHECK)) {
                    
                    /*expression to evaluate will be between 4th char (first
                    quotation mark) and last char (last quotation mark)*/
                    expression = line.substring(4, line.length() - 1);
                    return expression;
                }
            }
        }
        
        return Constants.NON_BC_EXPRESSION;
    }
    
    /**
     * evaluates expression in calculator
     * @return the result of the evaluation
     */            
    public String evaluate()
    {
        if(expression == "")
        {
          throw new IllegalArgumentException("No input for infix evaluation");
        }        
        MathExpressionEvaluator eval = new MathExpressionEvaluator();
        mathexpression.accept(eval);
        return eval.getResult();
    } 
}
