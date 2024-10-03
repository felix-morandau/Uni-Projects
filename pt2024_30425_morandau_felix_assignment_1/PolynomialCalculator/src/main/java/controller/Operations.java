package controller;

import model.*;

import java.util.Map;

public class Operations {

    public static Polynomial add(Polynomial p1, Polynomial p2) {
        Polynomial result = new Polynomial(p1.getPolynomial());

        for (Map.Entry<Integer, Monomial> entry : p2.getPolynomial().entrySet()) {
            result.addMonomial(entry.getValue());
        }

        return result;
    }

    public static Polynomial subtract(Polynomial p1, Polynomial p2) {
        Polynomial result = new Polynomial(p2.getInversePolynomial());

        for (Map.Entry<Integer, Monomial> entry : p1.getPolynomial().entrySet()) {
            result.addMonomial(entry.getValue());
        }

        return result;
    }

    public static Polynomial multiply(Polynomial p1, Polynomial p2) {
        Polynomial result = new Polynomial();

        for (Map.Entry<Integer, Monomial> entry1 : p1.getPolynomial().descendingMap().entrySet()) {
            for (Map.Entry<Integer, Monomial> entry2 : p2.getPolynomial().descendingMap().entrySet()) {
                result.addMonomial(new Monomial(entry1.getKey() + entry2.getKey(),
                        entry1.getValue().coefficient() * entry2.getValue().coefficient()));
            }
        }

        return result;
    }

    public static DivisionResult divide(Polynomial p1, Polynomial p2) {
        if (p2 == null || p2.toString().equals("0")) {
            throw new IllegalArgumentException("Divisor cannot be null or zero.");
        }

        Polynomial quotient = new Polynomial();
        Polynomial remainder = new Polynomial(p1.getPolynomial());

        while (!remainder.getPolynomial().isEmpty() && remainder.getDegree() >= p2.getDegree()) {
            int degreeDifference = remainder.getDegree() - p2.getDegree();
            double coefficientDivision = remainder.getLead().coefficient() / p2.getLead().coefficient();

            Polynomial termQuotient = new Polynomial();
            termQuotient.addMonomial(new Monomial(degreeDifference, coefficientDivision));

            quotient = Operations.add(quotient, termQuotient);
            remainder = Operations.subtract(remainder, Operations.multiply(termQuotient, p2));
        }

        return new DivisionResult(quotient, remainder);
    }


    public static Polynomial derivative(Polynomial polynomial) {
        Polynomial result = new Polynomial();

        for (Map.Entry<Integer, Monomial> entry : polynomial.getPolynomial().entrySet()) {
            if (entry.getKey() > 0) {
                result.addMonomial(new Monomial(entry.getKey() - 1,
                        entry.getValue().coefficient() * entry.getKey()));
            }
        }

        return result;
    }

    public static Polynomial integrate(Polynomial polynomial) {
        Polynomial result = new Polynomial();

        for (Map.Entry<Integer, Monomial> entry : polynomial.getPolynomial().entrySet()) {
            result.addMonomial(new Monomial(entry.getKey() + 1,
                    entry.getValue().coefficient() / (entry.getKey() + 1)));
        }

        return result;
    }
}
