package model;

public class TronGame {
    public int rows,cols;
    public Cell[][] grid;
    public Player p1,p2;
    public boolean gameOver=false;
    public Player winner=null;
    public int elapsedTime=0;

    public TronGame(int r,int c, Player p1, Player p2, Cell[][] grid){
        this.rows=r; this.cols=c;
        this.p1=p1; this.p2=p2;
        this.grid=grid;

        markCell(p1.x,p1.y, Cell.ColorType.PLAYER1);
        markCell(p2.x,p2.y, Cell.ColorType.PLAYER2);
    }

    public void tick() {
        if(gameOver)return;
        movePlayer(p1,Cell.ColorType.PLAYER1);
        movePlayer(p2,Cell.ColorType.PLAYER2);
        elapsedTime++;
    }

    private void movePlayer(Player p,Cell.ColorType ct){
        if(!p.alive)return;
        int nx=p.x+p.direction.dx;
        int ny=p.y+p.direction.dy;

        if(nx<0||nx>=cols||ny<0||ny>=rows){
            p.alive=false;
            checkGameOver();
            return;
        }

        if(grid[ny][nx].colorType == Cell.ColorType.OBSTACLE) {
            p.alive=false;
            checkGameOver();
            return;
        }

        if(grid[ny][nx].occupied){
            p.alive = false;
            checkGameOver();
            return;
        }

        p.x=nx;p.y=ny;
        markCell(nx,ny,ct);
    }

    private void markCell(int x,int y,Cell.ColorType ct){
        grid[y][x].occupied=true;
        grid[y][x].colorType=ct;
    }

    private void checkGameOver(){
        if(!p1.alive && !p2.alive) {
            gameOver=true;
            winner=null;
        } else if(!p1.alive) {
            gameOver=true;winner=p2;
        } else if(!p2.alive) {
            gameOver=true;winner=p1;
        }
    }
}
