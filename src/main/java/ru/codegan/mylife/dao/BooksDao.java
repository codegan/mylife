package ru.codegan.mylife.dao;

import java.sql.SQLException;
import java.util.List;

import ru.codegan.mylife.model.Books;

public interface BooksDao {
	public List<Books> findAll();
	public void addBook(Books books) throws SQLException;
	public void editBook(Books books) throws SQLException;
	public List<Books> getListBooks();
	public Books getBookById(int id) throws SQLException;
	public void deleteBook(int id) throws SQLException;
}