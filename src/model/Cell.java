package model;

public class Cell {
    public boolean occupied;
    public ColorType colorType;
    public Cell(){
        occupied=false;
        colorType=ColorType.NONE;
    }
    public enum ColorType {
        NONE,PLAYER1,PLAYER2,OBSTACLE
    }
}
