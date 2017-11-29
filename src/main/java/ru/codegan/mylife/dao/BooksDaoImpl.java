package ru.codegan.mylife.dao;

import java.awt.print.Book;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
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
	private DataSource dataSource;
	private Connection conn;
	private Statement stmt;
	private ResultSet rs;
	
	@PersistenceContext
	private EntityManager em;
	
	@Resource(name="dataSource")
	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}
	
	
	public List<Books> findAllBooks(){
		  List<Books> books = em.createQuery("SELECT b FROM Books b").getResultList();
		return books;
	}
	
	public Books saveBooks(Books books) {
		
		books.setName("test book");
		books.setAuthor("test author");
		books.setYear(1993);
		
		if(books.getId() == null) {
			em.persist(books);
		}else {
			em.merge(books);
		}
		return books;
	}
	
	public List<BooksUsed> findAllBooksUsed(){
		  List<BooksUsed> books_used = em.createQuery("SELECT b FROM BooksUsed b").getResultList();
		return books_used;
	}
	
	public List<BooksUsed> findAllStatusBooks(int status_id) {
		TypedQuery<BooksUsed> books_used = (TypedQuery<BooksUsed>) em.createQuery("SELECT b FROM BooksUsed b left join fetch b.usedStatus bs WHERE bs.id =:status_id");
		return books_used.setParameter("status_id", status_id).getResultList();
	}
	
	public void editBook(Books books) throws SQLException {
		conn = dataSource.getConnection();
		PreparedStatement ps ;
		if(books.getAuthor() == null || books.getYear() == 0) {
			ps = conn.prepareStatement("UPDATE main.books set name = ? where id = ?");
			ps.setString(1, books.getName());
			ps.setInt(2, books.getId());
		}else {
			ps = conn.prepareStatement("UPDATE main.books set name = ?, year = ?, author = ? where id = ?");
			ps.setString(1, books.getName());
			ps.setInt(2, books.getYear());
			ps.setString(3, books.getAuthor());
			ps.setInt(4, books.getId());
		}
		ps.executeUpdate();
		ps.close();
		conn.close();
	}

	public List<Books> getListBooks()  {
		List<Books> list = new ArrayList();
		try {
		conn = dataSource.getConnection();
		stmt = conn.createStatement();
		rs = stmt.executeQuery("select * from main.books");
				while (rs.next())
				{
					Books books = new Books();
					books.setId(rs.getInt("id"));
					books.setName(rs.getString("name"));
					books.setYear(rs.getInt("year"));
					books.setAuthor(rs.getString("author"));
					list.add(books);
				}
				rs.close();
				stmt.close();
		}catch (SQLException exp) {
			exp.printStackTrace();
		}finally {
			try {
				conn.close();
			}catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return list;
	}
	
	public Books getBookById(int id) throws SQLException {
		conn = dataSource.getConnection();
		stmt = conn.createStatement();
		rs = stmt.executeQuery("select * from main.books where id =" + id);
		Books books = new Books();
		if (rs.next()) {
			books.setId(rs.getInt("id"));
			books.setName(rs.getString("name"));
			books.setYear(rs.getInt("year"));
			books.setAuthor(rs.getString("author"));
		}
		rs.close();
		stmt.close();
		return books;
	}

	public void deleteBook(int id) throws SQLException {
		conn = dataSource.getConnection();
		PreparedStatement ps = conn.prepareStatement("delete from main.books where id = ?");
		ps.setInt(1, id);
		ps.execute();
		ps.close();
		conn.close();
	}
}
