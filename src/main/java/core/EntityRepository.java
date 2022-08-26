package core;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

public interface EntityRepository<T> {
	  public ArrayList<T> getAll() throws Exception;
      public T getEntityById(int id) throws Exception;
      public boolean updateEntity(T entity,Connection connection) throws Exception;
      public boolean deleteEntity(T entity) throws Exception;
      public boolean addEntity(T entity,Connection connection) throws SQLException;
      public int currentId(Connection con);
}
