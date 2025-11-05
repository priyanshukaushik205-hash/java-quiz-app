import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class OnlineQuizApp extends JFrame implements ActionListener {
    JLabel questionLabel;
    JRadioButton[] options = new JRadioButton[4];
    ButtonGroup group;
    JButton nextButton, resultButton;

    String[][] questions = {
        {"Which language is used for Android development?", "Java", "Python", "Swift", "C++", "Java"},
        {"What does JVM stand for?", "Java Variable Method", "Java Virtual Machine", "Joint Virtual Machine", "Java Verified Mode", "Java Virtual Machine"},
        {"Which of these is not a Java feature?", "Object-Oriented", "Use of pointers", "Platform Independent", "Dynamic", "Use of pointers"},
        {"Which method is the entry point in Java?", "start()", "run()", "main()", "init()", "main()"},
        {"Which keyword is used for inheritance?", "implement", "extends", "inherits", "super", "extends"}
    };

    int current = 0, score = 0;

    OnlineQuizApp() {
        setTitle("Online Quiz Application");
        setSize(500, 300);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        questionLabel = new JLabel();
        add(questionLabel, BorderLayout.NORTH);

        JPanel optionsPanel = new JPanel();
        optionsPanel.setLayout(new GridLayout(4, 1));
        group = new ButtonGroup();

        for (int i = 0; i < 4; i++) {
            options[i] = new JRadioButton();
            group.add(options[i]);
            optionsPanel.add(options[i]);
        }

        add(optionsPanel, BorderLayout.CENTER);

        JPanel controlPanel = new JPanel();
        nextButton = new JButton("Next");
        resultButton = new JButton("Show Result");
        nextButton.addActionListener(this);
        resultButton.addActionListener(this);
        resultButton.setEnabled(false);

        controlPanel.add(nextButton);
        controlPanel.add(resultButton);
        add(controlPanel, BorderLayout.SOUTH);

        loadQuestion();
        setVisible(true);
    }

    void loadQuestion() {
        group.clearSelection();
        questionLabel.setText("Q" + (current + 1) + ". " + questions[current][0]);
        for (int i = 0; i < 4; i++) {
            options[i].setText(questions[current][i + 1]);
        }
    }

    boolean checkAnswer() {
        for (int i = 0; i < 4; i++) {
            if (options[i].isSelected()) {
                return options[i].getText().equals(questions[current][5]);
            }
        }
        return false;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == nextButton) {
            if (checkAnswer()) score++;
            current++;
            if (current < questions.length) {
                loadQuestion();
            } else {
                nextButton.setEnabled(false);
                resultButton.setEnabled(true);
            }
        }

        if (e.getSource() == resultButton) {
            if (current < questions.length && checkAnswer()) score++;        
            JOptionPane.showMessageDialog(this, "Your score: " + score + "/" + questions.length);

            String feedback;
            if (score == questions.length) feedback = "Excellent!";
            else if (score >= 3) feedback = "Good Job!";
            else feedback = "Keep Practicing!";

            JOptionPane.showMessageDialog(this, "Feedback: " + feedback);
            System.exit(0);
        }
    }

    public static void main(String[] args) {
        new OnlineQuizApp();
    }
}