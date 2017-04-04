package main;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.net.*;
import javax.swing.*;
import java.util.concurrent.*;
import java.util.*;

public class Server extends JFrame{
	private static JTextArea jta = new JTextArea();
	private ServerSocket server;
	private static ArrayList<Socket> list = new ArrayList<Socket>();//
	private ExecutorService exec; 
	/*ExecutorService提供了管理终止的方法，
	以及可为跟踪一个或多个异步任务执行状况而生成 Future 的方法。
	*/
	
	public Server(){
		setLayout(new BorderLayout());
		add(new JScrollPane(jta), BorderLayout.CENTER);
		setTitle("chat server");
		setSize(500, 300);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
		
		try{
			server = new ServerSocket(6666);
			exec = Executors.newCachedThreadPool();
			jta.append("server is already start.");
			Socket client = null;
			while(true){
				client = server.accept();
				list.add(client);
				exec.execute(new Task(client));
				}
		}catch(Exception e){
			//e.printStackTrace();
		}
	}
	
	static class Task implements Runnable{ //多线程
		private Socket client;
		private DataInputStream fromclient;
		private DataOutputStream toclient;
		String msg;
		
		public Task(Socket client) throws IOException{
			this.client = client;
			fromclient = new DataInputStream(client.getInputStream());
		}
		
		public void run(){ //自动调用
			try{
				while ((msg = fromclient.readUTF()) != null){
					System.out.println(msg);
					msg = "[" + client.getPort() + "]say:" + msg;
					//System.out.println(msg);
					jta.append(msg + "\n");
					sendMessage();
				}
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		
		public void sendMessage() throws IOException{
			for(Socket client:list){
				toclient = new DataOutputStream(client.getOutputStream());
				toclient.writeUTF(msg);
			}
		}
	}
	
	public static void main(String[] args){
		new Server();
	}
}
