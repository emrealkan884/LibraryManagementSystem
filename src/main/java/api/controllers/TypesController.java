package api.controllers;


import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import business.abstracts.BookService;
import business.abstracts.TypeService;
import business.concretes.BookManager;
import business.concretes.TypeManager;
import dataAccess.concretes.BookDaoImpl;
import dataAccess.concretes.TypeDaoImpl;

@Path("/LibraryService")
public class TypesController {

    private TypeService typeService;
    private BookService bookService;

	public TypesController() {
		this.typeService = new TypeManager(new TypeDaoImpl());
		this.bookService = new BookManager(new BookDaoImpl());
}

	@Path("/türleriGetir")
	@GET
	@Produces(MediaType.TEXT_PLAIN)
	public String getTypes() throws Exception  {
		String name = null;
		for (int i = 0; i < typeService.getAllTypes().size(); i++) {
			name = typeService.getAllTypes().get(i).getName();
		}
		return name;
	}
	
	@Path("/kitapIsmiGetir/{id}")
	@GET
	@Produces(MediaType.TEXT_PLAIN)
	public String getBook(@PathParam("id") int id) throws Exception  {
		var b = bookService.getBookInfoById(id);
		return b.getName();
	}
	
//	@Path("/kitapbilgileriGetir")
//	@GET
//	@Produces(MediaType.TEXT_PLAIN)
//	public ArrayList<BookInfo> getBookInfos() throws Exception  {
//		
//	}
	
}
