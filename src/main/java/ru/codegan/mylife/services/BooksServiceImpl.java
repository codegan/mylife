package ru.codegan.mylife.services;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ru.codegan.mylife.dao.BooksDao;
import ru.codegan.mylife.model.Books;
import ru.codegan.mylife.model.BooksUsed;

import javax.annotation.*;
@Service
@Transactional
public class BooksServiceImpl implements BooksService{

	BooksDao booksDao;
	
	@Resource(name="booksDaoImpl")
	public void setBooksDao(BooksDao booksDao) {
		this.booksDao = booksDao;
	}
	//Books
	@Transactional(readOnly=true)
	public List<Books> findAllBooks() {
		return this.booksDao.findAllBooks();
	}

	public Books findAllBooksById(int id) {
		return this.booksDao.findAllBooksById(id);
	}	
	
	public void saveBooks(Books books) {
		this.booksDao.saveBooks(books);
	}
	
	public void removeBooks(Books books) {
		this.booksDao.removeBooks(books);
	}
	//Books_Used
	@Transactional(readOnly=true)
	public List<BooksUsed> findAllBooksUsed() {
		return this.booksDao.findAllBooksUsed();
	}
	@Transactional(readOnly=true)
	public List<BooksUsed> findAllStatusBooks(int status_id) {
		return this.booksDao.findAllStatusBooks(status_id);
	}
	
	public void saveBooksUsed(BooksUsed booksUsed) {
		this.booksDao.saveBooksUsed(booksUsed);
		
	}
	
	public void removeBooksUsed(BooksUsed booksUsed) {
		this.booksDao.removeBooksUsed(booksUsed);
	}
}
