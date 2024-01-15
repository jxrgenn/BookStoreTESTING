package main;

import java.awt.Desktop;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.StringTokenizer;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Main {
	private static Desktop desktop = Desktop.getDesktop();
	public static void main(String[] args) throws FileNotFoundException {
	}

	public static ArrayList<Book> getBooks (String fileName)
	{
		ArrayList<Book> books = new ArrayList<Book>();
		try {
		Scanner input =new Scanner (new File(fileName));
		StringTokenizer token = null;
		
		int ISBN;
		String title="";
		String category="";
		String supplier="";
		double purchasedPrice;
		
		while (input.hasNextLine())
		{
			token = new StringTokenizer(input.nextLine(),",");
			ISBN=Integer.parseInt(token.nextToken());
			title=token.nextToken();
			category=token.nextToken();
			supplier=token.nextToken();
			purchasedPrice=Double.parseDouble(token.nextToken());
			Book book = new Book (ISBN,title,category,supplier,purchasedPrice);
			book.setQuantity(1);
			books.add(book);
			
		}
		}
		catch (FileNotFoundException e)
		{
		}
		return books;
	}
	
	public static void checkQuantity (ArrayList<Book> books)
	{
		for (int i=0;i<books.size();i++)
		{
			for (int j=i+1;j<books.size();j++)
			{
			if (books.get(i).getISBN()==books.get(j).getISBN())
			{
				books.get(i).setQuantity(books.get(i).getQuantity()+1);
				books.remove(j);
				j--;
			}
			}
		}
	}

	public static void openFile (File file) {
		try {
			desktop.open(file);
		} catch (IOException ex) {
			Logger.getLogger(
					Main.class.getName()).log(
					Level.SEVERE, null, ex
			);
		}
	}
}

