package view;

import model.TronGame;
import model.Direction;
import model.Player;
import model.LevelLoader;
import persistence.Database;
import persistence.HighScore;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import javax.swing.*;

public class MainWindow extends JFrame {
    public TronGame game;
    public Board board;
    public JLabel info;
    public Database db;
    public Timer timer;
    public Player p1,p2;
    private String currentLevelFile;
    private JFrame startScreen;

    public MainWindow(String p1Name, Color p1Color,
                      String p2Name, Color p2Color,
                      String levelFile, JFrame startScreenRef) {
        this.startScreen = startScreenRef;
        db=new Database();

        try {
            game= LevelLoader.loadLevel(levelFile, p1Name, p1Color, Direction.RIGHT,
                    p2Name, p2Color, Direction.LEFT);
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this,"Error loading level: "+e.getMessage());
            System.exit(1);
        }

        this.p1=game.p1; this.p2=game.p2;
        this.currentLevelFile = levelFile;

        setTitle("Tron - " + levelFile);
// If needed, you can allow resizing:
        setResizable(true);
        pack();
        setLocationRelativeTo(null);

        getContentPane().setBackground(Color.BLACK);
        setLayout(new BorderLayout());

        info=new JLabel("Time: 0s");
        info.setFont(new Font("Arial", Font.BOLD, 16));
        info.setForeground(Color.GREEN);
        info.setHorizontalAlignment(SwingConstants.CENTER);
        add(info,BorderLayout.NORTH);

        board=new Board(game);
        board.setBackground(Color.BLACK);
        // Add some margin around the board
        JPanel centerPanel = new JPanel(new GridBagLayout());
        centerPanel.setBackground(Color.BLACK);
        centerPanel.add(board);
        add(centerPanel,BorderLayout.CENTER);

        JMenuBar mb=new JMenuBar();
        mb.setBackground(Color.DARK_GRAY);
        JMenu mGame=new JMenu("Game");
        mGame.setForeground(Color.WHITE);
        JMenuItem mScores=new JMenuItem(new AbstractAction("High Scores"){
            public void actionPerformed(ActionEvent e){
                ArrayList<HighScore> hs=db.getTopScores();
                new HighScoreWindow(hs,MainWindow.this);
            }
        });
        JMenuItem mRestart=new JMenuItem(new AbstractAction("Restart"){
            public void actionPerformed(ActionEvent e){
                restart();
            }
        });
        mGame.add(mScores);
        mGame.add(mRestart);
        mb.add(mGame);
        setJMenuBar(mb);

        pack();
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        addKeyListener(new KeyAdapter(){
            public void keyPressed(KeyEvent e){
                int k=e.getKeyCode();
                if(k==KeyEvent.VK_W)game.p1.direction=Direction.UP;
                if(k==KeyEvent.VK_S)game.p1.direction=Direction.DOWN;
                if(k==KeyEvent.VK_A)game.p1.direction=Direction.LEFT;
                if(k==KeyEvent.VK_D)game.p1.direction=Direction.RIGHT;
                if(k==KeyEvent.VK_UP)game.p2.direction=Direction.UP;
                if(k==KeyEvent.VK_DOWN)game.p2.direction=Direction.DOWN;
                if(k==KeyEvent.VK_LEFT)game.p2.direction=Direction.LEFT;
                if(k==KeyEvent.VK_RIGHT)game.p2.direction=Direction.RIGHT;
            }
        });

        timer=new Timer(200,(ev)->gameTick());
        timer.start();
        setVisible(true);
    }

    private void gameTick(){
        if(!game.gameOver){
            game.tick();
            info.setText("Time: "+game.elapsedTime+"s");
            board.repaint();
            if(game.gameOver) endGame();
        }
    }

    private void endGame(){
        timer.stop();
        if(game.winner!=null){
            db.updateScore(game.winner.name);
            JOptionPane.showMessageDialog(this,game.winner.name+" wins!");
        } else {
            JOptionPane.showMessageDialog(this,"Draw!");
        }
        // After game ends, go back to start screen
        this.dispose();
        startScreen.setVisible(true);
    }

    private void restart(){
        this.dispose();
        SetupDialog dlg=new SetupDialog(null);
        if(dlg.ok){
            LevelDialog levelDlg = new LevelDialog(null);
            if(levelDlg.ok) {
                new MainWindow(dlg.p1Name, dlg.p1Color, dlg.p2Name, dlg.p2Color, levelDlg.getSelectedLevel(), startScreen);
            }
        }
    }
}
