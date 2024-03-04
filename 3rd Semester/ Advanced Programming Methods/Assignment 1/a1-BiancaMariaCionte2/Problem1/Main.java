package Problem1;

import java.math.BigInteger;

public class Main {
    public static void main(String[] args) {
        if (args.length == 0)
        {
            System.out.println("Please provide one or more integer numbers as command-line parameters.");
            return;
        }
        for (String arg : args)
        {
            int num = Integer.parseInt(arg);
            if (isPrime(num))
            {
                System.out.print(num + " ");
            }
        }
    }

    // Check if a number is prime
    public static boolean isPrime(int num) {
        if (num <= 1) {
            return false;
        }

        if (num <= 3) {
            return true;
        }

        if (num % 2 == 0 || num % 3 == 0) {
            return false;
        }

        for (int i = 5; i * i <= num; i += 6) {
            if (num % i == 0 || num % (i + 2) == 0) {
                return false;
            }
        }

        return true;
    }
}

//  print the maximum value from all the double numbers given as command-line parameters.
class MaxDoubleValue {
    public static void main(String[] args) {
        if (args.length == 0) {
            System.out.println("Please provide one or more double numbers as command-line parameters.");
            return;
        }

        double max = Double.MIN_VALUE; // Initialize max with the smallest possible double value

        for (String arg : args) {
            try {
                double current = Double.parseDouble(arg);
                if (current > max) {
                    max = current;
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input: " + arg + " is not a valid double number.");
                return;
            }
        }

        System.out.println("Maximum value is: " + max);
    }
}


class GCDCalculator {
    public static void main(String[] args) {
        if (args.length < 2) {
            System.out.println("Please provide at least two integer numbers as command-line parameters.");
            return;
        }

        BigInteger gcd = new BigInteger(args[0]);

        try {
            for (int i = 1; i < args.length; i++) {
                BigInteger number = new BigInteger(args[i]);
                gcd = gcd.gcd(number);
            }

            System.out.println("Greatest Common Divisor (GCD) of the provided integers: " + gcd);
        } catch (NumberFormatException e) {
            System.out.println("Invalid input. Please provide valid integer numbers.");
        }
    }
}
