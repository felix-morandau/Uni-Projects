package model;

import java.util.Comparator;
import java.util.Map;
import java.util.TreeMap;

public class Polynomial implements Comparable<Polynomial> {
    private TreeMap<Integer, Monomial> polynomial = new TreeMap<>(Comparator.reverseOrder());

    public Polynomial(TreeMap<Integer, Monomial> polynomial) {
        this.polynomial = polynomial;
    }

    public Polynomial() {

    }

    public void addMonomial(Monomial monomial) {
        if (polynomial.containsKey(monomial.degree())) {

            Monomial existingMonomial = polynomial.get(monomial.degree());
            double newCoefficient = existingMonomial.coefficient() + monomial.coefficient();

            if (newCoefficient == 0) {
                polynomial.remove(monomial.degree());
            } else {
                polynomial.put(monomial.degree(), new Monomial(monomial.degree(), newCoefficient));
            }
        } else {
            polynomial.put(monomial.degree(), monomial);
        }
    }

    public TreeMap<Integer, Monomial> getPolynomial() {
        return this.polynomial;
    }

    public TreeMap<Integer, Monomial> getInversePolynomial() {
        TreeMap<Integer, Monomial> inversePolynomial = new TreeMap<>(Comparator.reverseOrder());

        for (Map.Entry<Integer, Monomial> entry : polynomial.entrySet()) {
            inversePolynomial.put(entry.getKey(), new Monomial(entry.getKey(), -entry.getValue().coefficient()));
        }

        return inversePolynomial;
    }

    public String toString() {
        if (polynomial.isEmpty()) {
            return "0";
        }

        StringBuilder representation = new StringBuilder();
        for (Map.Entry<Integer, Monomial> entry : polynomial.entrySet()) {
            double coeff = entry.getValue().coefficient();
            int degree = entry.getKey();

            if (coeff == 0) continue;

            if (!representation.isEmpty()) {
                representation.append(coeff > 0 ? " + " : " - ");
            } else if (coeff < 0) {
                representation.append("-");
            }

            if (Math.abs(coeff) != 1 || degree == 0) {
                representation.append(Math.abs(coeff));
            }

            if (degree > 0) {
                representation.append("x");
                if (degree > 1) {
                    representation.append("^").append(degree);
                }
            }
        }

        return representation.toString();
    }

    public int getDegree() {
        if (polynomial.isEmpty()) {
            throw new IllegalStateException();
        }
        return polynomial.firstKey();
    }

    public Monomial getLead() {
        return polynomial.firstEntry().getValue();
    }

    public int compareTo(Polynomial polynomial) {
        return Integer.compare(this.getDegree(), polynomial.getDegree());
    }
}
