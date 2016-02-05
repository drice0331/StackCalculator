package com.drice.stackcalculator;

/**
 * MathElementVisitor - interface describing visitor classes which perform a 
 * certain function on an infix expression as well as the operands and operators
 * within that expression
 * @author drice
 */
public interface MathElementVisitor {
     /**
     * visits a operand that the MathElementVisitor will use
     * @param operand
     */      
    public void visit(Operand operand);
     /**
     * visits a operator that the MathElementVisitor will use
     * @param operator
     */          
    public void visit(Operator operator); 
     /**
     * visits an infix expression that the MathElementVisitor uses for it's
     * specific function (which eventually calls into use the other visit
     * methods
     * @param infix
     */          
    public void visit(MathExpression infix);    
}
