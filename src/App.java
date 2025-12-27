import java.util.Scanner;

public class App {
    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(System.in); 
        // scanner for console input
        System.out.print("Please see README for intructions on use. Input problem here:  ");
        String input = scanner.nextLine();
        
        //System.out.println(input);
        scanner.close();
    }

    public static String bracketsEvaluation(String input)
    {
        if ((input.contains("(") == true) && (input.contains(")") == true)) {
            // should it contain brackets the program should evaluate those brackets and repeat until the sets of
            // brackets are gone. each time a bracket set is evaluated it is replaced with the answer. 

        }
        return input;
    }

    public static String exponentEvaluation(String input)
    {
        if (input.contains("^") == true) {
            double base = 0;
            double exponent = 0;
            double powerSolution = power(base, exponent);
        }
        return input;
    }

    public static String divisionEvaluation(String input)
    {
        if ((input.contains("/") == true) || (input.contains("÷") == true)) {
            double dividend = 10;    
            double divisor = 2;      
            double quotient = 0.0;
            
            if (divisor != 0) {
                quotient = dividend / divisor;
            } else {
                System.out.println("Error: Division by zero!");
                // must break out of the function and return with an error. 
            }
        }

        return input;
    }

    public static String multiplicationEvaluation(String input)
    {
        if ((input.contains("x") == true) || (input.contains("*") == true)) {
            double multiplicand = 10;    
            double multiplier = 2;      
            double product = 0.0;
            product = multiplicand * multiplier;
        }

        return input;
    }

    public static String additionEvaluation(String input)
    {
        if (input.contains("+") == true) {
            double addend1 = 10;    
            double addend2 = 2;      
            double sum = 0.0;
            sum = addend1 + addend2;
        }

        return input;
    }

    public static String subtractionEvaluation(String input)
    {
        if (input.contains("-") == true) {
            double minuend = 10;    
            double subtrahend = 2;      
            double difference = 0.0;
            difference = minuend - subtrahend;
        }

        return input;
    }

    public static double calculateSolution(String input)
    {
        // first the question is evaluated using bedmas. 
        // first step is to check for brackets.
        while ((input.contains("(") == true) && (input.contains(")") == true)) 
        {
            input = bracketsEvaluation(input);
        }

        while (input.contains("^") == true)
        {
            input = exponentEvaluation(input);
        }

        while ((input.contains("/") == true) || (input.contains("÷") == true))
        {
            input = divisionEvaluation(input);
        }

        while ((input.contains("x") == true) || (input.contains("*") == true))
        {
            input = multiplicationEvaluation(input);
        }

        while (input.contains("+") == true) 
        {
            input = additionEvaluation(input);
        }

        while (input.contains("-") == true) 
        {
            input = subtractionEvaluation(input);
        }

        
        double answer = 0.0;
        return answer;
    }

    public static double power(double base, double exponent) {
        // base case: exponent is 0
        if (exponent == 0) {
            return 1;
        }
        // recursive case
        return base * power(base, exponent - 1);
    }
}