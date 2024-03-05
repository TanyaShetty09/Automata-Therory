import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class three extends JFrame {
    private JTextField statesField, initialStateField, acceptingStatesField, inputAlphabetField, transitionsField, verifyStringField;
    private JTextArea outputArea;

    public three() {
        setTitle("Deterministic Finite State Machine Simulator");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(500, 300);
        setLayout(new BorderLayout());

        // Input Panel
        JPanel inputPanel = new JPanel(new GridLayout(8, 2));
        inputPanel.add(new JLabel("Number of States:"));
        statesField = new JTextField();
        inputPanel.add(statesField);

        inputPanel.add(new JLabel("Initial State:"));
        initialStateField = new JTextField();
        inputPanel.add(initialStateField);

        inputPanel.add(new JLabel("Number of Accepting States:"));
        acceptingStatesField = new JTextField();
        inputPanel.add(acceptingStatesField);

        inputPanel.add(new JLabel("Enter Accepting States (comma-separated):"));
        JTextField acceptingStatesInputField = new JTextField();
        inputPanel.add(acceptingStatesInputField);

        inputPanel.add(new JLabel("Input Alphabet (comma-separated):"));
        inputAlphabetField = new JTextField();
        inputPanel.add(inputAlphabetField);

        inputPanel.add(new JLabel("Transitions (semicolon-separated):"));
        transitionsField = new JTextField();
        inputPanel.add(transitionsField);

        // Verify Panel
        inputPanel.add(new JLabel("Enter String to Verify:"));
        verifyStringField = new JTextField();
        inputPanel.add(verifyStringField);

        // Align the input panel to the left
        JPanel leftPanel = new JPanel(new BorderLayout());
        leftPanel.add(inputPanel, BorderLayout.WEST);
        add(leftPanel, BorderLayout.NORTH);

        // Verify Panel
        JPanel verifyPanel = new JPanel(new FlowLayout());
        verifyPanel.add(new JLabel("Enter String to Verify:"));
        verifyStringField = new JTextField(20);
        verifyPanel.add(verifyStringField);
        JButton verifyButton = new JButton("Verify");
        verifyButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                verifyString();
            }
        });
        verifyPanel.add(verifyButton);

        add(verifyPanel, BorderLayout.CENTER);

        // Output Panel
        outputArea = new JTextArea();
        outputArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(outputArea);
        add(scrollPane, BorderLayout.SOUTH);

        // Initialize GUI
        setVisible(true);
    }

    private void verifyString() {
        String inputString = verifyStringField.getText();

        if (inputString.isEmpty()) {
            outputArea.setText("Please enter a string to verify.");
            return;
        }

        // DFSM Simulator Logic
        String currentState = "q0";
        int bCount = 0;

        for (char symbol : inputString.toCharArray()) {
            if (symbol == 'a') {
                switch (currentState) {
                    case "q0":
                        currentState = "q2";
                        break;
                    case "q1":
                        currentState = "q3";
                        break;
                    case "q2":
                        currentState = "q0";
                        break;
                    case "q3":
                        currentState = "q3";
                        break;
                }
            } else if (symbol == 'b') {
                bCount++;
                switch (currentState) {
                    case "q0":
                        currentState = "q1";
                        break;
                    case "q1":
                        currentState = "q1";
                        break;
                    case "q2":
                        currentState = "q1";
                        break;
                    case "q3":
                        currentState = "q3";
                        break;
                }
            }
        }

        // Check if the final state is an accepting state
        boolean isAccepted = (currentState.equals("q0") || currentState.equals("q1")) && bCount >= 1;

        // Display the result
        if (isAccepted) {
            outputArea.setText("String accepted. Final state: " + currentState);
        } else {
            outputArea.setText("String rejected. Final state: " + currentState);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new FSMGUI());
    }
}
