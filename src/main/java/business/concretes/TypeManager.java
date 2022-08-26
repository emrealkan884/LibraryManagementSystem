package business.concretes;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import business.abstracts.TypeService;
import dataAccess.abtracts.TypeDao;
import entities.concretes.Type;


public class TypeManager implements TypeService {
	
	private TypeDao typeDao;
	
	public TypeManager(TypeDao typeDao) {
		this.typeDao =typeDao;
	}

	@Override
	public ArrayList<Type> getAllTypes() throws Exception {
		return typeDao.getAll();
	}

	@Override
	public Type getTypeById(int id) throws Exception {
		return typeDao.getEntityById(id);
	}


	@Override
	public boolean addType(Type type,Connection con) throws SQLException {	
		return typeDao.addEntity(type,con);
	}


	@Override
	public void updateType(Type type,Connection con) throws Exception {
		typeDao.updateEntity(type,con);
	}


	@Override
	public void deleteType(Type type) throws Exception {
		typeDao.deleteEntity(type);
	}

	@Override
	public int currentId(Connection con) {
		return typeDao.currentId(con);
	}
	
}
