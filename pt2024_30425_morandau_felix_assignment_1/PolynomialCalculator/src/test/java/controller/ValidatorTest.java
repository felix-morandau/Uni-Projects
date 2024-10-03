package controller;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ValidatorTest {

    @Test
    void testValidatePolynomial() {
        String polynomial = "x^2+x^3-2x";

        boolean check = Validator.validatePolynomial(polynomial);

        assertTrue(check);
    }

    @Test
    void testValidatePolynomialJustANumber() {
        String polynomial = "2";

        boolean check = Validator.validatePolynomial(polynomial);

        assertTrue(check);
    }

    @Test
    void testValidatePolynomialJustX() {
        String polynomial = "x";

        boolean check = Validator.validatePolynomial(polynomial);

        assertTrue(check);
    }

    @Test
    void testValidatePolynomialMoreTerms() {
        String polynomial = "x^2+x^3-2x";

        boolean check = Validator.validatePolynomial(polynomial);

        assertTrue(check);
    }

    @Test
    void testValidatePolynomialOneTerm() {
        String polynomial = "2x^2";

        boolean check = Validator.validatePolynomial(polynomial);

        assertTrue(check);
    }

    @Test
    void testValidatePolynomialWithMoreSigns() {
        String polynomial = "x^2+x^3---2x";

        boolean check = Validator.validatePolynomial(polynomial);

        assertFalse(check);
    }

    @Test
    void testValidatePolynomialWithFirstTermNegative() {
        String polynomial = "-x^2+x^3-2x";

        boolean check = Validator.validatePolynomial(polynomial);

        assertTrue(check);
    }

    @Test
    void testValidatePolynomialLetters() {
        String polynomial = "x^2+xxx+2x";

        boolean check = Validator.validatePolynomial(polynomial);

        assertFalse(check);
    }

    @Test
    void testValidatePolynomialWithSpaces() {
        String polynomial = "-x ^ 2 +2x +x^2";

        boolean check = Validator.validatePolynomial(polynomial);

        assertTrue(check);
    }

    @Test
    void testValidatePolynomialWithFloatingPointCoefficients() {
        String polynomial = "-x^2+2.3x+x^2";

        boolean check = Validator.validatePolynomial(polynomial);

        assertTrue(check);
    }

}