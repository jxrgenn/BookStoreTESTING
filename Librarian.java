import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

public class Librarian extends User{
	protected String name;
	protected String surame;
	protected int PhoneNumber;
	protected String email;
	protected String password;
	
	protected double billNO;
	protected double booksNO;
	protected double profit;
	public Librarian ()
	{
		
	}
	
	public Librarian (String name, String surname, String email, String password)
	{
		super(name,surname,email,password);
	}
	public double getProfit() {
		return profit;
	}
	public void addProfit(double profit)
	{
		this.profit= this.profit + profit;
	}
	
	public double getbooksNO() {
		return booksNO;
	}
	public void addBooksNO (double booksNO)
	{
		this.booksNO = this.booksNO + booksNO;
	}
	public double getbillNO() {
		return billNO;
	}
	public void addBillsNO (double billsNO)
	{
		this.billNO = this.billNO + billNO;
	}
	
	public String toString2 ()
	{
		return getName() + "," + getSurname() + "," + getEmail() +"," + getPassword() +
				"," + getProfit() +"," + getbooksNO();
	}
	
	public String toString ()
	{
		return getName() + "," + getSurname() + "," + getEmail();
	}
	public void CheckOutBook (ArrayList<Book> books) throws NoBooksException
	{
		for (int i=0;i<books.size();i++)
		{
				double price = books.get(i).getPurchasedPrice();
				addProfit(price);
				addBooksNO(1);
			
		}
	}
	
	public void CreateBill (ArrayList<Book>books)
	{
		try(FileWriter f = new FileWriter("C:\\Users\\Maçoku\\eclipse-workspace\\BOOKSTORE PROJECT, ITERATION 1\\Bill.txt",true);
				BufferedWriter b = new BufferedWriter(f);
				PrintWriter p = new PrintWriter(b);){
			for (int i=0;i<books.size();i++)
			{
				p.println(books.get(i).BillString());
			}
			
	}
	catch (IOException e1) {
		e1.printStackTrace();
	}
}
	
	
	public ArrayList<Book> FixBill (ArrayList<Book> books)
	{
		for (int i=0;i<books.size();i++)
		{
			for (int j=i+1;j<books.size();j++)
			{
				if (books.get(i).getTitle().equals(books.get(j).getTitle()))
				{
					books.get(i).setSoldQuantity(books.get(i).getSoldQuantity()+2);
					books.remove(j);
				}
			}
		}
		return books;
	}
	
	public String checkPerformance ()
	{
		return "Number of books sold: " +getbooksNO() +
				", Profit: " + getProfit();
	}
	
}
