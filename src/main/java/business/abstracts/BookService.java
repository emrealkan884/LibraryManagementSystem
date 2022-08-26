package business.abstracts;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import entities.concretes.BookInfo;

public interface BookService {
	
	ArrayList<BookInfo> getAllBookInfos() throws Exception;
	
	ArrayList<BookInfo> getBooksListByBookName(String name) throws Exception;
	
	BookInfo getBookInfoById(int id) throws Exception;
	
	boolean addBookInfo(BookInfo bookInfo,Connection con) throws SQLException, Exception;
	
	boolean updateBookInfo(BookInfo bookInfo,Connection con) throws Exception;
	
	int currentId(Connection con);
}
