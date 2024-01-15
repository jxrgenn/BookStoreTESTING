package main;

public class Administrator extends User {
	protected int accessLevel = 3;

	public Administrator() {
		super();
	}

	public Administrator(String name, String surname, String email, String password) {
		super(name, surname, email, password);
	}

}
