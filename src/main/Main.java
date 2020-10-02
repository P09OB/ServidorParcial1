package main;

import processing.core.PApplet;

public class Main extends PApplet implements OnMessageListener {
	
	TCPsingleton tcp;

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
		background(0);
	}

	@Override
	public void messageReceived(String msg) {
		// TODO Auto-generated method stub
		
	}

}
