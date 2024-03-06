//Design and Implement a GUI (Graphical user Interface) for simulating a DFSM which accept the language L = {w | w {a, b}* and Na (w) mod 3 = Nb (w) mod 3}. Analyze the output with different test cases.

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Q2 extends JFrame {
    private JTextField statesField, initialStateField, acceptingStatesField, inputAlphabetField, transitionsField, verifyStringField;
    private JTextArea outputArea;
    private JButton verifyButton;

    private String initialState;
    private String[] acceptingStates;
    private String[] inputAlphabet;

    public Q2() {
        setTitle("Finite State Machine Simulator");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 600);
        setLayout(new BorderLayout());

        // Input Panel
        JPanel inputPanel = new JPanel(new GridLayout(6, 2));
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
        inputAlphabetField = new JTextField();
        inputPanel.add(inputAlphabetField);

        inputPanel.add(new JLabel("Input Alphabet (comma-separated):"));
        inputAlphabetField = new JTextField();
        inputPanel.add(inputAlphabetField);

        inputPanel.add(new JLabel("Enter Transitions (comma-separated):"));
        transitionsField = new JTextField();
        inputPanel.add(transitionsField);

        add(inputPanel, BorderLayout.NORTH);

        // Verify Panel
        JPanel verifyPanel = new JPanel(new FlowLayout());
        verifyPanel.add(new JLabel("Enter the String to Verify:"));
        verifyStringField = new JTextField(20);
        verifyPanel.add(verifyStringField);

        verifyButton = new JButton("Verify");
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
        outputArea.setFont(new Font("Arial", Font.PLAIN, 18)); // Increase font size
        JScrollPane scrollPane = new JScrollPane(outputArea);
        add(scrollPane, BorderLayout.SOUTH);

        // Display Image
        ImageIcon imageIcon = new ImageIcon("transition2.png");  // Replace with the actual path to your image
        Image scaledImage = imageIcon.getImage().getScaledInstance(300, 300, Image.SCALE_SMOOTH);
        imageIcon = new ImageIcon(scaledImage);
        JLabel imageLabel = new JLabel(imageIcon);
        add(imageLabel, BorderLayout.WEST);

        setVisible(true);
    }

    private void verifyString() {
        String inputString = verifyStringField.getText();

        if (inputString.isEmpty()) {
            outputArea.setText("Please enter a string to verify.");
            return;
        }

        // DFSM Simulator Logic
        String currentState = "q00";

        for (char symbol : inputString.toCharArray()) {
            if (symbol == 'a') {
                switch (currentState) {
                    case "q00":
                        currentState = "q10";
                        break;
                    case "q01":
                        currentState = "q11";
                        break;
                    case "q02":
                        currentState = "q12";
                        break;
                    case "q10":
                        currentState = "q20";
                        break;
                    case "q11":
                        currentState = "q21";
                        break;
                    case "q12":
                        currentState = "q22";
                        break;
                    case "q20":
                        currentState = "q00";
                        break;
                    case "q21":
                        currentState = "q01";
                        break;
                    case "q22":
                        currentState = "q02";
                        break;
                }
            } else if (symbol == 'b') {
                switch (currentState) {
                    case "q00":
                        currentState = "q01";
                        break;
                    case "q01":
                        currentState = "q02";
                        break;
                    case "q02":
                        currentState = "q00";
                        break;
                    case "q10":
                        currentState = "q11";
                        break;
                    case "q11":
                        currentState = "q12";
                        break;
                    case "q12":
                        currentState = "q10";
                        break;
                    case "q20":
                        currentState = "q21";
                        break;
                    case "q21":
                        currentState = "q22";
                        break;
                    case "q22":
                        currentState = "q20";
                        break;
                }
            }
        }

        // Check if the final state is an accepting state
        boolean isAccepted = currentState.equals("q00") || currentState.equals("q11") || currentState.equals("q22");

        // Display the result
        if (isAccepted) {
            outputArea.setText("String accepted. " );
        } else {
            outputArea.setText("String rejected.  " );
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(Q2::new);
    }
}
