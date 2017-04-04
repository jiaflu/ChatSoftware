package main;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Login extends JFrame {
	JPanel jp = new JPanel();
	JButton login = new JButton("登录");
	JButton register = new JButton("注册");
	JLabel name = new JLabel("用户名:");
	JLabel password = new JLabel("密码:");
	JTextField jname = new JTextField(10); //明文账号输入
	JPasswordField jpassword = new JPasswordField(10); //非明文密码输入
	
	public Login(){
		jp.add(name,BorderLayout.CENTER); //将内容加到面板jp上
		jp.add(jname,BorderLayout.CENTER);
		jp.add(password,BorderLayout.CENTER);
		jp.add(jpassword,BorderLayout.CENTER);
		jp.add(login,BorderLayout.CENTER);
		jp.add(register,BorderLayout.CENTER);
		
		login.addActionListener(new loginListener());//登录增加事件监听
		this.setTitle("登录窗口");
		this.add(jp);
		this.setSize(500, 300);
		this.pack();
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); //退出
	}
	//登录监听
	private class loginListener implements ActionListener{ 
		@Override
		public void actionPerformed(ActionEvent e){
			if(jname.getText().equals("admin") && String.valueOf(jpassword.getPassword()).equals("admin"))
			{
				new Client();
				//JOptionPane.showMessageDialog(null, "登录成功，欢迎到来！");  
	            //显示信息提示框
				//System.exit(0);
				//new Client();
	            
			}
			else{
				JOptionPane.showMessageDialog(null, "用户或密码错误！请从新登录！");  
	            //显示信息提示框  
	            jname.setText("");   
	            jpassword.setText(""); 
			}
		}
	}
	//注册监听
	private class registerListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {

		}
	}
	public static void main(String[] args)
	{
		JFrame.setDefaultLookAndFeelDecorated(true);  
        new Login();
	}
}
