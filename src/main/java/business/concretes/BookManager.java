package business.concretes;

import java.sql.Connection;
import java.util.ArrayList;
import business.abstracts.BookService;
import dataAccess.abtracts.BookDao;
import entities.concretes.BookInfo;

public class BookManager implements BookService {
	
	private BookDao bookDao;
	
	public BookManager(BookDao bookDao) {
		this.bookDao = bookDao;
	}
	@Override
	public ArrayList<BookInfo> getAllBookInfos() throws Exception {
		return bookDao.getAll();
	}
	@Override
	public ArrayList<BookInfo> getBooksListByBookName(String name) throws Exception {
		return bookDao.getBookListByBookName(name);
	}
	@Override
	public BookInfo getBookInfoById(int id) throws Exception {
		return bookDao.getEntityById(id);
	}
	@Override
	public boolean addBookInfo(BookInfo bookInfo, Connection connection) throws Exception {
		ArrayList<BookInfo> bookInfos = bookDao.getAll();
		String bookInfoName = bookInfo.getName().substring(0, 1).toUpperCase() + bookInfo.getName().substring(1).toLowerCase();
		String authorFirstName = bookInfo.getAuthor().getFirstName().substring(0, 1).toUpperCase() +  bookInfo.getAuthor().getFirstName().substring(1).toLowerCase();
		String authorLastName = bookInfo.getAuthor().getLastName().substring(0, 1).toUpperCase() +  bookInfo.getAuthor().getLastName().substring(1).toLowerCase();
		String typeName = bookInfo.getType().getName().substring(0,1).toUpperCase() + bookInfo.getType().getName().substring(1).toLowerCase();
		
		for (BookInfo bookInfo2 : bookInfos) {
			if (bookInfo2.getName().trim().equals(bookInfoName)
					&& bookInfo2.getAuthor().getFirstName().trim().equals(authorFirstName)
					&& bookInfo2.getAuthor().getLastName().trim().equals(authorLastName)
					&& bookInfo2.getType().getName().trim().equals(typeName)) {
				//bookDao.updateEntity(bookInfo2,connection);
				return false;
			}
		}
		return bookDao.addEntity(bookInfo, connection);	
	}
	@Override
	public int currentId(Connection con) {
		return bookDao.currentId(con);
	}
	@Override
	public boolean updateBookInfo(BookInfo bookInfo, Connection con) throws Exception {
		return bookDao.updateEntity(bookInfo, con);
	}
	
}
