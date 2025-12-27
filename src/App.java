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
            // brackets will be evaluted by counting. every ( braket adds one to the count and every ) bracket subtracts 1 
            // from the count. when the count is 0 the brackets are evaluated. then calculate solution is called recursively until all brackets are evaluated. 
            
            int lastOpen = -1;
            int count = 0;
            for (int i = 0; i < input.length(); i++) {
                if (input.charAt(i) == '(') {
                    if (count == 0) {
                        lastOpen = i; // mark start of new innermost bracket
                    }
                    count++;
                } else if (input.charAt(i) == ')') {
                    count--;
                    if (count == 0 && lastOpen != -1) {
                        // Found a full bracketed part
                        String inside = input.substring(lastOpen + 1, i);
                        // Recursively evaluate what's inside using calculateSolution
                        double evaluated = calculateSolution(inside);
                        // Replace the bracketed part with the evaluated result
                        String newInput = input.substring(0, lastOpen) + evaluated + input.substring(i + 1);
                        // Recursively process the new input (in case there are more brackets)
                        return bracketsEvaluation(newInput);
                    }
                }
            }
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