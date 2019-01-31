package com.dad.amigoanimal;

public class usuario {
	
	private String name;
	private Type type;
	//private String tipo;
	
	public enum Type {
	    Cliente, Invitado, Trabajador
	}

	public usuario(String name ,Type type) {
		this.name = name;
		this.type = type;
		//this.tipo = tipo; //ints o enum?
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public Type getType() {
		return type;
	}

	public void setType(Type type) {
		this.type = type;
	}

}
