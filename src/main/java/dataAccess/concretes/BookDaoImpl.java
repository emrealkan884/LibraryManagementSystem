package dataAccess.concretes;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import dataAccess.abtracts.BookDao;
import entities.concretes.Author;
import entities.concretes.BookInfo;
import entities.concretes.Theme;
import entities.concretes.Type;
import utilities.DbHelper;

public class BookDaoImpl implements BookDao {
	
	@Override
	public ArrayList<BookInfo> getAll() throws Exception {
		
		ArrayList<BookInfo> bookInfos = new ArrayList<BookInfo>();
		Connection connection = null;
		Statement statement = null;
		ResultSet rSet = null;
		String sorgu = "Select books.name,types.name,authors.first_name,authors.last_name,LISTAGG(themes.name,',') from books " 
				+"Inner JOIN authors On authors.id = books.author_id "
				+"Inner JOIN types On types.id = books.type_id "
				+"Inner JOIN booktheme On booktheme.book_Id = books.id "
				+"Inner JOIN themes On themes.id = booktheme.theme_id "
				+"GROUP BY books.name,types.name,authors.first_name,authors.last_name";
		try {
			connection = DbHelper.getConnection();
			statement = connection.createStatement();
			rSet = statement.executeQuery(sorgu);
			BookInfo bookInfo = null;
			Type type = null;
			Author author =null;
			Theme theme = null;
			ArrayList<Theme> themeList = new ArrayList<Theme>();
			while (rSet.next()) {
				
				bookInfo = new BookInfo();
				bookInfo.setName(rSet.getString(1));
				
				type = new Type();
				type.setName(rSet.getString(2));
				bookInfo.setType(type);
				
				author = new Author();
				author.setFirstName(rSet.getString(3));
				author.setLastName(rSet.getString(4));
				bookInfo.setAuthor(author);
				
				theme = new Theme();
				theme.setName(rSet.getString(5));
				themeList.add(theme);
				bookInfo.setThemeList(themeList);
				bookInfos.add(bookInfo);
			}
		} catch (Exception e) {
			System.out.println(e);
		} finally {
			if (rSet != null) {
				rSet.close();
			}
			if (statement != null) {
				statement.close();
			}
			if (connection != null)
				connection.close();
		}
		return bookInfos;
	}
	
	public ArrayList<BookInfo> getBookListByBookName(String name) throws SQLException {
		Connection connection = null;
		PreparedStatement stmt = null;
		ResultSet rSet = null;
		String sorgu = "Select Name from Books where Name like ? ";
		ArrayList<BookInfo> bookInfos = new ArrayList<BookInfo>();
		try {
			connection = DbHelper.getConnection();
			stmt = connection.prepareStatement(sorgu);
			stmt.setString(1, name.substring(0, 1).toUpperCase() + name.substring(1).toLowerCase() + "%");
			rSet = stmt.executeQuery();
			BookInfo bookInfo;
			while (rSet.next()) {
				bookInfo = new BookInfo();
				bookInfo.setName(rSet.getString("Name"));
				bookInfos.add(bookInfo);
			}
		} catch (Exception e) {
			System.out.println(e);
		} finally {
			if (rSet != null) {
				rSet.close();
			}
			if (stmt != null) {
				stmt.close();
			}
			if (connection != null)
				connection.close();
		}
		return bookInfos;
	}
	
	@Override
	public BookInfo getEntityById(int id) throws Exception {
		String query = "Select books.id,books.name,types.name,authors.fırst_name,authors.last_name,LISTAGG(themes.name,',')from books \r\n"
				+ "Inner JOIN authors On authors.id = books.author_id\r\n"
				+ "Inner JOIN types On types.id = books.type_ıd\r\n"
				+ "Inner Joın booktheme On booktheme.book_Id = books.id\r\n"
				+ "Inner Joın themes On themes.id = booktheme.theme_ıd\r\n"
				+ "GROUP BY books.name,types.name,authors.fırst_name,authors.last_name,books.id\r\n"
				+ "HAVING books.id = ?";
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		BookInfo bookInfo = null;
		try {
			connection = DbHelper.getConnection();
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setInt(1, id);
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				bookInfo = new BookInfo();
				bookInfo.setId(resultSet.getInt(1));
				bookInfo.setName(resultSet.getString(2));
				Type type = new Type();
				type.setName(resultSet.getString(3));
				bookInfo.setType(type);
				Author author = new Author();
				author.setFirstName(resultSet.getString(4));
				author.setLastName(resultSet.getString(5));
				bookInfo.setAuthor(author);
				Theme theme = new Theme();
		
     			ArrayList<Theme> themeList = new ArrayList<Theme>();	
     			
				theme.setName(resultSet.getString(6));
				themeList.add(theme);
				bookInfo.setThemeList(themeList);
			}
		} catch (Exception e) {
			System.out.println(e);
		} finally {
			if (resultSet != null) {
				resultSet.close();
			}
			if (preparedStatement != null) {
				preparedStatement.close();
			}
			if (connection != null) {
				connection.close();
			}
		}
		return bookInfo;
	}
	@Override
	public boolean updateEntity(BookInfo entity,Connection connection) throws Exception {
		PreparedStatement preparedStatement = null;
		String sorgu = "UPDATE Books SET name = ?, quantıty = quantity + ? WHERE ID = ?";
		try {
			preparedStatement = connection.prepareStatement(sorgu);
			preparedStatement.setString(1, entity.getName());
			preparedStatement.setInt(2, +entity.getQuantity());
			preparedStatement.setInt(3, entity.getId());
			int guncellenen = preparedStatement.executeUpdate();
			if (guncellenen > 0) {
				System.out.println(guncellenen + " Kitap güncellendi");
				return true;
			} else {
				System.out.println("Kitap güncellenirken sorun oluştu");
				return false;
			}
		} catch (Exception e) {
			System.out.println(e);
			return false;
		}
	}
	@Override
	public boolean deleteEntity(BookInfo entity) throws Exception {
		return true;
	}
	@Override
	/*Eğer Connection' ı parametre olarak gönderirsek, o connection'ı burada kapatmamamız lazım.
	Connection'ı verdiğimiz yerde kapatmak lazım*/
	public boolean addEntity(BookInfo entity,Connection con) throws SQLException {
		PreparedStatement stmtBook = null;
		String sorguBook = "Insert Into Books (Type_Id,Author_Id,Name,Quantity) values(?,?,?,?)";
		try {
			stmtBook = con.prepareStatement(sorguBook);
			stmtBook.setInt(1, entity.getType().getId());
			stmtBook.setInt(2, entity.getAuthor().getId());
			stmtBook.setString(3,
					entity.getName().substring(0, 1).toUpperCase() + entity.getName().substring(1).toLowerCase());
			stmtBook.setInt(4, entity.getQuantity());
			int eklenen = stmtBook.executeUpdate();
			if (eklenen > 0) {
				System.out.println(eklenen + " Kitap Eklendi");
				return true;
			} else {
				System.out.println("Kitap eklenirken sorun oluştu");
				return false;
			}
		} catch (Exception e) {
			System.out.println(e);
			return false;
		}
	}
	@Override
	public int currentId(Connection con) {
		String sorgu = "Select Id From (select Id from Books Order By Id desc) where rownum <=1 ORDER BY rownum";
  		Statement statement = null; 
  		ResultSet resultSet = null;
  		try {
  			statement = con.createStatement();
  			resultSet = statement.executeQuery(sorgu);
  			while(resultSet.next()) {
  				return resultSet.getInt("Id");
  			}
  		} catch (Exception e) {
  			System.out.println(e);
  		}
  		return 0;
	}


}
