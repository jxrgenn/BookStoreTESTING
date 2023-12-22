import java.util.ArrayList;

public class managerController {

private ArrayList<User> managers;
	
	public managerController () {
		managers=new ArrayList<>();
	}
	
	public void addManager(User user)
	{
		managers.add(user);
	}
	public boolean signUp (String firstName, String lastName, String email
			,String password, String verifyPassword) {
		if (password.equals(verifyPassword))
		{
			User newUser = new User (firstName, lastName, email, password);
			managers.add(newUser);
			return true;
		}
		return false;
	}
	
	public void printManagers()
	{
		for (int i=0; i<managers.size();i++)
		{
			System.out.println(managers.get(i));
		}
	}
	public User getManager(String email,String password)
	{
		int counter=0;
		for (int i=0;i<managers.size();i++)
		{
			if (email==managers.get(i).getEmail() && password==managers.get(i).getPassword())
			{
				counter=i;
			}
		}
		return managers.get(counter);
	}
	public ArrayList<User> getManagers ()
	{
		return managers;
	}
}
