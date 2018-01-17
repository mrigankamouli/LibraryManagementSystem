package com.echo.lms.manage;

import java.util.Scanner;

import com.echo.lms.book.Book;
import com.echo.lms.book.BookStore;
import com.echo.lms.user.LibraryUser;
import com.echo.lms.user.UserManager;
import com.echo.lms.util.LibraryUtil;

public final class LibraryManager {
	
	private static final String EXITING_CHOICE = "0";

	/**
	 * As of now, Only considering book name and author name to add book in library
	 */
	public void addBookToLibrary(Scanner sc)
	{
		try
		{
			showAddBookOptions();
			String nextLine;
			while((nextLine = sc.nextLine()) != null) {
				String[] split = nextLine.split(" ");
				if(split.length == 1 && split[0].equals(EXITING_CHOICE))
				{
					clearSC(sc);
					return;
				}else if(split.length < 2)
				{
					System.out.println("Invalid input. Please try agin. Press 0 to exit");
					continue;
				}
				String name = split[0];
				String author = split[1];
				
				if(!name.startsWith("\"") || !name.endsWith("\"") || !author.startsWith("\"") || !author.endsWith("\"") || name.length() <=2 || author.length() <= 2)
				{
					System.out.println("Invalid input. Please try agin. Press 0 to exit");
					continue;
				}
				name = name.substring(1, name.length()-1).trim();
				author = author.substring(1, author.length()-1).trim();
				if(name.length() == 0 || author.length() == 0)
				{
					System.out.println("Invalid input. Please try agin. Press 0 to exit");
					continue;
				}
				
				BookStore.getBookStore().addBookToStore(name, author);
				clearSC(sc);
				showAddBookOptions();
				
			}
		}catch(Exception e)
		{
			
		}
	}
	
	public void addUser(Scanner sc)
	{
		try
		{
			
			while(true)
			{
				System.out.println("Please provide user name. Press 0 to exit");
				String nextLine = sc.nextLine();
				if(nextLine == null || nextLine.length() == 0)
				{
					System.out.println("Invalid input. Please try again");
					continue;
				}
				if(nextLine.equals(EXITING_CHOICE))
				{
					System.out.println("Exiting add user module");
					clearSC(sc);
					break;
				}
				String name = nextLine;
				
				System.out.println("Please provide Address. Press 0 to exit");
				nextLine = sc.nextLine();
				
				if(nextLine == null || nextLine.length() == 0)
				{
					System.out.println("Invalid input. Please try again");
					continue;
				}
				if(nextLine.equals(EXITING_CHOICE))
				{
					System.out.println("Exiting add user module");
					break;
				}
				String address = nextLine;
				
				UserManager.getUserManger().addUser(name, address);
				clearSC(sc);
				
			}
		}catch(Exception e)
		{
			
		}
	}
	
	public void lendBookToUser(Scanner sc)
	{
		System.out.println("Press 0 any time to exit from this module");
		System.out.println("Please enter user name");
		String nextLine = sc.nextLine();
		if(nextLine == null || nextLine.length() == 0)
		{
			invalidUserPrompt(sc);
			return;
		}
		if(nextLine.equals(EXITING_CHOICE))
		{
			return;
		}
		LibraryUser user = UserManager.getUserManger().getUser(nextLine);
		if(user == null)
		{
			invalidUserPrompt(sc);
			return;
		}
		if(user.getBorrowedBooks().size() >= LibraryUtil.MAX_BORROWD_BOOK)
		{
			System.out.println("You have borrowed maximum number of books");
			return;
		}
		while(true)
		{
			System.out.println("Please provide book title");
			nextLine = sc.nextLine();
			if(nextLine == null || nextLine.length() == 0)
			{
				System.out.println("Invalid book title. Please try again");
				return;
			}
			Book bookByTitle = BookStore.getBookStore().getBookByTitle(nextLine);
			if(bookByTitle == null)
			{
				System.out.println("Book is not present in library. Please try other books");
				continue;
			}
			if(bookByTitle.isBorrowed())
			{
				System.out.println("Book is already borrowed. Please try other books");
				continue;
			}
			
			user.getBorrowedBooks().add(bookByTitle);
			System.out.println(bookByTitle.getTitle() + "Book is provided to user "+user.getName());
			clearSC(sc);
			return;
		}
	}
	
	private void invalidUserPrompt(Scanner sc)
	{
		System.out.println("Invalid user. Exiting module.");
		clearSC(sc);
	}
	
	private void clearSC(Scanner sc)
	{
		while(sc.hasNextLine())
		{
			sc.nextLine();
		}
	}
	
	private void showAddBookOptions()
	{
		System.out.println("Please provide Book title, author name in double quote format in a single line. See the following sample input.");
		System.out.println("\"Gitanjali\" \"Rabindranath Tagor\"");
		System.out.println("Press 0 to exit");
	}
	
	private void showAddUserOptions()
	{
		System.out.println("Please provide user name");
	}

}
