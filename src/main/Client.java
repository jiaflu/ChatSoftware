package main;

import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.net.*;
import javax.swing.*;

public class Client extends JFrame{
	private JPanel p = new JPanel();
	private JTextField jtf1 = new JTextField();
	private JTextArea jta1 = new JTextArea();
	private DataOutputStream toserver;
	private DataInputStream fromserver;
	private JButton button = new JButton("Send_Message");
	
	public Client(){
		p.setLayout(new BorderLayout());
		JPanel p2 = new JPanel();
		p2.setLayout(new BorderLayout());
		p2.add(jtf1, BorderLayout.CENTER);
		p2.add(new JLabel("聊天室"));
		p2.add(button, BorderLayout.EAST);
		p.add(p2, BorderLayout.SOUTH);
		p.add(jta1, BorderLayout.CENTER);
		
		this.setTitle("a simple chat box.");
		this.add(p);
		this.setSize(500, 300);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
		button.addActionListener(new buttonListener());  //??????
		
		try{
			Socket client = new Socket("127.0.0.1", 6666);
			toserver = new DataOutputStream(client.getOutputStream());
			fromserver = new DataInputStream(client.getInputStream());
			
			while(true){
				String msg = fromserver.readUTF();
				if(msg != null){
					jta1.append(msg + "\n");
				}
			}
		}catch(UnknownHostException e){
			e.printStackTrace();
		}catch(IOException e){
			e.printStackTrace();
		}
		}
	
	private class buttonListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e){
			String str = jta1.getText().toString();
			try{
				toserver.writeUTF(str);
				toserver.flush();  //
			}catch(UnknownHostException e1){
				e1.printStackTrace();
			}catch(IOException e1){
				e1.printStackTrace();
			}
		}
		
	}
	
	public static void main(String[] args){
		new Client();
	}
	
}
