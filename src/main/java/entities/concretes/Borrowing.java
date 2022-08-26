package entities.concretes;
import java.sql.Date;

import entities.abstracts.Entity;

public class Borrowing implements Entity{
	private int id;
	private int bookId;
	private int userId;
	private Date borrowingDate;
	private Date scheduledDeliveryDate;
	private Date deliveryDate;
	
	public Borrowing() {
		
	}

	public Borrowing(int id, int bookId, int userId, Date borrowingDate, Date scheduledDeliveryDate,
			Date deliveryDate) {
		super();
		this.id = id;
		this.bookId = bookId;
		this.userId = userId;
		this.borrowingDate = borrowingDate;
		this.scheduledDeliveryDate = scheduledDeliveryDate;
		this.deliveryDate = deliveryDate;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getBookId() {
		return bookId;
	}

	public void setBookId(int bookId) {
		this.bookId = bookId;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public Date getBorrowingDate() {
		return borrowingDate;
	}

	public void setBorrowingDate(Date borrowingDate) {
		this.borrowingDate = borrowingDate;
	}

	public Date getScheduledDeliveryDate() {
		return scheduledDeliveryDate;
	}

	public void setScheduledDeliveryDate(Date scheduledDeliveryDate) {
		this.scheduledDeliveryDate = scheduledDeliveryDate;
	}

	public Date getDeliveryDate() {
		return deliveryDate;
	}

	public void setDeliveryDate(Date deliveryDate) {
		this.deliveryDate = deliveryDate;
	}
}
