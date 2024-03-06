import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;

public class Q1 extends JFrame {
    private JTextField statesField, initialStateField, acceptingStatesField, inputAlphabetField, transitionsField, verifyStringField;
    private JTextArea outputArea;
    private JButton verifyButton;

    private Map<String, Map<Character, String>> transitionTable;
    private String initialState;
    private String[] acceptingStates;
    private String[] inputAlphabet;

    public Q1() {
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
        ImageIcon imageIcon = new ImageIcon("transition1.png");  // Replace with the actual path to your image
        Image scaledImage = imageIcon.getImage().getScaledInstance(550, 500, Image.SCALE_SMOOTH);
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
        SwingUtilities.invokeLater(Q1::new);
    }
}
