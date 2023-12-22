import java.util.ArrayList;

public class Manager extends User{
	protected String name;
	protected String surame;
	protected int PhoneNumber;
	protected String email;
	protected String password;
	protected int accessLevel = 2;
	ArrayList<Book> books = new ArrayList<Book>();
	
	
	public Manager (String name, String surname, String email, String password)
	{
		super(name,surname,email,password);
	}
	
	public ArrayList<Book> addBook (ArrayList<Book> books,int ISBN, String title, 
			String category, String supplier,double purchasedPrice)
	{
		Book book = new Book(ISBN,title,category,supplier,purchasedPrice);
		books.add(book);
		return books;
	}
}
