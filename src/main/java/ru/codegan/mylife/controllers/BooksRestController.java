package ru.codegan.mylife.controllers;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import ru.codegan.mylife.dao.BooksDao;
import ru.codegan.mylife.dao.BooksDaoImpl;
import ru.codegan.mylife.model.Books;
import ru.codegan.mylife.services.BooksService;

@RestController
@RequestMapping("/api")
public class BooksRestController {
	
	private BooksService booksService;
	
	@Autowired
	public void setBooksService(BooksService booksService){
		this.booksService = booksService;
	}
	
	@RequestMapping(method = RequestMethod.GET, value="/book/all")
	public @ResponseBody List<Books> getBooks() throws SQLException {
		return this.booksService.getListBooks();
	}
	
	@RequestMapping(method = RequestMethod.GET, value="/book/{id}")
	public @ResponseBody Books getBookById(@PathVariable("id") int id) throws SQLException {
		return this.booksService.getBookById(id);
	}
	
	@RequestMapping(method = RequestMethod.POST, value="/book/add/{name}/{year}/{author}")
	public List<Books> addBook(@PathVariable String name, @PathVariable int year, @PathVariable String author) throws SQLException {
		Books books = new Books();
		books.setName(name);
		books.setYear(year);
		books.setAuthor(author);
		this.booksService.addBook(books);
		return this.getBooks();
	}
	
	@RequestMapping(method = RequestMethod.POST, value="/book/delete/{id}")
	public List<Books> deleteBook(@PathVariable int id) throws SQLException{
		this.booksService.deleteBook(id);
		return booksService.getListBooks();
	}
	
	@RequestMapping(method = RequestMethod.POST, value="/book/edit/{id}/{name}/{year}/{author}")
	public List<Books> updateBook(@PathVariable int id, @PathVariable String name, @PathVariable int year, @PathVariable String author) throws SQLException{
		Books books = new Books();
		books.setId(id);
		books.setName(name);
		books.setYear(year);
		books.setAuthor(author);
		this.booksService.editBook(books);
		return this.booksService.getListBooks();
	}
	
	@RequestMapping(value="/book/**")
	public String errorCode() {
		return "Такоко адреса не сушествует";
	}
}