package com.echo.lms.user;

import java.util.HashMap;
import java.util.Map;

/**
 * 
 * @author mriganka
 *
 */
public enum  UserManager {
	INSTANCE;
	private Map<String, LibraryUser> userStore;
	private UserManager()
	{
		this.userStore = new HashMap<>();
	}
	public static UserManager getUserManger()
	{
		return INSTANCE;
	}
	
	/**
	 * Assuming name will be unique
	 * @param name
	 * @param address
	 * @return
	 */
	public LibraryUser addUser(String name, String address)
	{
		if(name == null || name.trim().length() == 0)
		{
			System.out.println("User should be with valid name");
			return null;
		}
		LibraryUser user = new LibraryUser(name, address);
		userStore.put(name, user);
		System.out.println("User added successfully");
		return user;
	}
	
	public LibraryUser getUser(String name)
	{
		return this.userStore.get(name);
	}
	
	public LibraryUser deleteUser(String name)
	{
		LibraryUser libraryUser = this.userStore.get(name);
		if(libraryUser != null)
		{
			this.userStore.remove(name);
		}else {
			System.out.println("User does not exist");
		}
		return libraryUser;
	}
}
