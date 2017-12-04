package ru.codegan.mylife.controllers;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import ru.codegan.mylife.dao.BooksDao;
import ru.codegan.mylife.dao.BooksDaoImpl;
import ru.codegan.mylife.model.Books;
import ru.codegan.mylife.model.BooksUsed;
import ru.codegan.mylife.model.BooksUsedStatus;
import ru.codegan.mylife.services.BooksService;
import org.apache.commons.dbcp.BasicDataSource;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
@RestController
@RequestMapping("/api")
public class BooksRestController {
	
	private BooksService booksService;
	
	@Autowired
	public void setBooksService(BooksService booksService){
		this.booksService = booksService;
	}
	//Books
	@RequestMapping(method = RequestMethod.GET, value="/books")
	public @ResponseBody List<Books> findBooks() {
		return this.booksService.findAllBooks();	
	}
	
	@RequestMapping(method = RequestMethod.POST, value="/books")
	public List<Books> addBooks(@RequestBody Books books) {
		this.booksService.saveBooks(books);
		return this.findBooks();
	}
	
	@RequestMapping(method = RequestMethod.PUT, value="/books/{id}/{name}/{year}/{author}")
	public List<Books> updateBooks(@PathVariable int id, @PathVariable String name, @PathVariable int year, @PathVariable String author) {
		Books books = new Books();
		books.setId(id);
		books.setName(name);
		books.setYear(year);
		books.setAuthor(author);
		this.booksService.saveBooks(books);
		return this.findBooks();
	}
	
	@RequestMapping(method = RequestMethod.DELETE, value="/books/{id}")
	public List<Books> removeBooks(@PathVariable int id) {
		Books books = new Books();
		books.setId(id);
		this.booksService.removeBooks(books);
		return this.findBooks();
	}
	//Books_Used
	@RequestMapping(method = RequestMethod.GET, value="/books_used")
	public @ResponseBody List<BooksUsed> findBooksUsed() {
		return this.booksService.findAllBooksUsed();
	}
	
	@RequestMapping(method = RequestMethod.POST, value="/books_used/{date_start}/{page_number}/{books_id}/{books_used_status_id}")
	public List<BooksUsed> addBooksUsed(@PathVariable String date_start ,@PathVariable int page_number, @PathVariable int books_id, @PathVariable int books_used_status_id) {
		Books books = new Books();
		books.setId(books_id);
	
		BooksUsedStatus booksUsedStatus = new BooksUsedStatus();
		booksUsedStatus.setId(books_used_status_id);
		DateTime dateTime = (DateTime) DateTimeFormat.forPattern("yyyy-MM-dd").parseDateTime(date_start);
		
		Date date = dateTime.toDate();
		BooksUsed booksUsed = new BooksUsed();
		booksUsed.setDate_start(date);
		booksUsed.setPage_number(page_number);
		booksUsed.setBooks(books);
		booksUsed.setUsedStatus(booksUsedStatus);
		this.booksService.saveBooksUsed(booksUsed);
		return this.booksService.findAllBooksUsed();
	}
	
	@RequestMapping(method = RequestMethod.PUT, value="/books_used/{id}/{page_number}/{books_id}/{books_used_status_id}")
	public List<BooksUsed> updateBooksUsed(@PathVariable int id, @PathVariable int page_number, @PathVariable int books_id, @PathVariable int books_used_status_id) {
		Books books = new Books();
		books.setId(books_id);
		
		BooksUsedStatus booksUsedStatus = new BooksUsedStatus();
		booksUsedStatus.setId(books_used_status_id);
		
		BooksUsed booksUsed = new BooksUsed();
		booksUsed.setId(id);
		booksUsed.setPage_number(page_number);
		booksUsed.setBooks(books);
		booksUsed.setUsedStatus(booksUsedStatus);
		this.booksService.saveBooksUsed(booksUsed);
		return this.booksService.findAllBooksUsed();
	}
	
	@RequestMapping(method = RequestMethod.GET, value="/book_used_status/{id}")
	public @ResponseBody List<BooksUsed> getUsedStatusBooks(@PathVariable("id") int used_status_id) throws SQLException {
		return this.booksService.findAllStatusBooks(used_status_id);
	}
	
	@RequestMapping(method = RequestMethod.DELETE, value="/books_used/{id}/{page_number}/{books_id}/{books_used_status_id}")
	public List<BooksUsed> removeBooksUsed(@PathVariable int id, @PathVariable int page_number, @PathVariable int books_id, @PathVariable int books_used_status_id) {
		Books books = new Books();
		books.setId(books_id);
		
		BooksUsedStatus booksUsedStatus = new BooksUsedStatus();
		booksUsedStatus.setId(books_used_status_id);
		
		BooksUsed booksUsed = new BooksUsed();
		booksUsed.setId(id);
		booksUsed.setPage_number(page_number);
		booksUsed.setBooks(books);
		booksUsed.setUsedStatus(booksUsedStatus);
		this.booksService.removeBooksUsed(booksUsed);
		return this.booksService.findAllBooksUsed();
	}
}