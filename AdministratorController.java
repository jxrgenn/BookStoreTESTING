import java.util.ArrayList;

public class AdministratorController {

private ArrayList<Administrator> admins;
	
	public AdministratorController () {
		admins=new ArrayList<>();
	}
	
	public void addAdmin(Administrator admin)
	{
		admins.add(admin);
	}
	public boolean signUp (String firstName, String lastName, String email
			,String password, String verifyPassword) {
		if (password.equals(verifyPassword))
		{
			Administrator admin = new Administrator (firstName, lastName, email, password);
			admins.add(admin);
			return true;
		}
		return false;
	}
	
	public void printAdmins()
	{
		for (int i=0; i<admins.size();i++)
		{
			System.out.println(admins.get(i).toString());
		}
	}
	
	public Administrator getAdmin(String email,String password)
	{
		int counter=0;
		for (int i=0;i<admins.size();i++)
		{
			if (email.equals(admins.get(i).getEmail()) && password.equals(admins.get(i).getPassword()))
			{
				counter=i;
			}
		}
		return admins.get(counter);
	}
	public ArrayList<Administrator> getAdmins ()
	{
		return admins;
	}
}
