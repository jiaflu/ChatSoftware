package test;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class testMySQL {
	public static void main(String[] args){
		//声明Connection对象
		Connection con;
		//驱动程序名？？？？
		String driver = "com.mysql.jdbc.Driver";
		//URL指向要访问的数据库名mydata
		String url = "jdbc:mysql://127.0.0.1:3306/";
		//MySQL配置时的用户名
		String user = "root";
		//MySQL配置时的密码
		String password = "wjjf2008";

		//遍历查询结果
		try{
			//加载驱动程序
			Class.forName(driver);
			//.getConnection()方法连接MySQL数据库
			con = DriverManager.getConnection(url, user, password);
			if(!con.isClosed())
				System.out.println("Succeeded connecting to the Database!");
			//创建statement类对象，用来执行SQL语句
			Statement statement = con.createStatement();
			//要执行的SQL语句
			String sql = "select * from emp";
			//ResultSet类，用来存放获取的结果集
			ResultSet rs = statement.executeQuery(sql);
			System.out.println("-------------------------");
			System.out.println("执行结果如下所示:");
			System.out.println("-------------------------");
			String a = null;
			String b = null;
			while(rs.next()){
				//获取名为a这列的数据
				a = rs.getString("a");
				b = rs.getString("b");
			}
			rs.close();
			con.close();
		}catch(ClassNotFoundException e){
			//数据库驱动类异常处理
			System.out.println("Sorry,can`t find the Driver!");
			e.printStackTrace();
		}catch(SQLException e){
			e.printStackTrace();
		}finally{

		}
	}
}
