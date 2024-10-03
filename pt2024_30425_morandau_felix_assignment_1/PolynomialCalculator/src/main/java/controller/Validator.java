package controller;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Validator {
    public static boolean validatePolynomial(String polynomial) {
        String monomial = "\\b((\\d+(\\.\\d+)?)?\\s*(x\\s*(\\^\\s*\\d+)?)?\\s*)";

        String regex = "^-?" + monomial + "([+-]" + monomial + ")*$";
        Pattern pattern1 = Pattern.compile(regex);

        Matcher matcher = pattern1.matcher(polynomial);

        return matcher.matches();
    }
}
