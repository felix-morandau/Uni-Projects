package controller;

import model.Monomial;
import model.Polynomial;
import org.junit.jupiter.api.Test;

import java.util.Comparator;
import java.util.TreeMap;

import static org.junit.jupiter.api.Assertions.*;

class OperationsTest {
    Polynomial p1, p2, expected;
    TreeMap<Integer, Monomial> monomials1 = new TreeMap<>(Comparator.reverseOrder());
    TreeMap<Integer, Monomial> monomials2 = new TreeMap<>(Comparator.reverseOrder());
    TreeMap<Integer, Monomial> monomialsResult = new TreeMap<>(Comparator.reverseOrder());

    @Test
    void testAdditionWithoutResultingCoefficientNull() {
        monomials1.put(1, new Monomial(1, 2));
        monomials1.put(0, new Monomial(0, 1));
        monomials1.put(3, new Monomial(3, 3));
        monomials1.put(4, new Monomial(4, 4));
        monomials1.put(5, new Monomial(5, 2));
        p1 = new Polynomial(monomials1);

        monomials2.put(1, new Monomial(1, 2));
        monomials2.put(0, new Monomial(0, 3));
        monomials2.put(3, new Monomial(3, 1));
        monomials2.put(4, new Monomial(4, 9));
        monomials2.put(5, new Monomial(5, 3));
        p2 = new Polynomial(monomials2);

        monomialsResult.put(1, new Monomial(1, 4));
        monomialsResult.put(0, new Monomial(0, 4));
        monomialsResult.put(3, new Monomial(3, 4));
        monomialsResult.put(4, new Monomial(4, 13));
        monomialsResult.put(5, new Monomial(5, 5));
        expected = new Polynomial(monomialsResult);

        assertEquals(expected.toString(), Operations.add(p1, p2).toString());
    }

    @Test
    void testAdditionWithResultingCoefficientNull() {
        monomials1.put(1, new Monomial(1, 2));
        monomials1.put(0, new Monomial(0, 1));
        monomials1.put(3, new Monomial(3, 3));
        monomials1.put(4, new Monomial(4, 4));
        monomials1.put(5, new Monomial(5, 2));
        p1 = new Polynomial(monomials1);

        monomials2.put(1, new Monomial(1, -2));
        monomials2.put(0, new Monomial(0, -3));
        monomials2.put(3, new Monomial(3, 1));
        monomials2.put(4, new Monomial(4, -2));
        monomials2.put(5, new Monomial(5, -3));
        p2 = new Polynomial(monomials2);

        monomialsResult.put(0, new Monomial(0, -2));
        monomialsResult.put(3, new Monomial(3, 4));
        monomialsResult.put(4, new Monomial(4, 2));
        monomialsResult.put(5, new Monomial(5, -1));
        expected = new Polynomial(monomialsResult);

        assertEquals(expected.toString(), Operations.add(p1, p2).toString());
    }

    @Test
    void testSubtraction() {
        monomials1.put(1, new Monomial(1, 2));
        monomials1.put(0, new Monomial(0, 1));
        monomials1.put(3, new Monomial(3, 3));
        monomials1.put(4, new Monomial(4, 4));
        monomials1.put(5, new Monomial(5, 2));
        p1 = new Polynomial(monomials1);

        monomials2.put(1, new Monomial(1, 2));
        monomials2.put(0, new Monomial(0, 3));
        monomials2.put(3, new Monomial(3, 1));
        monomials2.put(4, new Monomial(4, 9));
        monomials2.put(5, new Monomial(5, 3));
        p2 = new Polynomial(monomials2);

        monomialsResult.put(0, new Monomial(0, -2));
        monomialsResult.put(3, new Monomial(3, 2));
        monomialsResult.put(4, new Monomial(4, -5));
        monomialsResult.put(5, new Monomial(5, -1));
        expected = new Polynomial(monomialsResult);

        assertEquals(expected.toString(), Operations.subtract(p1, p2).toString());
    }

    @Test
    void testSubtractionResultingZero() {
        monomials1.put(1, new Monomial(1, 2));
        monomials1.put(0, new Monomial(0, 1));
        monomials1.put(3, new Monomial(3, 3));
        monomials1.put(4, new Monomial(4, 4));
        monomials1.put(5, new Monomial(5, 2));
        p1 = new Polynomial(monomials1);

        monomials2.put(1, new Monomial(1, 2));
        monomials2.put(0, new Monomial(0, 1));
        monomials2.put(3, new Monomial(3, 3));
        monomials2.put(4, new Monomial(4, 4));
        monomials2.put(5, new Monomial(5, 2));
        p2 = new Polynomial(monomials2);

        assertEquals("0", Operations.subtract(p1, p2).toString());
    }

    @Test
    void testMultiplication() {
        monomials1.put(1, new Monomial(1, 2));
        monomials1.put(0, new Monomial(0, 1));
        p1 = new Polynomial(monomials1);

        monomials2.put(1, new Monomial(1, 2));
        monomials2.put(2, new Monomial(2, 1));
        monomials2.put(3, new Monomial(3, 3));
        p2 = new Polynomial(monomials2);

        monomialsResult.put(1, new Monomial(1, 2));
        monomialsResult.put(2, new Monomial(2, 5));
        monomialsResult.put(3, new Monomial(3, 5));
        monomialsResult.put(4, new Monomial(4, 6));
        expected = new Polynomial(monomialsResult);

        assertEquals(expected.toString(), Operations.multiply(p1, p2).toString());
    }

    @Test
    void testDivide() {
        monomials1.put(2, new Monomial(2, 1));
        monomials1.put(1, new Monomial(1, 5));
        monomials1.put(0, new Monomial(0, 6));
        p1 = new Polynomial(monomials1);

        monomials2.put(0, new Monomial(0, 2));
        monomials2.put(1, new Monomial(1, 1));
        p2 = new Polynomial(monomials2);

        monomialsResult.put(0, new Monomial(0, 3));
        monomialsResult.put(1, new Monomial(1, 1));
        expected = new Polynomial(monomialsResult);

        assertEquals(expected.toString(), Operations.divide(p1, p2).quotient().toString());
        assertEquals("0", Operations.divide(p1, p2).remainder().toString());
    }

    @Test
    void testDivideWithZero() {
        monomials1.put(1, new Monomial(1, 2));
        p1 = new Polynomial(monomials1);

        p2 = new Polynomial();

        assertThrows(IllegalArgumentException.class, () -> {
            Operations.divide(p1, p2);
        });
    }

    @Test
    void testDerivative() {
        monomials1.put(0, new Monomial(0, 2));
        monomials1.put(1, new Monomial(1, 1));
        monomials1.put(2, new Monomial(2, 4));
        monomials1.put(4, new Monomial(4, 5));
        monomials1.put(5, new Monomial(5, 1));
        p1 = new Polynomial(monomials1);

        monomialsResult.put(4, new Monomial(4, 5));
        monomialsResult.put(3, new Monomial(3, 20));
        monomialsResult.put(1, new Monomial(1, 8));
        monomialsResult.put(0, new Monomial(0, 1));
        expected = new Polynomial(monomialsResult);

        assertEquals(expected.toString(), Operations.derivative(p1).toString());
    }

    @Test
    void testDerivativeResultingZero() {
        monomials1.put(0, new Monomial(0, 0));
        p1 = new Polynomial(monomials1);

        assertEquals("0", Operations.derivative(p1).toString());
    }

    @Test
    void testIntegrate() {
        monomials1.put(0, new Monomial(0, 5));
        monomials1.put(1, new Monomial(1, 2));
        p1 = new Polynomial(monomials1);

        monomialsResult.put(1, new Monomial(1, 5));
        monomialsResult.put(2, new Monomial(2, 1));
        expected = new Polynomial(monomialsResult);

        assertEquals(expected.toString(), Operations.integrate(p1).toString());
    }
}