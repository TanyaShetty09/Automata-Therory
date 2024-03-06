//Design and Implement a GUI (Graphical user Interface) for simulating a DFSM which accept strings that start and end with same character. Analyze the output with different test cases.

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Q3 extends JFrame {
    private JTextField statesField, initialStateField, acceptingStatesField, inputAlphabetField, transitionsField, verifyStringField;
    private JTextArea outputArea;
    private JButton verifyButton;

    public Q3() {
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
        ImageIcon imageIcon = new ImageIcon("transition3.png");  // Replace with the actual path to your image
        Image scaledImage = imageIcon.getImage().getScaledInstance(300, 250, Image.SCALE_SMOOTH);
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
        String currentState = "q0";

        if (inputString.charAt(0) == 'a') {
            currentState = "q1";
            for (char symbol : inputString.toCharArray()) {
                if (currentState.equals("q1")) {
                    if (symbol == 'a') {
                        currentState = "q2";
                    } else if (symbol == 'b') {
                        currentState = "q1";
                    }
                } else if (currentState.equals("q2")) {
                    if (symbol == 'a') {
                        currentState = "q2";
                    } else if (symbol == 'b') {
                        currentState = "q1";
                    }
                }
            }
        } else if (inputString.charAt(0) == 'b') {
            currentState = "q3";
            for (char symbol : inputString.toCharArray()) {
                if (currentState.equals("q3")) {
                    if (symbol == 'a') {
                        currentState = "q3";
                    } else if (symbol == 'b') {
                        currentState = "q4";
                    }
                } else if (currentState.equals("q4")) {
                    if (symbol == 'a') {
                        currentState = "q3";
                    } else if (symbol == 'b') {
                        currentState = "q4";
                    }
                }
            }
        }

        // Check if the final state is an accepting state
        boolean isAccepted = currentState.equals("q2") || currentState.equals("q4");

        // Display the result
        if (isAccepted) {
            outputArea.setText("String accepted. ");
        } else {
            outputArea.setText("String rejected.  ");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(Q3::new);
    }
}
