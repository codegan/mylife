package ru.codegan.mylife.dao;

import java.awt.print.Book;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.fasterxml.jackson.annotation.JsonTypeInfo.Id;

import ru.codegan.mylife.model.Books;
import ru.codegan.mylife.model.BooksUsed;

import javax.annotation.*;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
@Repository
@Transactional
public class BooksDaoImpl implements BooksDao{
	
	private static final Log LOG = LogFactory.getLog(BooksDaoImpl.class);
	
	@PersistenceContext
	private EntityManager em;
	//Books
	public List<Books> findAllBooks(){
		  List<Books> books = em.createQuery("SELECT b FROM Books b").getResultList();
		return books;
	}
	
	public void saveBooks(Books books) {
		if(books.getId() == null) {
			em.persist(books);
		}else {
			em.merge(books);
		}
	}
	
	public void removeBooks(Books books) {
		Books mergedBook = em.merge(books);
		em.remove(mergedBook);
	}
	//Books_Used
	public List<BooksUsed> findAllBooksUsed(){
		  List<BooksUsed> books_used = em.createQuery("SELECT b FROM BooksUsed b").getResultList();
		return books_used;
	}
	
	public List<BooksUsed> findAllStatusBooks(int status_id) {
		TypedQuery<BooksUsed> books_used = (TypedQuery<BooksUsed>) em.createQuery("SELECT b FROM BooksUsed b left join fetch b.usedStatus bs WHERE bs.id =:status_id");
		return books_used.setParameter("status_id", status_id).getResultList();
	}
	
	public void saveBooksUsed(BooksUsed booksUsed) {
		if(booksUsed.getId() == null) {
			em.persist(booksUsed);
		}else {
			em.merge(booksUsed);
		}
	}
	
	public void removeBooksUsed(BooksUsed booksUsed) {
		BooksUsed mergedBooksUsed = em.merge(booksUsed);
		em.remove(mergedBooksUsed);
	}
}
