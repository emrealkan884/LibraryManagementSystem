package business.abstracts;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import entities.concretes.Author;

public interface AuthorService {
	
	ArrayList<Author> getAllAuthors() throws Exception;
	
	Author getAuthorById(int id) throws Exception;

    boolean addAuthor(Author author,Connection con) throws SQLException, Exception;

    void updateAuthor(Author author,Connection con) throws Exception;

    void deleteAuthor(Author author) throws Exception;
    
    int currentId(Connection con);
}
