package business.concretes;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import business.abstracts.UserService;
import dataAccess.abtracts.UserDao;
import entities.concretes.User;

public class UserManager implements UserService{
	
	private UserDao userDao;
	
	public UserManager(UserDao userDao) {
		userDao = this.userDao;
	}
	
	@Override
	public ArrayList<User> getAllUsers() throws Exception {
		return userDao.getAll();
	}

	@Override
	public boolean addUser(User user,Connection con) throws SQLException {
		return userDao.addEntity(user,con);
	}

	@Override
	public void updateUser(User user,Connection connection) throws Exception {
		userDao.updateEntity(user,connection);
	}

	@Override
	public void deleteUser(User user) throws Exception {
		userDao.deleteEntity(user);
	}

	@Override
	public User getUserById(int id) throws Exception {
		return userDao.getEntityById(id);
	}

	@Override
	public int currentId(Connection con) {
		return userDao.currentId(con);
	}
	
}
