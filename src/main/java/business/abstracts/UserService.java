package business.abstracts;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import entities.concretes.User;

public interface UserService {
	
	ArrayList<User> getAllUsers() throws Exception;
	
	User getUserById(int id) throws Exception;

    boolean addUser(User user,Connection con) throws SQLException;

    void updateUser(User user,Connection con) throws Exception;

    void deleteUser(User user) throws Exception;
    
    int currentId(Connection con);

   
}
