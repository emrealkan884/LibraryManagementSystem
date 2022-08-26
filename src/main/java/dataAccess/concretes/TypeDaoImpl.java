package dataAccess.concretes;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import dataAccess.abtracts.TypeDao;
import entities.concretes.Type;
import utilities.DbHelper;

public class TypeDaoImpl implements TypeDao {
	
	@Override
	public ArrayList<Type> getAll() throws Exception {
		Connection connection = null;
		Statement statement = null;
		ResultSet rSet = null;
		String sorgu = "Select Name from Types";
		ArrayList<Type> types = new ArrayList<Type>();
		try {
			connection = DbHelper.getConnection();
			statement = connection.createStatement();
			rSet = statement.executeQuery(sorgu);
			Type type = null;
			while (rSet.next()) {
				type = new Type();
				type.setName(rSet.getString("Name"));
				types.add(type);
			}
		} catch (Exception e) {
			System.out.println(e);
		}finally {
			if (connection != null) {
				connection.close();
			}
		}
		return types;
	}
	
	@Override
	public Type getEntityById(int id) throws Exception {
		Connection connection = null;
		String query = "Select * from Types where Id = ?";
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		Type type = null;
		try {
			connection = DbHelper.getConnection();
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setInt(1, id);
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				type = new Type();
				type.setName(resultSet.getString("Name"));
				type.setId(id);
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
		return type;
	}
	@Override
	public boolean updateEntity(Type entity,Connection connection) throws Exception {
		String query = "Update Types Set Name=?";
		PreparedStatement preparedStatement = null;
		try {
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, entity.getName());
			int guncellenen = preparedStatement.executeUpdate();
			if (guncellenen>0) {
				System.out.println(guncellenen + " Tür güncellendi.");
				return true;
			}
			else {
				System.out.println("Tür güncellenirken sorun oluştu.");
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
	public boolean deleteEntity(Type entity) throws Exception {
		String query = "Delete from Types where Id = ?";
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		try {
			connection = DbHelper.getConnection();
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setInt(1, entity.getId());
			int silinen = preparedStatement.executeUpdate();
			if (silinen>0) {
				System.out.println(silinen + " Tür silindi.");
				return true;
			}
			else {
				System.out.println("Tür silinirken hata oluştu");
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
	public boolean addEntity(Type entity,Connection con) throws SQLException {
		String query = "Insert into Types (Name) values (?)";
		PreparedStatement preparedStatement = null;
		try {
			preparedStatement = con.prepareStatement(query);
			preparedStatement.setString(1,entity.getName());
			int eklenen = preparedStatement.executeUpdate();
			if (eklenen>0) {
				System.out.println(eklenen +" Tür eklendi.");
				return true;
			}
			else {
				System.out.println("Tür eklenirken sorun oluştu");
				return false;
			}
		} catch (Exception e) {
			System.out.println(e);
			return false;
		}
	}
	@Override
	public int currentId(Connection con) {
		String sorgu = "Select Id From (select Id from Types Order By Id desc) where rownum <=1 ORDER BY rownum";
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
