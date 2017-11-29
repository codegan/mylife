package ru.codegan.mylife.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name="main.books_used_status")
@SequenceGenerator(name="auto_id_books_used_status", sequenceName="main.auto_id_books_used_status", allocationSize = 1, initialValue = 1)
public class BooksUsedStatus {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "auto_id_books_used_status")
	private int id;
	
	private String name;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
}
