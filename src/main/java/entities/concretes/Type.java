package entities.concretes;

import entities.abstracts.Entity;

public class Type implements Entity{
	private int id;
	private String name;
	
	public Type() {
		
	}

	public Type(int id, String name) {
		super();
		this.id = id;
		this.name = name;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
}
