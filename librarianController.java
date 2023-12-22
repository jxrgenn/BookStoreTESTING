import java.util.ArrayList;

public class librarianController {

private ArrayList<Librarian> librarians;
	
	public librarianController () {
		librarians=new ArrayList<>();
	}
	
	public void addLibrarian(Librarian librarian)
	{
		librarians.add(librarian);
	}
	public boolean signUp (String firstName, String lastName, String email
			,String password, String verifyPassword) {
		if (password.equals(verifyPassword))
		{
			Librarian librarian = new Librarian (firstName, lastName, email, password);
			librarians.add(librarian);
			return true;
		}
		return false;
	}
	
	public void printLibrarians()
	{
		for (int i=0; i<librarians.size();i++)
		{
			System.out.println(librarians.get(i).toString());
		}
	}
	
	public Librarian getLibrarian(String email,String password)
	{
		int counter=0;
		for (int i=0;i<librarians.size();i++)
		{
			if (email.equals(librarians.get(i).getEmail()) && password.equals(librarians.get(i).getPassword()))
			{
				counter=i;
			}
		}
		return librarians.get(counter);
	}
	public ArrayList<Librarian> getLibrarians ()
	{
		return librarians;
	}
}
