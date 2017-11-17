package ru.codegan.mylife.services;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ru.codegan.mylife.dao.BooksDao;
import ru.codegan.mylife.model.Books;

@Service
public class BooksServiceImpl implements BooksService{

	BooksDao booksDao;
	
	@Autowired
	public void setBooksDao(BooksDao booksDao) {
		this.booksDao = booksDao;
	}
	
	public void addBook(Books books) throws SQLException {
		this.booksDao.addBook(books);
	}

	public void editBook(Books books) throws SQLException {
		this.booksDao.editBook(books);
	}

	public List<Books> getListBooks() throws SQLException {
		return this.booksDao.getListBooks();
	}

	public Books getBookById(int id) throws SQLException {
		return this.booksDao.getBookById(id);
	}

	public void deleteBook(int id) throws SQLException {
		this.booksDao.deleteBook(id);
	}
}
