package controller;

import model.Monomial;
import model.Polynomial;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PolynomialParser {
    public static Polynomial getPolynomial(String polynomial) {
        Polynomial result = new Polynomial();

        String monomialRegex = "([-+])?((\\d*)(\\.\\d+)?)?\\s*(x(\\^(\\d+))?)?";
        Pattern pattern = Pattern.compile(monomialRegex);

        Matcher matcher = pattern.matcher(polynomial);

        while (matcher.find()) {
            double coefficient = 0;
            int degree = 0;
            boolean hasX = matcher.group(5) != null;

            if (matcher.group(2) != null && !matcher.group(2).isEmpty()) {
                coefficient = Double.parseDouble(matcher.group(2));
            } else if (hasX) {
                coefficient = 1;
            }

            if (matcher.group(1) != null && matcher.group(1).equals("-")) {
                coefficient *= -1;
            }

            if (hasX) {
                if (matcher.group(6) != null) {
                    degree = Integer.parseInt(matcher.group(7));
                } else {
                    degree = 1;
                }
            }

            if (coefficient != 0) {
                result.addMonomial(new Monomial(degree, coefficient));
            }
        }

        return result;
    }
}
