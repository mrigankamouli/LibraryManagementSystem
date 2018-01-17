package com.echo.lms.book;

public class Book {
	private String title;
	private String author;
	private String isbn;
	private double price;
	private BookCategory category;
	private boolean borrowed;
	
	
	public Book(String title, String author)
	{
		this(title, author, null, 0.0, null);
	}
	
	public Book(String title, String author, String isbn)
	{
		this(title, author, isbn, 0.0, null);
	}
	public Book(String title, String author, String isbn, double price)
	{
		this(title, author, isbn, price, null);
	}
	
	public Book(String title, String author, String isbn, double price, BookCategory category)
	{
		this.title = title;
		this.author = author;
		this.isbn = isbn;
		this.price = price;
		this.category = category;
		this.borrowed = false;
	}




	public String getIsbn()
	{
		return isbn;
	}
	public void setIsbn(String isbn)
	{
		this.isbn = isbn;
	}
	public double getPrice()
	{
		return price;
	}
	public void setPrice(double price)
	{
		this.price = price;
	}
	public BookCategory getCategory()
	{
		return category;
	}
	public void setCategory(BookCategory category)
	{
		this.category = category;
	}
	public boolean isBorrowed()
	{
		return borrowed;
	}
	public void setBorrowed(boolean borrowed)
	{
		this.borrowed = borrowed;
	}
	public String getTitle()
	{
		return title;
	}
	public String getAuthor()
	{
		return author;
	}
	
	@Override
	public boolean equals(Object obj)
	{
		
		if(obj == null || !(obj instanceof Book))
		{
			return false;
		}
		Book another = (Book)obj;
		return this.title.equals(another.title) && this.author.equals(another.author);
	}
	
	@Override
	public int hashCode()
	{
		return this.title.hashCode()+this.author.hashCode();
	}
	
}
