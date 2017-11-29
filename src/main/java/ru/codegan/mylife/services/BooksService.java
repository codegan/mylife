package ru.codegan.mylife.services;


import java.sql.SQLException;
import java.util.List;

import ru.codegan.mylife.model.Books;
import ru.codegan.mylife.model.BooksUsed;

public interface BooksService {
	public List<Books> findAllBooks();
	public void saveBooks(Books books);
	public List<BooksUsed> findAllBooksUsed();
	public List<BooksUsed> findAllStatusBooks(int status_id);
	public void editBook(Books books) throws SQLException;
	public List<Books> getListBooks();
	public Books getBookById(int id) throws SQLException;
	public void deleteBook(int id) throws SQLException;
	
}