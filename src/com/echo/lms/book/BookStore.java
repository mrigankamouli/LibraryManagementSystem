package com.echo.lms.book;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

/**
 * The book store for library. This class will manage all books.
 * It will provide add, remove search for books.
 * 
 * Making it singleton as there will only one book store for a library.
 * For singleton, using Enum is best. But that implementation keeping for future.
 * 
 * @author mriganka
 *
 */
public final class BookStore implements Externalizable{
	private static BookStore instance;
	private ConcurrentHashMap<String, Book> titleMap;
	/*
	 * Keeping an extra map to search by author. This will provide faster search.
	 * Else we have to loop for all entries of single map to find authors/title.
	 */
	private ConcurrentHashMap<String, List<Book>> authorMap;
	
	private BookStore()
	{
		this.titleMap = new ConcurrentHashMap<>();
		this.authorMap = new ConcurrentHashMap<>();
	}
	
	public static BookStore getBookStore()
	{
		if(instance == null)
		{
			synchronized(BookStore.class)
			{
				if(instance == null)
				{
					instance = new BookStore();
				}
			}
		}
		return instance;
	}
	
	public void addBookToStore(String title, String author)
	{
		addBookToStore(title, author, null, 0.0, null);
	}
	public void addBookToStore(String title, String author, String isbn)
	{
		addBookToStore(title, author, isbn, 0.0, null);
	}
	public void addBookToStore(String title, String author, String isbn, double price)
	{
		addBookToStore(title, author, isbn, price, null);
	}
	public void addBookToStore(String title, String author, String isbn, double price, BookCategory category )
	{
		if(title == null || title.trim().length() == 0)
		{
			System.out.println("Book title should be present");
			return;
		}
		if(author == null || author.trim().length() == 0)
		{
			System.out.println("Book Author should be present");
			return;
		}
		Book book = this.titleMap.get(title);
		if(book != null)
		{
			System.out.println("Book is already available in library");
			return;
		}
		book = new Book(title, author, isbn, price, category);
		titleMap.put(title, book);
		
		List<Book> booklist = authorMap.get(author);
		if(booklist == null)
		{
			booklist = new ArrayList<>();
		}
		booklist.add(book);
		authorMap.put(author, booklist);
		System.out.println("Book is added to Store");
	}
	
	public Book getBookByTitle(String title)
	{
		if(title == null || title.trim().length() == 0)
		{
			return null;
		}
		return titleMap.get(title);
	}
	
	public List<Book> getBookByAuthor(String author)
	{
		if(author == null || author.trim().length() == 0)
		{
			return null;
		}
		return authorMap.get(author);
	}
	
	public void removeBook(String title)
	{
		if(title == null || title.trim().length() == 0)
		{
			System.out.println("Book title can not be empty");
			return;
		}
		Book book = titleMap.get(title);
		if(book == null)
		{
			System.out.println("Book is not present in library");
			return;
		}
		if(book.isBorrowed())
		{
			System.out.println("Can not delete a book which is borrowed by somebody");
			return;
		}
		String author = book.getAuthor();
		List<Book> booklist = authorMap.get(author);
		booklist.remove(book);
		titleMap.remove(title);
		System.out.println("Book removed from library");
	}
	
	@Override
	public void writeExternal(ObjectOutput out) throws IOException
	{
		throw new IOException("Operation is not supported");
		
	}

	@Override
	public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException
	{
		throw new IOException("Operation is not supported");
		
	}
	
}
