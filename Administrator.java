
public class Administrator extends User {
	protected String name;
	protected String surame;
	protected int PhoneNumber;
	protected String email;
	protected String password;
	protected int accessLevel = 3;
	
	public Administrator (String name, String surname, String email, String password)
	{
		super(name,surname,email,password);
	}
	
	
}
