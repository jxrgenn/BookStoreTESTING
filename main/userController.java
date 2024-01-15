package main;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

public class userController<T extends User> {
    private ArrayList<T> users;

    public userController() {
        users = new ArrayList<>();
    }

    public void addUser(T user) {
        users.add(user);
    }
    public T signIn (String email, String password, String role,
                           ArrayList<Manager> managers, ArrayList<Librarian> librarians,
                           ArrayList<Administrator> admins)
    {
        T user = null;
        if (role.equalsIgnoreCase("librarian"))
        {

            for (Librarian value : librarians) {
                if (value.getEmail().equals(email)
                        && (value.getPassword().equals(password)))
                {
                    user = (T) value;
                    break;
                }
            }
        }
        else if (role.equalsIgnoreCase("manager")) {
            for (Manager value : managers) {
                if (value.getEmail().equals(email)
                        && (value.getPassword().equals(password)))
                {
                    user = (T) value;
                    break;
                }
            }

        }
        else if (role.equalsIgnoreCase("administrator")||role.equalsIgnoreCase("admin"))
        {
            for (Administrator value : admins) {
                if (value.getEmail().equals(email)
                        && (value.getPassword().equals(password)))
                {
                    user = (T) value;
                    break;
                }
            }
        }
        return user;
    }

    public T signUp(String firstName, String lastName, String email, String password, String verifyPassword, String role, String fileName, String messages,  ArrayList<Manager> managers, ArrayList<Librarian> librarians,
                    ArrayList<Administrator> admins) {
        T newUser = null;
        boolean verifyPass = false;
        boolean isCreated = false;

        if (password.equals(verifyPassword))
            verifyPass=true;

        if (role.equalsIgnoreCase("librarian"))
        {

            for (Librarian value : librarians) {
                if (value.getEmail().equals(email))
                {
                    isCreated=true;
                    break;
                }
            }
        }
        else if (role.equalsIgnoreCase("manager")) {
            for (Manager value : managers) {
                if (value.getEmail().equals(email))
                {
                    isCreated=true;
                    break;
                }
            }

        }
        else if (role.equalsIgnoreCase("administrator")||role.equalsIgnoreCase("admin"))
        {
            for (Administrator value : admins) {
                if (value.getEmail().equals(email))
                {
                    isCreated=true;
                    break;
                }
            }
        }


        if (verifyPass==true && isCreated==false) {
            newUser = (T) new User(firstName, lastName, email, password);
            addUser(newUser);

            StringBuilder contextBuilder = new StringBuilder();
            contextBuilder.append(newUser.getName()).append(",")
                    .append(newUser.getSurname()).append(",")
                    .append(newUser.getEmail()).append(",")
                    .append(newUser.getPassword());
                if ("librarian".equalsIgnoreCase(role)) {
                    contextBuilder.append(",0.0,0.0");
                    fileName = "C:\\Users\\Maçoku\\OneDrive\\Desktop\\Second Yr Assignments\\bookStore101\\src\\main\\files\\librarians.txt";
                }
                else if ("manager".equalsIgnoreCase(role))
                    fileName = "C:\\Users\\Maçoku\\OneDrive\\Desktop\\Second Yr Assignments\\bookStore101\\src\\main\\files\\managers.txt";
                else if ("admin".equalsIgnoreCase(role))
                    fileName = "C:\\Users\\Maçoku\\OneDrive\\Desktop\\Second Yr Assignments\\bookStore101\\src\\main\\files\\admins.txt";

                String context = contextBuilder.toString();
                System.out.println("konteksti: " + context +"File: " +fileName);

            try (FileWriter f = new FileWriter(fileName, true);
                 BufferedWriter b = new BufferedWriter(f);
                 PrintWriter p = new PrintWriter(b)) {

                p.println();
                p.print(context);
            } catch (IOException e1) {
                e1.printStackTrace();
                System.out.println("File not found");
            }
    }
        if (isCreated) {
            messages += "This user is already created; \n";
        }
        else {
            if (verifyPass)
                messages += "Wrong password";

        }
        return newUser;
    }

    public void printUsers() {
        for (T user : users) {
            System.out.println(user);
        }
    }

    public T getUser(String email, String password) {
        for (T user : users) {
            if (email.equals(user.getEmail()) && password.equals(user.getPassword())) {
                return user;
            }
        }
        return null; // User not found
    }

    public ArrayList<T> getUsers() {
        return users;
    }
}


