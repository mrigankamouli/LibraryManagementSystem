package com.echo.lms.util;

public final class LibraryUtil {
	public static final int MAX_BORROWD_BOOK = 3;
	private static int _NextUserID = 1;
	public static int getNextUserId()
	{
		return _NextUserID++;
	}
}
