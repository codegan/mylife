package ru.codegan.mylife.dao;

import java.sql.SQLException;
import java.util.List;

import ru.codegan.mylife.model.Books;
import ru.codegan.mylife.model.BooksUsed;

public interface BooksDao {
	//Books
	public List<Books> findAllBooks();
	public void saveBooks(Books books);
	public void removeBooks(Books books);
	//BooksUsed
	public List<BooksUsed> findAllBooksUsed();
	public List<BooksUsed> findAllStatusBooks(int status_id);
	public void saveBooksUsed(BooksUsed booksUsed);
	public void removeBooksUsed(BooksUsed booksUsed);
}