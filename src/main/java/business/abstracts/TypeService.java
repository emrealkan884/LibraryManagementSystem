package business.abstracts;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import entities.concretes.Type;

public interface TypeService {
	ArrayList<Type> getAllTypes() throws Exception;
	
	Type getTypeById(int id) throws Exception;

    boolean addType(Type type,Connection con) throws SQLException, Exception;

    void updateType(Type type,Connection con) throws Exception;

    void deleteType(Type type) throws Exception;
    
    int currentId(Connection con);

	
}
