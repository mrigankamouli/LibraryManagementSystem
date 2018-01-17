package com.echo.lms.book;

/**
 * Enum defines the category of books available in library.
 * For now keeping few elements in category. In future this categories will be enhanced.
 * @author mriganka
 *
 */
public enum BookCategory {
	FICTION("Fiction"), NON_FICTION("Non-Fiction"), SCIENCE("Science"), MANAGEMENT("Management"), CLASSIC("Classic"), HORROR("Horror");
	private String type;
	private BookCategory(String type)
	{
		this.type = type;
	}
	
	@Override
	public String toString()
	{
		return this.type;
	}
}
