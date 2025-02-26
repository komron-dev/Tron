package view;

import persistence.Database;
import persistence.HighScore;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.ArrayList;

public class StartScreen extends JFrame {
    private Database db;

    public StartScreen() {
        db = new Database();
        setTitle("Tron - Start Screen");
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        JPanel panel = new JPanel();
        panel.setBackground(Color.DARK_GRAY);
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        JLabel title = new JLabel("TRON GAME");
        title.setFont(new Font("Arial", Font.BOLD, 30));
        title.setForeground(Color.CYAN);
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        JButton startGameBtn = new JButton("Start Game");
        startGameBtn.setAlignmentX(Component.CENTER_ALIGNMENT);
        startGameBtn.setFont(new Font("Arial", Font.PLAIN, 18));
        startGameBtn.addActionListener((ActionEvent e) -> startGame());

        JButton highScoresBtn = new JButton("High Scores");
        highScoresBtn.setAlignmentX(Component.CENTER_ALIGNMENT);
        highScoresBtn.setFont(new Font("Arial", Font.PLAIN, 18));
        highScoresBtn.addActionListener((ActionEvent e) -> showHighScores());

        JButton exitBtn = new JButton("Exit");
        exitBtn.setAlignmentX(Component.CENTER_ALIGNMENT);
        exitBtn.setFont(new Font("Arial", Font.PLAIN, 18));
        exitBtn.addActionListener((ActionEvent e) -> System.exit(0));

        panel.add(Box.createVerticalStrut(50));
        panel.add(title);
        panel.add(Box.createVerticalStrut(30));
        panel.add(startGameBtn);
        panel.add(Box.createVerticalStrut(20));
        panel.add(highScoresBtn);
        panel.add(Box.createVerticalStrut(20));
        panel.add(exitBtn);
        panel.add(Box.createVerticalStrut(50));

        add(panel);

        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void startGame() {
        SetupDialog dlg = new SetupDialog(this);
        if(dlg.ok) {
            LevelDialog levelDlg = new LevelDialog(this);
            if(levelDlg.ok) {
                String chosenLevel = levelDlg.getSelectedLevel();
                new MainWindow(dlg.p1Name, dlg.p1Color, dlg.p2Name, dlg.p2Color, chosenLevel, this);
                this.setVisible(false);
            }
        }
    }

    private void showHighScores() {
        ArrayList<HighScore> hs = db.getTopScores();
        new HighScoreWindow(hs,this);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(StartScreen::new);
    }
}
