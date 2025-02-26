package persistence;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class Database {
    private final Connection conn;
    private static final String DB_USER = "komron";
    private static final String DB_PASSWORD = "psql_pass";
    private static final String DB_NAME = "tron_game";
    private static final Integer DB_PORT = 5432;
    private static final String DB_DRIVER = "postgresql";
    private static final String DB_HOST = "localhost";

    private static final String DB_URL = String.format(
            "jdbc:%s://%s:%d/%s",
            DB_DRIVER, DB_HOST, DB_PORT, DB_NAME
    );

    public Database(){
        Connection c=null;
        try{
            Class.forName("org.postgresql.Driver");
            System.out.println("Connecting to database...");
            c=DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
        }catch(Exception ex){
            ex.printStackTrace();
        }
        conn=c;
        System.out.println(c);
        createTable();
    }
    private void createTable(){
        try(Statement stmt=conn.createStatement()){
            System.out.println("Creating table");
            stmt.execute("CREATE TABLE IF NOT EXISTS playerscores(name VARCHAR(100) PRIMARY KEY,score INT)");
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    public void updateScore(String name){
        int old=getScore(name);
        int neu=old+1;

        String sql="INSERT INTO playerscores (name,score) VALUES('"+name+"',"+neu+") " +
                "ON CONFLICT (name) DO UPDATE SET score=EXCLUDED.score";
        try(Statement stmt=conn.createStatement()){
            stmt.executeUpdate(sql);
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    public int getScore(String name){
        int s=0;
        try(Statement stmt=conn.createStatement()){
            ResultSet rs=stmt.executeQuery("SELECT score FROM playerscores WHERE name='"+name+"'");
            if(rs.next()) s=rs.getInt(1);
        }catch(Exception e){
            e.printStackTrace();
        }
        return s;
    }
    public ArrayList<HighScore> getTopScores(){
        ArrayList<HighScore> list=new ArrayList<>();
        try(Statement stmt=conn.createStatement()){
            ResultSet rs=stmt.executeQuery("SELECT name,score FROM playerscores ORDER BY score DESC LIMIT 10");
            while(rs.next()){
                list.add(new HighScore(rs.getString(1),rs.getInt(2)));
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return list;
    }
}
