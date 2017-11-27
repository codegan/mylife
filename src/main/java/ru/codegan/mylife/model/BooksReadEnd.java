package ru.codegan.mylife.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="books_read_end")
public class BooksReadEnd {
	@Id
	@GeneratedValue
	private int id;
	@Column(name="date_end")
	private int date_end;
	@Column(name="books_id")
	private int books_id;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getDate_end() {
		return date_end;
	}
	public void setDate_end(int date_end) {
		this.date_end = date_end;
	}
	public int getBooks_id() {
		return books_id;
	}
	public void setBooks_id(int books_id) {
		this.books_id = books_id;
	}
	@Override
	public String toString() {
		return "BooksReadEnd [id=" + id + ", date_end=" + date_end + ", books_id=" + books_id + "]";
	}
	
	
}
