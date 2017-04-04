package test;


import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import main.Client;

public class test1{
	
	public static void main(String[] args){
		new NN();
	}
}

class NN{
	JPanel jp = new JPanel();
	JButton login = new JButton("登录");
	JButton register = new JButton("注册");
	JLabel name = new JLabel("用户名:");
	JLabel password = new JLabel("密码:");
	JTextField jname = new JTextField(10); //明文账号输入
	JPasswordField jpassword = new JPasswordField(10); //非明文密码输入
	NN(){
		new Client();
	}
}