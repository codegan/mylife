package ru.codegan.mylife.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
@Table(name="main.books_used")
@SequenceGenerator(name="auto_id_books_used", sequenceName="main.auto_id_books_used", allocationSize = 1, initialValue = 1)
public class BooksUsed {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "auto_id_books_used")
	private Integer id;

	private Integer page_number = 0;
	private Date date_start;
	private Date date_end;
	
	@JsonProperty
	@OneToOne
	@JoinColumn(name="books_id")
	private Books books;
	
	@JsonProperty
	@ManyToOne
    @JoinColumn(name="books_used_status_id")
	private BooksUsedStatus usedStatus;
	
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
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
		return usedStatus;
	}
	public void setUsedStatus(BooksUsedStatus usedStatus) {
		this.usedStatus = usedStatus;
	}
	
	
	
	
}
