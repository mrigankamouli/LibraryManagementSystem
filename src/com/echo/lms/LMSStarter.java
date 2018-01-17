package com.echo.lms;

import java.util.Scanner;

import com.echo.lms.manage.LibraryManager;

public class LMSStarter {

	public static void main(String[] args)
	{
		LibraryManager lm = new LibraryManager();
		System.out.println("Welcome to Library Management System");
		showOptions();
		try (Scanner sc = new Scanner(System.in))
		{
			System.out.println();
			
			int nextInt = 0;
			
			while((nextInt = sc.nextInt()) != 9)
			{
				
				switch(nextInt)
				{
				case 9:
					System.out.println("Exiting...");
					return;
				case 1:
					lm.addBookToLibrary(sc);
					break;
				case 2:
					lm.addUser(sc);
					break;
				case 3:
					lm.lendBookToUser(sc);
					break;
				default:
					System.out.println("Invalid input. Please try again");
					
				}
				showOptions();
			}
		}catch (Exception e)
		{
			
		}

	}
	
	private static void showOptions()
	{
		System.out.println("Please choose operation");
		System.out.println("1 - Add book to library");
		System.out.println("2 - Add User to library");
		System.out.println("3 - Rent a book from library");
		System.out.println("4 - Return the book to library");
		System.out.println("5 - Search book by title");
		System.out.println("6 - Search book by Author");
		System.out.println("7 - Search user by name");
		System.out.println("Press 9 to exit");
	}

}
