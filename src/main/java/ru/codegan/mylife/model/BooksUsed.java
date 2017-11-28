package ru.codegan.mylife.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name="main.books_used")
@JsonIgnoreProperties(ignoreUnknown = true)
public class BooksUsed {
	
	@Id
	@GeneratedValue
	private int id;

	private Integer page_number = 0;
	private Date date_start;
	private Date date_end;
	
	@JsonIgnore(true)
	@OneToOne(fetch=FetchType.LAZY)
	private Books books;
	
	@JsonIgnore(true)
	@OneToOne(fetch=FetchType.LAZY)
	private BooksUsedStatus books_used_status;
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Integer getPage_number() {
		return page_number;
	}
	public void setPage_number(Integer page_number) {
		this.page_number = page_number;
	}
	public Date getDate_start() {
		return date_start;
	}
	public void setDate_start(Date date_start) {
		this.date_start = date_start;
	}
	public Date getDate_end() {
		return date_end;
	}
	public void setDate_end(Date date_end) {
		this.date_end = date_end;
	}
	public Books getBooks() {
		return books;
	}
	public void setBooks(Books books) {
		this.books =books;
	}
	public BooksUsedStatus getUsedStatus() {
		return books_used_status;
	}
	public void setUsedStatus(BooksUsedStatus books_used_status) {
		this.books_used_status = books_used_status;
	}
	
	
	
	
}
