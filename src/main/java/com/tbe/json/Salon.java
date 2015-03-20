package com.tbe.json;

import java.util.ArrayList;

public class Salon {
	public int id;
	ArrayList<String> messages = new ArrayList<String>();
	public Salon(int id){
		this.id=id;
	}

	public ArrayList<String> getMessages() {
		return messages;
	}
	public void setMessages(ArrayList<String> messages) {
		this.messages = messages;
	}
	
	public Salon(){}
}
