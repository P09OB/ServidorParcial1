package main;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class TCPsingleton extends Thread {
	
	private static TCPsingleton instance  ;
	
	private TCPsingleton() {}
	
	public static TCPsingleton getInstance() {
		
		if(instance == null) {
			instance = new TCPsingleton();
			instance.start();
		}
		return instance;
		
	}
	
	private BufferedWriter writer;
	private Socket socket;
	private OnMessageListener observer;
	
	public void setObserver(OnMessageListener observer) {
		
		this.observer = observer;
	}
	
	
	@Override 
	public void run() {
		
		try {
			
			System.out.println("Esperando conexion....");
			ServerSocket server = new ServerSocket(5000);
			socket = server.accept();
			System.out.println("Conectados");
			
			InputStream is = socket.getInputStream();
			OutputStream out = socket.getOutputStream();
			
			writer = new BufferedWriter(new OutputStreamWriter(out));
			BufferedReader reader = new BufferedReader(new InputStreamReader(is));
			
			while(true) {
				
				String mensaje = reader.readLine();
				observer.messageReceived(mensaje);
				
			}
			
		
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
	}
	
	public void enviar(String mgs) {
		
		new Thread(
				
				()->{
					
					try {
						writer.write(mgs);
						writer.flush();
						
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				).start();
		
		
	}
	
	
	
	
	
	

}
