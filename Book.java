import java.util.Date;

public class Book {
	protected int ISBN;
	protected String title;
	protected String category;
	protected String supplier;
	protected Date purchasedDate=new Date();
	protected double purchasedPrice;
	protected double originalPrice;
	protected double sellingPrice;
	protected int OrderID;
	protected int quantity=1;
	protected int soldQuantity=0;
	

	
	public Book(int ISBN, String title, String category, String supplier,double purchasedPrice)
	{
		this.ISBN=ISBN;
		this.title=title;
		this.category=category;
		this.supplier=supplier;
		this.purchasedPrice=purchasedPrice;
		this.quantity=1;
		this.soldQuantity=0;
	}
	
	public int getISBN() {
		return ISBN;
	}
	public void setISBN(int ISBN)
	{
		this.ISBN=ISBN;
	}
	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title)
	{
		this.title=title;
	}
	
	public String getCategory()
	{
		return this.category;
	}
	public void setCategory(String category) {
		this.category=category;
	}
	
	public double getPurchasedPrice()
	{
		return this.purchasedPrice;
	}
	public void setPurchasedPrice(double price) {
		this.purchasedPrice=price;
	}
	
	public double getOriginalPrice()
	{
		return this.originalPrice;
	}
	public void setOriginalPrice(double price) {
		this.originalPrice=price;
	}
	
	public double getSellingPrice()
	{
		return this.sellingPrice;
	}
	public void setSellingPrice(double price) {
		this.sellingPrice=price;
	}
	public Date getDateCreated()
	{
		return purchasedDate;
	}
	
	public int getDoubleID()
	{
		return this.OrderID;
	}
	public void setDoubleId(int id)
	{
		this.OrderID=id;
	}
	public int getQuantity()
	{
		return quantity;
	}
	public void setQuantity (int quantity)
	{
		this.quantity=quantity;
	}
	
	public int getSoldQuantity()
	{
		return soldQuantity;
	}
	public void setSoldQuantity (int x)
	{
		this.soldQuantity=x;
	}
	public String getSupplier() {
		return this.supplier;
	}
	public void setSupplier(String supplier) {
		this.supplier=supplier;
	}
	
	public String toString ()
	{
		return "title: "+ title + ", Category: " + category + ", ISBN: " + ISBN 
				+ ", Supplier: " + supplier + " Price:" +
				purchasedPrice + "In stock: "+quantity + "Sold number: " + soldQuantity;
	}
	public String BillString()
	{
		double price=soldQuantity*purchasedPrice;
		return "Title: " + title + ", Category: " + category + ", Price: " + soldQuantity + " x " 
	+ purchasedPrice + " = " + price + ", On: " + purchasedDate;
	}
	}
