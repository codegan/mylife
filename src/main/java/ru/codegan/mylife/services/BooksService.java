package ru.codegan.mylife.services;


import java.sql.SQLException;
import java.util.List;

import ru.codegan.mylife.model.Books;
import ru.codegan.mylife.model.BooksUsed;

public interface BooksService {
	//Books
	public List<Books> findAllBooks();
	public void saveBooks(Books books);
	public void removeBooks(Books books);
	//BooksUsed
	public List<BooksUsed> findAllBooksUsed();
	public List<BooksUsed> findAllStatusBooks(int status_id);
	public void saveBooksUsed(BooksUsed books);
	public void removeBooksUsed(BooksUsed booksUsed);
}