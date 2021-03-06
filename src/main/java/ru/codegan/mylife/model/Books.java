package ru.codegan.mylife.model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name="main.books")
@NamedQuery(name="Books.findAll", query="SELECT b FROM Books b") 
@SequenceGenerator(name="auto_id_books", sequenceName="main.auto_id_books", allocationSize = 1, initialValue = 1)
public class Books implements Serializable{
	
	@Id
	@GeneratedValue( strategy = GenerationType.SEQUENCE, generator = "auto_id_books")
	@Column(name="id")
	private Integer id;

	@Column(name="name")
	private String name;

	@Column(name="author")
	private String author;
	
	@Column(name="year")
	private int year;
	
	
	public Books() {
	}

	public Books(int id, String name, int year, String author) {
		this.id = id;
		this.name = name;
		this.year = year;
		this.author = author;
	}
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getYear() {
		return year;
	}
	public void setYear(int year) {
		this.year = year;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}

	@Override
	public String toString() {
		return "Books [id=" + id + ", name=" + name + ", year=" + year + ", author=" + author + "]";
	}
	
	
	
}
