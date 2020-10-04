package main;

import com.google.gson.Gson;

import model.Coordenadas;
import processing.core.PApplet;

public class Main extends PApplet implements OnMessageListener {
	
	TCPsingleton tcp;
	int r,g,b;
	int x,y;
	Coordenadas coordenada;
	Coordenadas coordenadaReci;
	
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
		
		
		
		r = (int) random(0,250);
		g = (int) random(0,250);
		b = (int) random(0,250);
		
		


		
		
		
	}
	
	public void draw() {
		background(0);
		ellipse(x,y,50,50);
		fill(r,g,b);
	}
	
	public void mousePressed() {
		
		tcp.enviar("hola");
		rect(100,100,100,100);
		
	}

	@Override
	public void messageReceived(String msg) {
				
		System.out.println(msg);
		
		Gson gson = new Gson();
		
		 coordenadaReci = gson.fromJson(msg, Coordenadas.class);
		 
		  x =coordenadaReci.getX();
		  y = coordenadaReci.getY();
		 
		
		
		
		
		
		
	}

}
