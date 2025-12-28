import java.util.Scanner;

public class App {
    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(System.in); 
        // scanner for console input
        System.out.print("Please see README for intructions on use. Input problem here:  ");
        String input = scanner.nextLine();
        double answer = calculateSolution(input);
        
        System.out.println(answer);
        scanner.close();
    }

    public static String bracketsEvaluation(String input)
    {
        if (input.contains("(") && input.contains(")")) {
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
                        // found a full bracketed part
                        String inside = input.substring(lastOpen + 1, i);
                        // remove leading/trailing spaces inside the brackets
                        inside = inside.trim();
                        // recursively evaluate what's inside using calculateSolution
                        double evaluated = calculateSolution(inside);
                        // replace the bracketed part (including any spaces just inside the brackets) with the evaluated result
    
                        // expand lastOpen forward to include spaces after '('
                        int open = lastOpen;
                        while (open + 1 < input.length() && Character.isWhitespace(input.charAt(open + 1))) {
                            open++;
                        }
                        // expand i backward to include spaces before ')'
                        int close = i;
                        while (close - 1 >= 0 && Character.isWhitespace(input.charAt(close - 1))) {
                            close--;
                        }
    
                        // replace from lastOpen to i (inclusive), including inner spaces, with result
                        String newInput = input.substring(0, lastOpen) + evaluated + input.substring(i + 1);
                        // recursively process the new input (in case there are more brackets)
                        return bracketsEvaluation(newInput);
                    }
                }
            }
        }
        return input;
    }

    public static String exponentEvaluation(String input)
    {
        // pattern: number (with optional decimal), optional spaces, ^, optional spaces, number (with optional decimal)
        java.util.regex.Pattern pattern = java.util.regex.Pattern.compile(
            "\\s*([0-9]+(?:\\.[0-9]+)?)\\s*\\^\\s*([0-9]+(?:\\.[0-9]+)?)\\s*"
        );
        java.util.regex.Matcher matcher = pattern.matcher(input);
    
        // keep replacing exponents until there are no more left
        while (matcher.find()) {
            // capture groups for numbers
            String num1 = matcher.group(1);
            String num2 = matcher.group(2);
    
            double base = Double.parseDouble(num1);
            double exponent = Double.parseDouble(num2);
    
            double powerSolution = power(base, exponent);
    
            // replace the matched part with the solution
            input = input.substring(0, matcher.start()) + powerSolution + input.substring(matcher.end());
            // reset matcher since input has changed
            matcher = pattern.matcher(input);
        }
    
        return input;
    }

    public static String divisionEvaluation(String input)
    {
        // pattern: optional spaces, number, optional spaces, * or x (case-insensitive), optional spaces, number, optional spaces
        java.util.regex.Pattern pattern = java.util.regex.Pattern.compile(
            "\\s*([0-9]+(?:\\.[0-9]+)?)\\s*[/÷]\\s*([0-9]+(?:\\.[0-9]+)?)\\s*"
        );
        java.util.regex.Matcher matcher = pattern.matcher(input);
    
        if (matcher.find()) {
            // capture groups for numbers
            String num1 = matcher.group(1);
            String num2 = matcher.group(2);
    
            double dividend = Double.parseDouble(num1);
            double divisor = Double.parseDouble(num2);
            double quotient = 0.0;
            if (divisor != 0) {
                quotient = dividend / divisor; 
            } else {
                System.err.println("Error: Division by zero!");
                System.exit(1); 
            }
    
            // replace the matched part with the product
            String result = input.substring(0, matcher.start()) + quotient + input.substring(matcher.end());
    
            return result;
        }
    
        return input; // no match found
    }

    public static String multiplicationEvaluation(String input)
    {
        // pattern: optional spaces, number, optional spaces, * or x (case-insensitive), optional spaces, number, optional spaces
        java.util.regex.Pattern pattern = java.util.regex.Pattern.compile(
            "\\s*([0-9]+(?:\\.[0-9]+)?)\\s*[*xX]\\s*([0-9]+(?:\\.[0-9]+)?)\\s*"
        );
        java.util.regex.Matcher matcher = pattern.matcher(input);
    
        if (matcher.find()) {
            // capture groups for numbers
            String num1 = matcher.group(1);
            String num2 = matcher.group(2);
    
            double multiplicand = Double.parseDouble(num1);
            double multiplier = Double.parseDouble(num2);
            double product = multiplicand * multiplier; 
    
            // replace the matched part with the product
            String result = input.substring(0, matcher.start()) + product + input.substring(matcher.end());
    
            return result;
        }
    
        return input; // no match found
    }

    public static String additionEvaluation(String input) {
        // pattern: optional spaces, number, optional spaces, +, optional spaces, number, optional spaces
        // number: one or more digits, optional decimal part
        java.util.regex.Pattern pattern = java.util.regex.Pattern.compile(
            "\\s*([0-9]+(?:\\.[0-9]+)?)\\s*\\+\\s*([0-9]+(?:\\.[0-9]+)?)\\s*"
        );
        java.util.regex.Matcher matcher = pattern.matcher(input);
    
        if (matcher.find()) {
            // capture groups for numbers
            String num1 = matcher.group(1);
            String num2 = matcher.group(2);
    
            double addend1 = Double.parseDouble(num1);
            double addend2 = Double.parseDouble(num2);
            double sum = addend1 + addend2;
    
            // replace the matched part with the sum
            String result = input.substring(0, matcher.start()) + sum + input.substring(matcher.end());
    
            return result;
        }
    
        return input; // no match found
    }

    public static String subtractionEvaluation(String input)
    {
        // pattern: optional spaces, number, optional spaces, +, optional spaces, number, optional spaces
        // number: one or more digits, optional decimal part
        java.util.regex.Pattern pattern = java.util.regex.Pattern.compile(
            "\\s*([0-9]+(?:\\.[0-9]+)?)\\s*\\-\\s*([0-9]+(?:\\.[0-9]+)?)\\s*"
        );
        java.util.regex.Matcher matcher = pattern.matcher(input);
    
        if (matcher.find()) {
            // capture groups for numbers
            String num1 = matcher.group(1);
            String num2 = matcher.group(2);
    
            double minuend = Double.parseDouble(num1);
            double subtrahend = Double.parseDouble(num2);
            double difference = minuend - subtrahend;
    
            // replace the matched part with the sum
            String result = input.substring(0, matcher.start()) + difference + input.substring(matcher.end());
    
            return result;
        }
    
        return input; // no match found
    }

    public static double calculateSolution(String input)
    {
        // first the question is evaluated using bedmas. 
        // first step is to check for brackets.
        while ((input.contains("(") == true) && (input.contains(")") == true)) 
        {
            input = bracketsEvaluation(input);
        }

        //System.out.println(input);

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

        
        double answer = Double.parseDouble(input);
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