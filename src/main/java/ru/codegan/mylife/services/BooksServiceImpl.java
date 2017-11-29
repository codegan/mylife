package ru.codegan.mylife.services;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ru.codegan.mylife.dao.BooksDao;
import ru.codegan.mylife.model.Books;
import ru.codegan.mylife.model.BooksUsed;

import javax.annotation.*;
@Service
public class BooksServiceImpl implements BooksService{

	BooksDao booksDao;
	
	@Resource(name="booksDaoImpl")
	public void setBooksDao(BooksDao booksDao) {
		this.booksDao = booksDao;
	}
	//Get:books
	public List<Books> findAllBooks() {
		return this.booksDao.findAllBooks();
	}
	//Post:books
	public void saveBooks(Books books) {
		this.booksDao.saveBooks(books);
	}

	public List<BooksUsed> findAllBooksUsed() {
		return this.booksDao.findAllBooksUsed();
	}

	public List<BooksUsed> findAllStatusBooks(int status_id) {
		return this.booksDao.findAllStatusBooks(status_id);
	}

	public void editBook(Books books) throws SQLException {
		this.booksDao.editBook(books);
	}

	public List<Books> getListBooks() {
		return this.booksDao.getListBooks();
	}

	public Books getBookById(int id) throws SQLException {
		return this.booksDao.getBookById(id);
	}

	public void deleteBook(int id) throws SQLException {
		this.booksDao.deleteBook(id);
	}

	public List<BooksUsed> findAll() {
		// TODO Auto-generated method stub
		return this.booksDao.findAllBooksUsed();
	}
}
