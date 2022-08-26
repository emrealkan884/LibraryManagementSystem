package entities.concretes;

import entities.abstracts.Entity;

public class BookTheme implements Entity{
	private int bookId;
	private int themeId;
	
	public BookTheme() {
		
	}

	public BookTheme(int bookId, int themeId) {
		super();
		this.bookId = bookId;
		this.themeId = themeId;
	}

	public int getBookId() {
		return bookId;
	}

	public void setBookId(int bookId) {
		this.bookId = bookId;
	}

	public int getThemeId() {
		return themeId;
	}

	public void setThemeId(int themeId) {
		this.themeId = themeId;
	}
}
