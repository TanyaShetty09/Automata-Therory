//Design and Implement a GUI (Graphical user Interface) for simulating a DFSM which accept the language having all ‘a’ before all ‘b’. Analyze the output with different test cases. 

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Q5 extends JFrame {
    private JTextField statesField, initialStateField, acceptingStatesField, inputAlphabetField, transitionsField, verifyStringField;
    private JTextArea outputArea;
    private JButton verifyButton;

    public Q5() {
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
        ImageIcon imageIcon = new ImageIcon("transition5.png");  // Replace with the actual path to your image
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

        // Call the state machine logic
        int result = isAcceptedString(inputString);

        // Display the result
        if (result == 1) {
            outputArea.setText("String accepted. ");
        } else {
            outputArea.setText("String rejected.  ");
        }
    }

    private int isAcceptedString(String inputString) {
        int length = inputString.length();
        int state = 0;

        for (int i = 0; i < length; i++) {
            if (state == 0) {
                state = startStateQ0(inputString.charAt(i));
            } else if (state == 1) {
                state = firstStateQ1(inputString.charAt(i));
            } else if (state == 2) {
                state = secondStateQ2(inputString.charAt(i));
            } else if (state == 3) {
                state = thirdStateQ3(inputString.charAt(i));
            } else {
                return 0;
            }
        }

        return (state == 1 || state == 2) ? 1 : 0;
    }

    private int startStateQ0(char s) {
        if (s == 'a') {
            return 1;
        } else if (s == 'b') {
            return 2;
        } else {
            return -1;
        }
    }

    private int firstStateQ1(char s) {
        if (s == 'a') {
            return 1;
        } else if (s == 'b') {
            return 2;
        } else {
            return -1;
        }
    }

    private int secondStateQ2(char s) {
        if (s == 'b') {
            return 2;
        } else if (s == 'a') {
            return 3;
        } else {
            return -1;
        }
    }

    private int thirdStateQ3(char s) {
        return 3;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(Q5::new);
    }
}
