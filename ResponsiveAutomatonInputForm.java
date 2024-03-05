import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ResponsiveAutomatonInputForm extends JFrame {
    private JTextField statesField, alphabetField, transitionField, startStateField, acceptingStatesField;

    public ResponsiveAutomatonInputForm() {
        setTitle("Responsive Automaton Input Form");
        setSize(500, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);

        // Components
        gbc.gridx = 0;
        gbc.gridy = 0;
        add(new JLabel("Number of States:"), gbc);
        setLayout(new GridLayout(6, 3));
        gbc.gridx = 1;
        gbc.gridy = 0;
        statesField = new JTextField();
        add(statesField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        add(new JLabel("Alphabet:"), gbc);

        gbc.gridx = 1;
        gbc.gridy = 1;
        alphabetField = new JTextField();
        add(alphabetField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        add(new JLabel("Transition Table (comma-separated):"), gbc);

        gbc.gridx = 1;
        gbc.gridy = 2;
        transitionField = new JTextField();
        add(transitionField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        add(new JLabel("Start State:"), gbc);

        gbc.gridx = 1;
        gbc.gridy = 3;
        startStateField = new JTextField();
        add(startStateField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 4;
        add(new JLabel("Accepting States (comma-separated):"), gbc);

        gbc.gridx = 1;
        gbc.gridy = 4;
        acceptingStatesField = new JTextField();
        add(acceptingStatesField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 5;
        gbc.gridwidth = 2;
        JButton submitButton = new JButton("Submit");
        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                onSubmit();
            }
        });
        add(submitButton, gbc);

        // Set minimum size to prevent components from becoming too small
        setMinimumSize(new Dimension(400, 300));

        // Ensure the window is centered on the screen
        setLocationRelativeTo(null);

        setVisible(true);
    }

    private void onSubmit() {
        String numStates = statesField.getText();
        String alphabet = alphabetField.getText();
        String transitionTable = transitionField.getText();
        String startState = startStateField.getText();
        String acceptingStates = acceptingStatesField.getText();

        // Process the input as needed (you can parse and use the values in your automaton logic)

        // For now, let's just print the input
        System.out.println("Number of States: " + numStates);
        System.out.println("Alphabet: " + alphabet);
        System.out.println("Transition Table: " + transitionTable);
        System.out.println("Start State: " + startState);
        System.out.println("Accepting States: " + acceptingStates);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new ResponsiveAutomatonInputForm();
            }
        });
    }
}
