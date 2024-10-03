package controller;

import model.Polynomial;
import view.CalculatorView;

import javax.swing.*;

public class CalculatorController {
    private final CalculatorView view = new CalculatorView();
    public CalculatorController(){
        view.setVisible(true);
        calculatorFunctionality();
    }

    private void calculatorFunctionality(){
        view.getAdd().addActionListener(e -> addButton());
        view.getSubtract().addActionListener(e -> subtractButton());
        view.getMultiply().addActionListener(e -> multiplyButton());
        view.getDifferentiate().addActionListener(e -> differentiateButton());
        view.getMultiply().addActionListener(e -> multiplyButton());
        view.getIntegrate().addActionListener(e -> integrateButton());
        view.getDivide().addActionListener(e -> divisionButton());
    }

    private void addButton(){
        String polynomial1 = view.getFirstPolynomial().getText();
        String polynomial2 = view.getSecondPolynomial().getText();

        if(checkFormat(polynomial1, polynomial2)) {
            Polynomial p1 = PolynomialParser.getPolynomial(polynomial1);
            Polynomial p2 = PolynomialParser.getPolynomial(polynomial2);

            view.setResult(Operations.add(p1, p2).toString());
        }
    }

    private void subtractButton(){
        String polynomial1 = view.getFirstPolynomial().getText();
        String polynomial2 = view.getSecondPolynomial().getText();

        if(checkFormat(polynomial1, polynomial2)) {
            Polynomial p1 = PolynomialParser.getPolynomial(polynomial1);
            Polynomial p2 = PolynomialParser.getPolynomial(polynomial2);

            view.setResult(Operations.subtract(p1, p2).toString());
        }
    }

    private void multiplyButton(){
        String polynomial1 = view.getFirstPolynomial().getText();
        String polynomial2 = view.getSecondPolynomial().getText();

        if(checkFormat(polynomial1, polynomial2)) {
            Polynomial p1 = PolynomialParser.getPolynomial(polynomial1);
            Polynomial p2 = PolynomialParser.getPolynomial(polynomial2);

            view.setResult(Operations.multiply(p1, p2).toString());
        }
    }

    private void divisionButton(){
        String polynomial1 = view.getFirstPolynomial().getText();
        String polynomial2 = view.getSecondPolynomial().getText();

        if(checkFormat(polynomial1, polynomial2)){
            Polynomial p1 = PolynomialParser.getPolynomial(polynomial1);
            Polynomial p2 = PolynomialParser.getPolynomial(polynomial2);

            try {
                view.setResult(Operations.divide(p1, p2).quotient().toString() + " R: " + Operations.divide(p1, p2).remainder().toString());
            }
            catch(IllegalArgumentException e){
                JOptionPane.showMessageDialog(view, "Second polynomial is 0.");
            }
        }
    }

    private void differentiateButton(){
        String polynomial1 = view.getFirstPolynomial().getText();

        if(checkFormat(polynomial1, "0")) {
            Polynomial p1 = PolynomialParser.getPolynomial(polynomial1);

            view.setResult(Operations.derivative(p1).toString());
        }
    }

    private void integrateButton(){
        String polynomial1 = view.getFirstPolynomial().getText();

        if(checkFormat(polynomial1, "0")) {
            Polynomial p1 = PolynomialParser.getPolynomial(polynomial1);

            view.setResult(Operations.integrate(p1).toString());
        }
    }


    private boolean checkFormat(String polynomial1, String polynomial2){
        if(!Validator.validatePolynomial(polynomial1)){
            JOptionPane.showMessageDialog(view, "Polynomial 1 is in the wrong format.");
            return false;
        }

        if(!Validator.validatePolynomial(polynomial2)){
            JOptionPane.showMessageDialog(view, "Polynomial 2 is in the wrong format.");
            return false;
        }

        return true;
    }
}
