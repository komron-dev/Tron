package view;

import java.util.ArrayList;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.WindowConstants;
import persistence.HighScore;

public class HighScoreWindow extends JDialog{
    public HighScoreWindow(ArrayList<HighScore> hs,JFrame parent){
        super(parent,true);
        JTable table=new JTable(new HighScoreTableModel(hs));
        add(new JScrollPane(table));
        setSize(300,300);
        setTitle("High Scores");
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
    }
}
