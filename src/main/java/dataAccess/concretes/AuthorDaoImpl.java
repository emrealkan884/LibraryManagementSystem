package dataAccess.concretes;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import dataAccess.abtracts.AuthorDao;
import entities.concretes.Author;
import utilities.DbHelper;

public class AuthorDaoImpl implements AuthorDao {
	@Override
	public ArrayList<Author> getAll() throws Exception {
		Connection connection = null;
		Statement statement = null;
		ResultSet rSet = null;
		String sorgu = "Select First_Name,Last_Name from Authors";
		ArrayList<Author> authors = new ArrayList<Author>();
		try {
			connection = DbHelper.getConnection();
			statement = connection.createStatement();
			rSet = statement.executeQuery(sorgu);
			Author author = null;
			while (rSet.next()) {
				author = new Author();
				author.setFirstName(rSet.getString("First_Name"));
				author.setLastName(rSet.getString("Last_Name"));
				authors.add(author);
			}
		} catch (Exception e) {
			System.out.println(e);
		}finally {
			if (connection != null) {
				connection.close();
			}
		}
		return authors;
	}

	@Override
	public Author getEntityById(int id) throws Exception {
		String query = "Select * from Authors where Id = ?";
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		Author author = null;
		try {
			connection = DbHelper.getConnection();
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setInt(1, id);
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				author = new Author();
				author.setFirstName(resultSet.getString("First_Name"));
				author.setLastName(resultSet.getString("Last_Name"));
				author.setId(id);
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
		return author;
	}

	@Override
	public boolean updateEntity(Author entity,Connection connection) throws Exception {
		String query = "Update Authors Set First_Name=?,Last_Name=?";
		PreparedStatement preparedStatement = null;
		try {
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, entity.getFirstName());
			preparedStatement.setString(2, entity.getLastName());
			int guncellenen = preparedStatement.executeUpdate();
			if (guncellenen>0) {
				System.out.println(guncellenen + " Yazar güncellendi.");
				return true;
			}
			else {
				System.out.println("Yazar güncellenirken sorun oluştu.");
				return false;
			}
		} catch (Exception e) {
			System.out.println(e);
			return false;
		}finally {
			if (preparedStatement != null) {
				preparedStatement.close();
			}
			
		}
	}

	@Override
	public boolean deleteEntity(Author entity) throws Exception {
		String query = "Delete from Authors where Id = ?";
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		try {
			connection = DbHelper.getConnection();
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setInt(1, entity.getId());
			int silinen = preparedStatement.executeUpdate();
			if (silinen>0) {
				System.out.println(silinen + " Yazar silindi.");
				return true;
			}
			else {
				System.out.println("Yazar silinirken hata oluştu");
				return false;
			}
		} catch (Exception e) {
			System.out.println(e);
			return false;
		}finally {
			if (preparedStatement != null) {
				preparedStatement.close();
			}
			
		}
		
	}

	@Override
	/*Eğer Connection' ı parametre olarak gönderdiysek, o connection'ı burada kapatmamamız lazım.
	Connection'ı verdiğimiz yerde kapatmak lazım*/
	public boolean addEntity(Author entity,Connection con){
		String query = "Insert into Authors (First_Name,Last_Name) values (?,?)";
		PreparedStatement preparedStatement = null;
		try {
			preparedStatement = con.prepareStatement(query);
			preparedStatement.setString(1,entity.getFirstName());
			preparedStatement.setString(2, entity.getLastName());
			int eklenen = preparedStatement.executeUpdate();
			if (eklenen>0) {
				System.out.println(eklenen +" Yazar eklendi.");
				return true;
			}
			else {
				System.out.println("Yazar eklenirken sorun oluştu");
				return false;
			}
		} catch (Exception e) {
			System.out.println(e);
			return false;
			
		}
	}

	@Override
	public int currentId(Connection con) {
		String sorgu = "Select Id From (select Id from Authors Order By Id desc) where rownum <=1 ORDER BY rownum";
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
