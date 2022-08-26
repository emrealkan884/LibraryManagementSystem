import java.sql.Connection;
import java.sql.SQLException;

import business.abstracts.AuthorService;
import business.abstracts.BookService;
import business.concretes.AuthorManager;
import business.concretes.BookManager;
import business.concretes.TypeManager;
import dataAccess.concretes.AuthorDaoImpl;
import dataAccess.concretes.BookDaoImpl;
import dataAccess.concretes.TypeDaoImpl;
import entities.concretes.Author;
import entities.concretes.BookInfo;
import entities.concretes.Type;
import utilities.DbHelper;

public class Main {

	public static void main(String[] args) throws Exception {
		// runAuthorMethods(connection);
		// runBookMethods(connection);
		// runFliterByBookName(connection);
		// runGetBookInfoById(connection);
		
		Connection conn = null;
		try {
			conn = DbHelper.getConnection();
			conn.setAutoCommit(false);
			
			BookService bookManager = new BookManager(new BookDaoImpl());
			BookInfo bookInfo = new BookInfo();
			bookInfo.setName("Nasrettin Hoca Masalları");
			Type type = new Type();
			type.setName("Masal");
			bookInfo.setType(type);
			TypeManager typeManager = new TypeManager(new TypeDaoImpl());
			typeManager.addType(type, conn);
			
			
			
			Author author = new Author();
			author.setFirstName("Nasrettin");
			author.setLastName("Hoca");
			bookInfo.setAuthor(author);
			AuthorManager authorManager = new AuthorManager(new AuthorDaoImpl());
			
			if (typeManager.addType(type, conn) !=false) {
				type.setId(typeManager.currentId(conn));
				
			}
			else {
				conn.rollback();
			}
			
			if (authorManager.addAuthor(author, conn)!=false) {
				author.setId(authorManager.currentId(conn));
			}
			else {
				conn.rollback();
			}
			
			if (bookManager.addBookInfo(bookInfo, conn) != false) {
				conn.commit();
			}
			else {
				conn.rollback();
				System.out.println("Değişiklikler geri alındı");
			}
	
		} catch (SQLException e) {
			conn.rollback();
			System.out.println("Değişiklikler geri alındı");
		}
		
		
	}

	public static void runGetBookInfoById() throws Exception {
		BookService bookService = new BookManager(new BookDaoImpl());
		BookInfo bookInfo = bookService.getBookInfoById(64);
		System.out.print(bookInfo.getName() + " / ");
		System.out.print(bookInfo.getType().getName() + " / ");
		System.out.print(bookInfo.getAuthor().getFirstName() + " " + bookInfo.getAuthor().getLastName() + " / ");
		System.out.println(bookInfo.getThemeList().get(0).getName());
	}

	public static void runFilterByBookName() throws Exception {
		BookService bookService = new BookManager(new BookDaoImpl());
		for (BookInfo bookInfos : bookService.getBooksListByBookName("o")) {
			System.out.println(bookInfos.getName());
		}
	}

	public static void runBookMethods() throws Exception {
		BookService bookService = new BookManager(new BookDaoImpl());
		var a = bookService.getAllBookInfos();
		int eleman = 0;
		for (BookInfo bookInfo : a) {
			System.out.print(bookInfo.getName() + " / ");
			System.out.print(bookInfo.getType().getName() + " / ");
			System.out.print(bookInfo.getAuthor().getFirstName() + " " + bookInfo.getAuthor().getLastName() + " / ");
			System.out.println(bookInfo.getThemeList().get(eleman).getName());
			eleman += 1;
		}
	}

	public static void runAuthorMethods() throws Exception {
		AuthorService authorService = new AuthorManager(new AuthorDaoImpl());
		var authors = authorService.getAllAuthors();
		for (Author author : authors) {
			System.out.print(author.getFirstName() + " ");
			System.out.println(author.getLastName());
		}
		System.out.println(authorService.getAuthorById(36).getFirstName());
	}

}
