package main;
import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
/**
 * Created by lujiafeng on 2017/4/1.
 */
public class User {
    private String username;
    private String password;

    User(String name, String password){
        this.username = username;
        this.password = password;
    }
    public String getName() { return username; }
    public String getPassword(){ return password; }

    private static Connection getConn(){
        String driver = "com.mysql.jdbc.Driver";
        String url = "jdbc:mysql://127.0.0.1:3306/";
        String user = "root";
        String password = "wjjf2008";
        Connection conn = null;
        try{
            Class.forName(driver); //classLoader，加载对应驱动
            conn = (Connection) DriverManager.getConnection(url, user, password);
        }catch(ClassNotFoundException e){
            e.printStackTrace();
        }catch(SQLException e){
            e.printStackTrace();
        }
        return conn;
    }
    public String insert() {
        Connection conn = getConn();
        int i = 0;
        String sql = "insert into userdb (username, password) values(?, ?)";
        PreparedStatement insertStatement;
        try {
            insertStatement = (PreparedStatement) conn.prepareStatement(sql);
            insertStatement.setString(1, username);
            insertStatement.setString(2, password);
            insertStatement.execute();
            insertStatement.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return username;
    }
}



