package view;

import javax.swing.*;
import java.awt.*;

public class SimulationView extends JFrame {
    private JTextArea textArea = new JTextArea();

    public SimulationView() {
        initUI();
    }

    private void initUI() {
        setSize(new Dimension(600, 600));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        textArea = new JTextArea();
        textArea.setEditable(false);
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);

        JScrollPane scrollPane = new JScrollPane(textArea);

        getContentPane().add(scrollPane, BorderLayout.CENTER);
        setVisible(true);
    }

    public void runUI(String simulationText) {
        textArea.setText(simulationText);
        textArea.setCaretPosition(0);
    }

}
