package model;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

public class LevelLoader {
    public static TronGame loadLevel(String levelFile, String p1Name, java.awt.Color p1Color, Direction p1Dir,
                                     String p2Name, java.awt.Color p2Color, Direction p2Dir) throws Exception {
        InputStream is = LevelLoader.class.getResourceAsStream("/resources/levels/" + levelFile);
        if(is == null) throw new RuntimeException("Level file not found: " + levelFile);
        BufferedReader br = new BufferedReader(new InputStreamReader(is));

        java.util.ArrayList<String> lines = new java.util.ArrayList<>();
        String line;
        while((line=br.readLine())!=null) {
            lines.add(line);
        }

        int rows = lines.size();
        int cols = lines.get(0).length();
        Cell[][] grid = new Cell[rows][cols];
        for(int y=0; y<rows; y++){
            for(int x=0; x<cols; x++){
                grid[y][x] = new Cell();
            }
        }

        Player p1=null,p2=null;

        for(int y=0;y<rows;y++){
            String rowStr = lines.get(y);
            for(int x=0;x<cols;x++){
                char ch = ' ';
                if(x<rowStr.length()) ch=rowStr.charAt(x);
                switch(ch) {
                    case '#':
                        grid[y][x].occupied=true;
                        grid[y][x].colorType= Cell.ColorType.OBSTACLE;
                        break;
                    case '1':
                        p1 = new Player(p1Name, p1Color, x, y, p1Dir);
                        break;
                    case '2':
                        p2 = new Player(p2Name, p2Color, x, y, p2Dir);
                        break;
                    case ' ':
                        break;
                    default:
                        break;
                }
            }
        }

        if(p1==null || p2==null) {
            throw new RuntimeException("Level must define both player 1 and player 2 start positions!");
        }

        return new TronGame(rows,cols,p1,p2,grid);
    }

}
