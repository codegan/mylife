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

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import com.fasterxml.jackson.annotation.JsonTypeInfo.Id;

import ru.codegan.mylife.model.Books;

@Repository
public class BooksDaoImpl implements BooksDao{
	
	private DataSource dataSource;
	private Connection conn;
	private Statement stmt;
	private ResultSet rs;
	
	@Autowired
	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}
	
	public void addBook(Books books) throws SQLException {
		conn = dataSource.getConnection();
		PreparedStatement ps = conn.prepareStatement("INSERT INTO main.books (name, year, author) values (?, ?, ?)");
		ps.setString(1, books.getName());
		ps.setInt(2, books.getYear());
		ps.setString(3, books.getAuthor());
		ps.execute();
		ps.close();
		conn.close();
	}

	public void editBook(Books books) throws SQLException {
		conn = dataSource.getConnection();
		PreparedStatement ps = conn.prepareStatement("UPDATE main.books set name = ?, year = ?, author = ? where id = ?");
		ps.setString(1, books.getName());
		ps.setInt(2, books.getYear());
		ps.setString(3, books.getAuthor());
		ps.setInt(4, books.getId());
		ps.executeUpdate();
		ps.close();
		conn.close();
	}

	public List<Books> getListBooks() throws SQLException {
		conn = dataSource.getConnection();
		stmt = conn.createStatement();
		rs = stmt.executeQuery("select * from main.books");
		List<Books> list = new ArrayList();
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
