package view;

import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;
import persistence.HighScore;

public class HighScoreTableModel extends AbstractTableModel{
    private final ArrayList<HighScore> list;
    private final String[] cols={"Name","Score"};
    public HighScoreTableModel(ArrayList<HighScore> l){this.list=l;}
    public int getRowCount(){return list.size();}
    public int getColumnCount(){return cols.length;}
    public Object getValueAt(int r,int c){
        HighScore h=list.get(r);
        if(c==0)return h.name;else return h.score;
    }
    public String getColumnName(int i){return cols[i];}
}
