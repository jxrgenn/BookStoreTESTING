package main;

import java.util.ArrayList;

public class Manager extends User {
	protected int accessLevel = 2;
	ArrayList<Book> books = new ArrayList<Book>();

	public Manager() {
		super();
	}

	public Manager(String name, String surname, String email, String password) {
		super(name, surname, email, password);
	}

	public static String addBook(ArrayList<Book> books, int ISBN, String title,
								 String category, String author, double purchasedPrice) {
		StringBuilder messages = new StringBuilder();
		boolean verified1 = true;
		for (Book existingBook : books) {
			if (existingBook.getISBN() == ISBN) {
				if (!(title.equalsIgnoreCase(existingBook.getTitle()))) {
					messages.append("ISBN already exists for another book \n");
					verified1 = false;
					break;
				}
			}
		}
			String isbnString = String.valueOf(ISBN);
			if (!(isbnString.length() == 8 && isbnString.matches("\\d+"))) {
				verified1 = false;
				messages.append("ISBN must be 8 numbers");
			}

			if (verified1) {
				Book book = new Book(ISBN, title, category, author, purchasedPrice);
				books.add(book);
				messages.append("Book successfully added");
			}
			return messages.toString();
		}

		// Additional methods, if needed
	}
