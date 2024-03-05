import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AutomatonInputForm extends JFrame {
    private JTextField statesField, alphabetField, transitionField, startStateField, acceptingStatesField;

    public AutomatonInputForm() {
        setTitle("Automaton Input Form");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(6, 2));

        // Components
        add(new JLabel("Number of States:"));
        statesField = new JTextField();
        add(statesField);

        add(new JLabel("Alphabet:"));
        alphabetField = new JTextField();
        add(alphabetField);

        add(new JLabel("Transition Table (comma-separated):"));
        transitionField = new JTextField();
        add(transitionField);

        add(new JLabel("Start State:"));
        startStateField = new JTextField();
        add(startStateField);

        add(new JLabel("Accepting States (comma-separated):"));
        acceptingStatesField = new JTextField();
        add(acceptingStatesField);

        JButton submitButton = new JButton("Submit");
        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                onSubmit();
            }
        });
        add(submitButton);

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
                new AutomatonInputForm();
            }
        });
    }
}
