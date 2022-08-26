package entities.concretes;

import java.util.ArrayList;

public class BookInfo {
	
	private int id;
	private Type type;
	private String name;
	private Author author;
	private ArrayList<Theme> themeList;
	private int quantity;
	
	public BookInfo() {
		this.type = new Type();
		this.author = new Author();
		this.themeList =new ArrayList<Theme>();
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Type getType() {
		return type;
	}
	public void setType(Type type) {
		this.type = type;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Author getAuthor() {
		return author;
	}
	public void setAuthor(Author author) {
		this.author = author;
	}
	public ArrayList<Theme> getThemeList() {
		return themeList;
	}
	public void setThemeList(ArrayList<Theme> themeList) {
		this.themeList = themeList;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
}
