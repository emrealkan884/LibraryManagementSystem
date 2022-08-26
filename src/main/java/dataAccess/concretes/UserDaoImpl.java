package dataAccess.concretes;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import dataAccess.abtracts.UserDao;
import entities.concretes.User;
import utilities.DbHelper;

public class UserDaoImpl implements UserDao{
	@Override
	public ArrayList<User> getAll() throws Exception {
		String query = "Select * from Users";
		ArrayList<User> users = new ArrayList<User>();
		Connection connection = null;
		Statement statement = null;
		ResultSet resultSet = null;
		try {
			connection = DbHelper.getConnection();
			statement = connection.createStatement();
			resultSet = statement.executeQuery(query);
			while (resultSet.next()) {
				users.add(new User(resultSet.getInt("Id"), resultSet.getString("First_Name"),
						resultSet.getString("Last_Name"), resultSet.getString("Identity_Number"),
						resultSet.getInt("Score")));
			}
		} catch (Exception e) {
			System.out.println(e);
		}finally {
			if (connection != null) {
				connection.close();
			}
		}
		return users;
	}

	@Override
	public User getEntityById(int id) throws Exception {
		String query = "Select * from Users where Id = ?";
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		User user = null;
		try {
			connection = DbHelper.getConnection();
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setInt(1, id);
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				user = new User();
				user.setId(resultSet.getInt("Id"));
				user.setFirstName(resultSet.getString("Fırst_Name"));
				user.setLastName(resultSet.getString("Last_Name"));
				user.setIdentityNumber(resultSet.getString("Identity_Number"));
				user.setScore(resultSet.getInt("Score"));
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
		return user;
	}

	@Override
	public boolean updateEntity(User entity,Connection connection) throws Exception {
		String query = "Update Users Set First_Name=?,Last_Name=?,Identity_Number,Score=? ";
		PreparedStatement preparedStatement = null;
		try {
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, entity.getFirstName());
			preparedStatement.setString(2, entity.getLastName());
			preparedStatement.setString(3, entity.getIdentityNumber());
			preparedStatement.setInt(4, entity.getScore());
			int guncellenen = preparedStatement.executeUpdate();
			if (guncellenen>0) {
				System.out.println(guncellenen + " Kullanıcı güncellendi.");
				return true;
			}
			else {
				System.out.println("Kullanıcı güncellenirken sorun oluştu.");
				return false;
			}
		} catch (Exception e) {
			System.out.println(e);
			return false;
		}finally {
			if (preparedStatement != null) {
				preparedStatement.close();
			}
			if (connection != null) {
				connection.close();
			}
		}
	}

	@Override
	public boolean deleteEntity(User entity) throws Exception {
		String query = "Delete from Users where Id = ?";
		PreparedStatement preparedStatement = null;
		Connection connection = null;
		try {
			connection = DbHelper.getConnection();
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setInt(1, entity.getId());
			int silinen = preparedStatement.executeUpdate();
			if (silinen>0) {
				System.out.println(silinen + " Kullanıcı silindi.");
				return true;
			}
			else {
				System.out.println("Kullanıcı silinirken hata oluştu");
				return false;
			}
		} catch (Exception e) {
			System.out.println(e);
			return false;
		}finally {
			if (preparedStatement != null) {
				preparedStatement.close();
			}
			if (connection != null) {
				connection.close();
			}
		}
		
	}

	@Override
	/*Eğer Connection' ı parametre olarak gönderdiysek, o connection'ı burada kapatmamamız lazım.
	Connection'ı verdiğimiz yerde kapatmak lazım*/
	public boolean addEntity(User entity,Connection con)  {
		String query = "Insert into Users (First_Name,Last_Name,Identity_Number) values (?,?,?)";
		PreparedStatement preparedStatement = null;
		try {
			preparedStatement = con.prepareStatement(query);
			preparedStatement.setString(1,entity.getFirstName());
			preparedStatement.setString(2, entity.getLastName());
			preparedStatement.setString(3, entity.getIdentityNumber());
			int eklenen = preparedStatement.executeUpdate();
			if (eklenen>0) {
				System.out.println(eklenen +" Kullanıcı eklendi.");
				return true;
			}
			else {
				System.out.println("Kullanıcı eklenirken sorun oluştu");
				return false;
			}
		} catch (Exception e) {
			System.out.println(e);
			return false;
		}
	}

	@Override
	public int currentId(Connection con) {
		String sorgu = "Select Id From (select Id from Users Order By Id desc) where rownum <=1 ORDER BY rownum";
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
