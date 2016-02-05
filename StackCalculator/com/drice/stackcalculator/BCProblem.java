package com.drice.stackcalculator;

public class BCProblem {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        Calculator calc = new Calculator();
        if(!args[0].equals(Constants.BC_CHECK) || (args.length != 2))
        {
            System.out.println(Constants.NON_BC_EXPRESSION);
            return;
        }
        
        String expression = args[1];
        calc.setExpression(expression);
        System.out.println(calc.evaluate());        
    }
	
}
