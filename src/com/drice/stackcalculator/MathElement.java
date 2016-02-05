package com.drice.stackcalculator;

/**
 * MathElement - interface describing elements used in a bc expressions
 * with method accept, which uses the given MathElement for the
 * visitor in the parameter of the accept method
 * @author drice
 */
public interface MathElement {
    
         /**
     * Accepts a visitor which uses this element
     * @param visitor
     */    
   public void accept(MathElementVisitor visitor); 
}
