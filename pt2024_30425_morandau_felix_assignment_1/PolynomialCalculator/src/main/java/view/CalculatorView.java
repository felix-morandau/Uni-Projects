package view;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

public class CalculatorView extends JFrame {
    private final JButton add = new JButton("add");
    private final JButton subtract = new JButton("subtract");
    private final JButton multiply = new JButton("multiply");
    private final JButton divide = new JButton("divide");
    private final JButton differentiate = new JButton("differentiate");
    private final JButton integrate = new JButton("integrate");
    private final JTextField firstPolynomial = new JTextField();
    private final JTextField secondPolynomial = new JTextField();
    private final Label result = new Label();

    public CalculatorView() {
        setTitle("Polynomial Calculator");
        setSize(400, 500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        initUI();
    }

    private void initUI() {
        JPanel contentPanel = new JPanel();
        contentPanel.setLayout(new GridLayout(2, 1));
        contentPanel.setBackground(new Color(255, 229, 180));
        contentPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        setContentPane(contentPanel);

        JPanel bottomPanel = new JPanel();
        bottomPanel.setLayout(new GridLayout(1, 1));

        JPanel topPanel = new JPanel();
        topPanel.setBackground(new Color(255, 229, 180));
        topPanel.setLayout(new GridLayout(6, 1, 10, 10));

        topPanel.add(new JLabel("Polynomial 1:"));
        topPanel.add(firstPolynomial);
        topPanel.add(new JLabel("Polynomial 2:"));
        topPanel.add(secondPolynomial);
        topPanel.add(new JLabel("Result:"));
        topPanel.add(result);

        JPanel operationPanel = new JPanel();
        operationPanel.setLayout(new GridLayout(6, 1, 2, 2));

        operationPanel.add(add);
        operationPanel.add(subtract);
        operationPanel.add(multiply);
        operationPanel.add(divide);
        operationPanel.add(differentiate);
        operationPanel.add(integrate);

        bottomPanel.add(operationPanel);

        contentPanel.add(topPanel);
        contentPanel.add(bottomPanel);

        //button colors
        add.setBackground(new Color(255, 195, 0));
        subtract.setBackground(new Color(255, 195, 0));
        multiply.setBackground(new Color(255, 195, 0));
        divide.setBackground(new Color(255, 195, 0));
        differentiate.setBackground(new Color(255, 195, 0));
        integrate.setBackground(new Color(255, 195, 0));

        setVisible(true);
    }

    public JButton getAdd() {
        return add;
    }

    public JButton getSubtract() {
        return subtract;
    }

    public JButton getMultiply() {
        return multiply;
    }

    public JButton getDivide() {
        return divide;
    }

    public JButton getDifferentiate() {
        return differentiate;
    }

    public JButton getIntegrate() {
        return integrate;
    }

    public JTextField getFirstPolynomial() {
        return firstPolynomial;
    }

    public JTextField getSecondPolynomial() {
        return secondPolynomial;
    }

    public void setResult(String string) {
        this.result.setText(string);
    }
}
