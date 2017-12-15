package ru.codegan.mylife.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import ru.codegan.mylife.model.Books;
import ru.codegan.mylife.model.BooksUsed;
import ru.codegan.mylife.model.BooksUsedStatus;
import ru.codegan.mylife.services.BooksService;

@RestController
@RequestMapping("/books")
//@Secured(value = {"ROLE_ADMIN"})
@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600)
public class BooksController {
	
	private BooksService booksService;
	
	@Autowired
	public void setBooksService(BooksService booksService){
		this.booksService = booksService;
	}
	//Books
	@RequestMapping(method = RequestMethod.GET, value="/")
	public @ResponseBody List<Books> findBooks() {
		return this.booksService.findAllBooks();	
	}
	
	//Books
	@RequestMapping(method = RequestMethod.GET, value="/{id}")
	public @ResponseBody Books findBooksById(@PathVariable("id") int id) {
		return this.booksService.findAllBooksById(id);	
	}
	
	@RequestMapping(method = RequestMethod.POST, value="/")
	public List<Books> addBooks(@RequestBody Books books) {
		this.booksService.saveBooks(books);
		return this.findBooks();
	}
	
	@RequestMapping(method = RequestMethod.PUT, value="/")
	public List<Books> updateBooks(@RequestBody Books books) {
		this.booksService.saveBooks(books);
		return this.findBooks();
	}
	
	@RequestMapping(method = RequestMethod.DELETE, value="/{id}")
	public List<Books> removeBooks(@PathVariable int id) {
		Books books = new Books();
		books.setId(id);
		this.booksService.removeBooks(books);
		return this.findBooks();
	}
	//Books_Used
	@RequestMapping(method = RequestMethod.GET, value="/used")
	public @ResponseBody List<BooksUsed> findBooksUsed() {
		return this.booksService.findAllBooksUsed();
	}
	
	@RequestMapping(method = RequestMethod.POST, value="/used")
	public List<BooksUsed> addBooksUsed(@RequestBody BooksUsed booksUsed) {
		this.booksService.saveBooksUsed(booksUsed);
		return this.booksService.findAllBooksUsed();
	}
	
	@RequestMapping(method = RequestMethod.PUT, value="/used")
	public List<BooksUsed> updateBooksUsed(@RequestBody BooksUsed booksUsed) {
		this.booksService.saveBooksUsed(booksUsed);
		return this.booksService.findAllBooksUsed();
	}
	@RequestMapping(method = RequestMethod.DELETE, value="/used")
	public List<BooksUsed> removeBooksUsed(@RequestBody BooksUsed booksUsed) {
		this.booksService.removeBooksUsed(booksUsed);
		return this.booksService.findAllBooksUsed();
	}
	//Used
	@RequestMapping(method = RequestMethod.GET, value="/used/status/{id}")
	public @ResponseBody List<BooksUsed> getUsedStatusBooks(@PathVariable("id") int used_status_id) {
		return this.booksService.findAllStatusBooks(used_status_id);
	}
	
	
}