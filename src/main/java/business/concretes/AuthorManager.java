package business.concretes;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

import business.abstracts.AuthorService;
import dataAccess.abtracts.AuthorDao;
import entities.concretes.Author;

public class AuthorManager implements AuthorService{

	private AuthorDao authorDao;
	
	public AuthorManager(AuthorDao authorDao) {
		this.authorDao = authorDao;
	}
	
	@Override
	public ArrayList<Author> getAllAuthors() throws Exception {
		return authorDao.getAll();
	}
	@Override
	public Author getAuthorById(int id) throws Exception {
		return authorDao.getEntityById(id);
	}
	@Override
	public boolean addAuthor(Author author,Connection con) throws SQLException {
		return authorDao.addEntity(author,con);
	}
	@Override
	public void updateAuthor(Author author,Connection con) throws Exception {
		authorDao.updateEntity(author,con);
	}
	@Override
	public void deleteAuthor(Author author) throws Exception {
		authorDao.deleteEntity(author);
	}

	@Override
	public int currentId(Connection con) {
		return authorDao.currentId(con);
	}
}
