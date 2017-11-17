package ru.codegan.mylife.model;

public class Books {
	private int id;
	private String name, author;
	private int year;
	
	public Books() {
	}

	public Books(int id, String name, int year, String author) {
		this.id = id;
		this.name = name;
		this.year = year;
		this.author = author;
	}
	
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
