package controller;

import model.Polynomial;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PolynomialParserTest {
    @Test
    void testParse() {
        String polynomial = "2x+3x^3+5x+3x^4+2";

        Polynomial res = PolynomialParser.getPolynomial(polynomial);

        assertEquals("3.0x^4 + 3.0x^3 + 7.0x + 2.0", res.toString());
    }
}