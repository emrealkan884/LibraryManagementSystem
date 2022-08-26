package dataAccess.abtracts;

import java.util.ArrayList;
import core.EntityRepository;
import entities.concretes.BookInfo;

public interface BookDao extends EntityRepository<BookInfo> {
	ArrayList<BookInfo> getBookListByBookName(String name) throws Exception;
}
