package main;

import javafx.collections.ObservableList;
import javafx.scene.control.ListView;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

public class Librarian extends User{
	protected String name;
	protected String surname;
	protected int PhoneNumber;
	protected String email;
	protected String password;
	
	protected double billNO;
	protected double booksNO;
	protected double profit;
	public Librarian ()
	{
		super();
	}
	
	public Librarian (String name, String surname, String email, String password)
	{
		super(name,surname,email,password);
	}
	public Librarian (String name, String surname, String email, String password, double profit, double booksNO)
	{
		super(name,surname,email,password);
		setProfit(profit);
		setBooksNO(booksNO);
	}
	public double getProfit() {
		return profit;
	}
	public void setProfit(double profit)
	{
		this.profit=profit;
	}
	public void setBooksNO(double booksNO)
	{
		this.booksNO=booksNO;
	}

	public void setName (String name)
	{
		this.name = name;
	}
	public void setSurname (String surname)
	{
		this.surname = surname;
	}
	public void setEmail (String email)
	{
		this.email = email;
	}
	public void setPassword (String password)
	{
		this.password = password;
	}

	public void addBooks(ListView<Book> booksInStore, ListView<Book> auga) {

		ArrayList<Book> books2 = new ArrayList<Book>();
		books2.addAll(auga.getItems());
		ObservableList<Book> selectedBooks = booksInStore.getSelectionModel().getSelectedItems();
		ArrayList<Book> books = new ArrayList<Book>(selectedBooks);
		for (Book book : books) {
			System.out.println("k");
			try (FileWriter f = new FileWriter(
					"files/soldBooks.txt", true);
				 BufferedWriter b = new BufferedWriter(f);
				 PrintWriter p = new PrintWriter(b)) {
				p.println(book.getISBN() + ", " + book.getTitle() + ", " + book.getCategory()
						+ ", " + book.getSupplier() + ", " + book.getPurchasedPrice() + ", ");
			} catch (IOException e2) {
				e2.printStackTrace();
				System.out.println("jo jo");
			}
		}

		books2.addAll(books);

		for (int i = 0; i < books2.size(); i++) {
			for (int j = i + 1; j < books2.size(); j++) {
				if (books2.get(i).getISBN() == books2.get(j).getISBN()) {
					books2.remove(j);
					j--;
				}
			}
		}
		for (Book book : books2) {
			if (book.getQuantity() == 1) {
				book.setSoldQuantity(book.getSoldQuantity() + 1);
				book.setQuantity(book.getQuantity() - 1);
				booksInStore.getItems().remove(booksInStore.getSelectionModel().getSelectedIndex());
			} else if (book.getQuantity() > 1) {
				book.setSoldQuantity(book.getSoldQuantity() + 1);
				book.setQuantity(book.getQuantity() - 1);
			}

		}
		auga.getItems().clear();
		auga.getItems().addAll(books2);
	}

	public static void reWriteBooks(ArrayList<Book> books, String fileName) {

		try (FileWriter f = new FileWriter(fileName);
			 BufferedWriter b = new BufferedWriter(f);
			 PrintWriter p = new PrintWriter(b)) {
			for (Book book : books) {
				for (int j = 0; j < book.getQuantity(); j++) {
					p.println(
							book.getISBN() + ", " + book.getTitle() + ", " + book.getCategory()
									+ ", " + book.getSupplier() + ", " + book.getPurchasedPrice());
				}
			}
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}

	public void buttonClicked(ListView<Book> booksInStore) throws NoBooksException, IOException {

		ObservableList<Book> temp;
		temp = booksInStore.getSelectionModel().getSelectedItems();
		ArrayList<Book> books = new ArrayList<>(booksInStore.getItems());

		ArrayList<Book> soldBooks = new ArrayList<>(temp);
		reWriteBooks(books, "files/bookList.txt");
		// get indices of all the selected books
		ArrayList<Integer> indices = new ArrayList<>(booksInStore.getSelectionModel().getSelectedIndices());
		System.out.println(indices);

		// System.out.println(books);
		CheckOutBook(soldBooks);

		System.out.println(checkPerformance());
		fixBill(soldBooks);
		CreateBill(soldBooks);
		booksInStore.getItems().clear();
		booksInStore.getItems().addAll(books);

		// fix soldBooks arrayList so we can avoid table redundancy

	}

	private void fixBill(ArrayList<Book> books) {

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
	}

	public void addProfit(double profit)
	{
		this.profit= this.profit + profit;
	}
	public String getName() {
		return name;
	}
	public String getSurname() {
		return surname;
	}

	public String getEmail() {
		return email;
	}

	@Override
	public String getPassword(){
		return password;
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
		try(FileWriter f = new FileWriter("files/Bill.txt",true);
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

	public String checkPerformance ()
	{
		return "Number of books sold: " +getbooksNO() +
				", Profit: " + getProfit();
	}
}