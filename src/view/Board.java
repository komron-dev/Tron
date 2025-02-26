package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import javax.swing.JPanel;
import model.TronGame;
import model.Cell;
import model.Player;

public class Board extends JPanel {
    public TronGame game;
    public int cellSize=15;
    public Board(TronGame g){
        this.game=g;
        setPreferredSize(new Dimension(game.cols*cellSize, game.rows*cellSize));
    }
    protected void paintComponent(Graphics g){
        super.paintComponent(g);
        for(int y=0;y<game.rows;y++){
            for(int x=0;x<game.cols;x++){
                Cell c=game.grid[y][x];
                if(c.occupied){
                    if(c.colorType== Cell.ColorType.PLAYER1) g.setColor(game.p1.color);
                    else if(c.colorType== Cell.ColorType.PLAYER2) g.setColor(game.p2.color);
                    else g.setColor(Color.GRAY);
                } else {
                    g.setColor(Color.BLACK);
                }
                g.fillRect(x*cellSize,y*cellSize,cellSize,cellSize);
            }
        }
        drawPlayer(g,game.p1);
        drawPlayer(g,game.p2);
    }
    private void drawPlayer(Graphics g,Player p){
        if(p.alive){
            g.setColor(Color.WHITE);
            g.fillOval(p.x*cellSize,p.y*cellSize,cellSize,cellSize);
        }
    }
}
