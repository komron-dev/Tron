package model;

import java.awt.Color;

public class Player {
    public String name;
    public Color color;
    public int x,y;
    public Direction direction;
    public boolean alive=true;
    public Player(String n,Color c,int x,int y,Direction d){
        this.name=n;this.color=c;this.x=x;this.y=y;this.direction=d;
    }
}