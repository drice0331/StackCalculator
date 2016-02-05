package com.drice.stackcalculator;

import java.util.Stack;

/**
 * MathExpressionEvaluator - class that implements MathElementVisitor. Evaluates an infix
 * expression using stacks
 * @author drice
 */
public class MathExpressionEvaluator implements MathElementVisitor
{
    private static Stack<String> operatorStack;
    private static Stack<String> operandStack;
    private static String result;
    private static String oprn;
    
    public MathExpressionEvaluator()
    {
        operatorStack = new Stack<String>();
        operandStack = new Stack<String>();
        result = "";
        oprn = "";
    }
    
    @Override
    public void visit(Operand operand) 
    {
        oprn = operand.getOperand();
        operandStack.push(oprn);
    }

    @Override
    public void visit(Operator operator) 
    {
        String optr = operator.getOperator();
        if(optr.equals("("))
        {
            operatorStack.push(optr);
        }
        else
        {
            if(optr.equals(")"))
            {
                while(!operatorStack.peek().equals("("))
                {
                    eval();
                }
                operatorStack.pop();
            }        
            else
            {
                while(!operatorStack.empty() && !higherPrecedence(optr))
                {
                    eval();
                }
                operatorStack.push(optr);
            }
        }
    }

    @Override
    public void visit(MathExpression infix) 
    {
        while(!operatorStack.empty())
        {
            eval();
        }
        result = operandStack.pop();
    }

     /**
     * Evaluates first 2 operands on operandStack with first operator off of 
     * operatorStack - used when operator visited doesn't have higher precedence
     * than operator on top of operatorStack (and when operatorStack isn't 
     * empty)
     */     
    private static void eval()
    {
        //note - may have to switch oprn1 and opr2 order
        int oprn1 = Integer.parseInt(operandStack.pop());
        int oprn2 = Integer.parseInt(operandStack.pop());
        String optr = operatorStack.pop();
        int evald;
        if(optr.equals("^")) {
        	evald = (int) Math.pow(oprn2, oprn1);
        	operandStack.push(String.valueOf(evald));
        }
        else if(optr.equals("/"))
        {
            evald = oprn2/oprn1;
            operandStack.push(String.valueOf(evald));
        }
        else if(optr.equals("*"))
        {
            evald = oprn2*oprn1;
            operandStack.push(String.valueOf(evald));            
        }
        else if(optr.equals("-"))
        {
            evald = oprn2-oprn1;
            operandStack.push(String.valueOf(evald));            
        }
        else if(optr.equals("+"))
        {
            evald = oprn2+oprn1;
            operandStack.push(String.valueOf(evald));            
        }
        else
        {
            throw new IllegalArgumentException("Invalid operator in eval");
        }
        
    }
     /**
     * Tests whether current operator visited has higher precedence over 
     * operator on top of the operatorStack
     * @param current
     * @return true if current has higher precedence than operator on top of
     * operatorStack
     */        
    public static boolean higherPrecedence(String current)
    {
        //Strings current and in stack are only one character
        char topOp = operatorStack.peek().charAt(0);
        char currOp = current.charAt(0);
        boolean result = false;

        switch (currOp){
        case '^': if (topOp == '^') result = false;
        		  else result = true;
        		  break;
        case '*':
        case '/': if (topOp == '*' || topOp == '/') result = false;
                  else if (topOp == '+' || topOp == '-') result = true;
                  else if (topOp == '^') result = false;
                  else if (topOp =='(') result = true;
                  break;
        case '+':
        case '-': if (topOp == '(') result = true;
                  else result = false;
        		  break;
        } // end switch

        return result;
    }      
     /**
     * Getter method for result of infix expression evaluation
     * @return result of infix evaluation, will return empty
     * string)
     */       
    public String getResult()
    {
        return result;
    }
}
