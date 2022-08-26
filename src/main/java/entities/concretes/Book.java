package entities.concretes;

import entities.abstracts.Entity;

public class Book implements Entity {
	private int id;
	private int typeId;
	private int authorId;
	private String name;
	private int quantity;
	private int availableQuantity;
	
	public Book() {
		
	}

	public Book(int id, int typeId, int authorId, String name, int quantity,int availableQuantity) {
		super();
		this.id = id;
		this.typeId = typeId;
		this.authorId = authorId;
		this.name = name;
		this.quantity=quantity;
		this.availableQuantity=availableQuantity;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getTypeId() {
		return typeId;
	}

	public void setTypeId(int typeId) {
		this.typeId = typeId;
	}

	public int getAuthorId() {
		return authorId;
	}

	public void setAuthorId(int authorId) {
		this.authorId = authorId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public int getAvailableQuantity() {
		return availableQuantity;
	}

	public void setAvailableQuantity(int availableQuantity) {
		this.availableQuantity = availableQuantity;
	}
	
	
}
