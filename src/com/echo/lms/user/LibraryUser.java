package com.echo.lms.user;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.echo.lms.book.Book;
import com.echo.lms.util.LibraryUtil;

public class LibraryUser {
	private int id;
	private String name;
	private String address;
	private Date registrationDate;
	private List<Book> borrowedBooks;
	
	public LibraryUser(String name, String address)
	{
		this.name = name;
		this.address = address;
		this.id = LibraryUtil.getNextUserId();
		this.registrationDate = new Date();
		this.borrowedBooks = new ArrayList<>();
	}

	public String getAddress()
	{
		return address;
	}

	public void setAddress(String address)
	{
		this.address = address;
	}

	public int getId()
	{
		return id;
	}

	public String getName()
	{
		return name;
	}

	public Date getRegistrationDate()
	{
		return registrationDate;
	}

	public List<Book> getBorrowedBooks()
	{
		return borrowedBooks;
	}
	
	
}
