package entities.concretes;

import entities.abstracts.Entity;

public class Theme implements Entity{
	private int id;
	private String name;
	
	public Theme() {
		
	}

	public Theme(int id, String name) {
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
