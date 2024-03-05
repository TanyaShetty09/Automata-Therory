import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;

public class FSMSimulator extends JFrame {
    private JTextField statesField, initialStateField, acceptingStatesField, inputAlphabetField, transitionsField, verifyStringField;
    private JTextArea outputArea;
    private JButton generateButton, verifyButton;

    private Map<String, Map<Character, String>> transitionTable;
    private String initialState;
    private String[] acceptingStates;
    private String[] inputAlphabet;

    public FSMSimulator() {
        setTitle("Finite State Machine Simulator");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 400);
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
        JScrollPane scrollPane = new JScrollPane(outputArea);
        add(scrollPane, BorderLayout.SOUTH);

        // Generate Button
        generateButton = new JButton("Generate Transition Diagram");
        generateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                generateTransitionDiagram();
            }
        });
        add(generateButton, BorderLayout.SOUTH);

        setVisible(true);
    }

    private void generateTransitionDiagram() {
        // Implement FSM generation logic here
        // Populate the transitionTable, initialState, acceptingStates, and inputAlphabet

        int numberOfStates = Integer.parseInt(statesField.getText());
        initialState = initialStateField.getText();
        acceptingStates = acceptingStatesField.getText().split(",");
        inputAlphabet = inputAlphabetField.getText().split(",");

        transitionTable = new HashMap<>();
        String[] transitions = transitionsField.getText().split(";");

        for (String transition : transitions) {
            String[] parts = transition.split(",");
            String currentState = parts[0];
            char symbol = parts[1].charAt(0);
            String nextState = parts[2];

            if (!transitionTable.containsKey(currentState)) {
                transitionTable.put(currentState, new HashMap<>());
            }
            transitionTable.get(currentState).put(symbol, nextState);
        }

        // Display the transition diagram
        StringBuilder diagram = new StringBuilder("Transition Diagram:\n");

        for (String state : transitionTable.keySet()) {
            for (char symbol : transitionTable.get(state).keySet()) {
                String nextState = transitionTable.get(state).get(symbol);
                diagram.append(state).append(" --").append(symbol).append("--> ").append(nextState).append("\n");
            }
        }

        outputArea.setText(diagram.toString());
    }

    private void verifyString() {
        if (transitionTable == null || initialState == null || acceptingStates == null || inputAlphabet == null) {
            outputArea.setText("Please generate the transition diagram first.");
            return;
        }

        String inputString = verifyStringField.getText();
        String currentState = initialState;

        for (char symbol : inputString.toCharArray()) {
            if (!transitionTable.containsKey(currentState) || !transitionTable.get(currentState).containsKey(symbol)) {
                outputArea.setText("Invalid symbol or transition at state " + currentState);
                return;
            }

            currentState = transitionTable.get(currentState).get(symbol);
        }

        boolean isAccepted = false;
        for (String acceptingState : acceptingStates) {
            if (currentState.equals(acceptingState)) {
                isAccepted = true;
                break;
            }
        }

        outputArea.setText("String '" + inputString + "' is " + (isAccepted ? "accepted" : "rejected"));
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(FSMSimulator::new);
    }
}
