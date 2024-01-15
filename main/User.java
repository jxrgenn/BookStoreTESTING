package main;

public class User {
private String name,surname,email,password;
	public User ()
	{
		this.name = " ";
		this.surname = "";
		this.email = " ";
		this.password = "";
	}
	public User (String name, String surname, String email, String password) {
		setName(name);
		setSurname(surname);
		setEmail(email);
		setPassword(password);
	}
	public void setName(String name) {
		this.name=name;
	}
	public void setSurname(String surname) {
		this.surname=surname;
	}
	public void setEmail(String email) {
		this.email=email;
	}
	public void setPassword(String password) {
		this.password=password;
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
	public String getPassword() {
		return password;
	}

	public String toString() {
		return ("Name: " + this.name + "| Surname: " + this.surname
				+ "| Email: " + this.email);
	}

}