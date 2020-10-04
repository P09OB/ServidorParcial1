package main;


import com.google.gson.Gson;

import model.Coordenadas;
import model.Usuario;
import processing.core.PApplet;

public class Main extends PApplet implements OnMessageListener {
	
	TCPsingleton tcp;
	int r,g,b;
	int x = 0;
	int y = 0;
	int cambio;
	String name = " ";
	Coordenadas coordenadaReci;
	Usuario user;
	
	String id;
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		PApplet.main("main.Main");

	}
	
	public void settings() {
		size(500,500);
	
		
		
	}
	
	public void setup() {
		
		tcp = TCPsingleton.getInstance();
		tcp.setObserver(this);

		
	}
	
	public void draw() {
		background(255);
		
		fill(r,g,b);
		text(name,x-10,y-30);
		noStroke();
		ellipse(x,y,50,50);
		
	}
	

	@Override
	public void messageReceived(String msg) {
				
		System.out.println(msg);
		Gson gson = new Gson();
		
		if(cambio ==0) {
			
			 user = gson.fromJson(msg, Usuario.class);
		
		}
		
		cambio = user.getNumero();
		name = user.getName();
		
		if(cambio == 1) {
			
			coordenadaReci = gson.fromJson(msg, Coordenadas.class);
			 
			  x =coordenadaReci.getX();
			  y = coordenadaReci.getY();
			  r = coordenadaReci.getR();
			  g = coordenadaReci.getG();
			  b = coordenadaReci.getB();

			
		}
		
		 
		
	}

}
