package main;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.StringTokenizer;

public class userView extends Application {
    public static void main(String[] args) {
        Application.launch(args);
    }
    Scene scene1, scene2, scene3, scene4, scene5, scene6, scene7, scene8, scene9, scene10;
    static String fileName = "";
    static String userType = "";
    userController<User> newUserList = new userController<>();

    public static void generateUsers(userController newUserList, Object object) {

        if (object instanceof Manager) {
            fileName = "C:\\Users\\Maçoku\\OneDrive\\Desktop\\Second Yr Assignments\\bookStore101\\src\\main\\files\\managers.txt";
            userType = "manager";
        } else if (object instanceof Librarian) {
            fileName = "C:\\Users\\Maçoku\\OneDrive\\Desktop\\Second Yr Assignments\\bookStore101\\src\\main\\files\\librarians.txt";
            userType = "librarian";
        } else if (object instanceof Administrator) {
            fileName = "C:\\Users\\Maçoku\\OneDrive\\Desktop\\Second Yr Assignments\\bookStore101\\src\\main\\files\\admins.txt";
            userType = "admin";
        }
        System.out.println(userType + " " + fileName);
        try {
            Scanner input = new Scanner(
                    new File(fileName));
            StringTokenizer token;
            String name;
            String surname;
            String email;
            String password;
            double profit;
            double booksNO;

            if (userType.equalsIgnoreCase("librarian")) {
                while (input.hasNextLine()) {
                    token = new StringTokenizer(input.nextLine(), ",");

                    name = token.nextToken();
                    surname = token.nextToken();
                    email = token.nextToken();
                    password = token.nextToken();

                    profit = Double.parseDouble(token.nextToken());
                    booksNO = Double.parseDouble(token.nextToken());

                    Librarian newUser = new Librarian(name, surname, email, password, profit, booksNO);
                    newUserList.addUser(newUser);

                }
            } else if (userType.equalsIgnoreCase("manager")) {
                while (input.hasNextLine()) {
                    token = new StringTokenizer(input.nextLine(), ",");

                    name = token.nextToken();
                    surname = token.nextToken();
                    email = token.nextToken();
                    password = token.nextToken();

                    Manager newUser = new Manager(name, surname, email, password);
                    newUserList.addUser(newUser);
                }
            } else if (userType.equalsIgnoreCase("admin")) {
                while (input.hasNextLine()) {
                    token = new StringTokenizer(input.nextLine(), ",");

                    name = token.nextToken();
                    surname = token.nextToken();
                    email = token.nextToken();
                    password = token.nextToken();

                    Administrator newUser = new Administrator(name, surname, email, password);
					newUserList.addUser(newUser);
                }
            }
        } catch (FileNotFoundException fnf) {
            System.out.println("File not found");
        }
    }


    public void start(Stage primaryStage) {
        //variable initialization and file reading
        //placeholder Objects of all types
        Librarian libPH = new Librarian();
        Manager manPH = new Manager();
        Administrator adminPH = new Administrator();

        //user which will be logged in while using the app
        final User[] user = new User[1];

        System.out.println(" otr");
        userController<Manager> newManagerList = new userController<>();
        generateUsers(newManagerList, manPH);
        System.out.println(" otr");
        ArrayList<Manager> managers = new ArrayList<>(newManagerList.getUsers());


        userController<Librarian> newLibrarianList = new userController<>();
        generateUsers(newLibrarianList, libPH);
        System.out.println(" otr");
        ArrayList<Librarian> librarians = new ArrayList<>(newLibrarianList.getUsers());


        //System.out.println(managers);

        userController<Administrator> newAdminList = new userController<>();
        generateUsers(newAdminList, adminPH);
        System.out.println(" otr");
        ArrayList<Administrator> admins = new ArrayList<>(newAdminList.getUsers());

        //test
        for (Librarian librarian : librarians) {
            System.out.println(librarian.toString2());
        }

        for (Manager manager : managers) {
            System.out.println(manager);
        }

        for (Administrator admin : admins) {
            System.out.println(admin);
        }

        ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        primaryStage.setTitle("Welcome to our Bookstore");
        // creating our first Page
        Button button1 = new Button("Sign up");
        Button button2 = new Button("Sign in");
        Label welcome = new Label("Welcome to our bookstore!\n" + "Sign up or sign in.");

        welcome.setStyle("-fx-font-size: 12");

        GridPane root = new GridPane();
        root.add(button1, 0, 2);
        root.add(button2, 1, 2);

        root.setHgap(10);
        root.setVgap(5);
        root.setPadding(new Insets(10));

        VBox exdee = new VBox();
        exdee.getChildren().add(welcome);
        exdee.setPadding(new Insets(10));
        exdee.getChildren().add(root);
        button1.setOnAction(event -> {
            primaryStage.setScene(scene2);
        });

        new EventHandler<MouseEvent>() {
            public void handle(MouseEvent event) {
            }
        };
        button2.setOnAction(event -> {
            primaryStage.setScene(scene3);
        });

        scene1 = new Scene(exdee);
        primaryStage.setScene(scene1);
        primaryStage.show();

///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        // create main pane
        // Sign up page
        VBox pane = new VBox(10);

        // create grid pane for elements
        GridPane gridPane = new GridPane();

        // create box pane for button
        HBox hBoxPane = new HBox(10);
        hBoxPane.setAlignment(Pos.CENTER);

        // Adding the grid pane and hbox pane to main pane
        Label successLabel = new Label("");
        successLabel.setAlignment(Pos.CENTER);

        pane.getChildren().addAll(gridPane, hBoxPane, successLabel);

        // creating labels
        Label firstNameLabel = new Label("First Name");
        Label lastNameLabel = new Label("Last Name");
        Label emailLabel = new Label("Email");
        Label passwordLabel = new Label("Password");
        Label verifyPassword = new Label("Verify Password");
        Label roleLabel = new Label("Role");

        // create button
        Button signUpButton = new Button("Sign up");
        Button mainPageButton = new Button("Sign in");

        // add button to hBoxPane
        hBoxPane.getChildren().addAll(signUpButton, mainPageButton);

        // create text fields
        TextField firstNameTF = new TextField();
        TextField lastNameTF = new TextField();
        TextField emailTF = new TextField();
		ChoiceBox<String> roleTF = new ChoiceBox<>();
		roleTF.setItems(FXCollections.observableArrayList("Librarian", "Manager", "Admin"));

		PasswordField passwordTF = new PasswordField();
        PasswordField verifyPasswordTF = new PasswordField();

        // adding text field to the gridpane
        // gridPane.add(signUpBtn, 0, 0);
        gridPane.add(firstNameLabel, 0, 1);
        gridPane.add(lastNameLabel, 0, 2);
        gridPane.add(emailLabel, 0, 3);
        gridPane.add(passwordLabel, 0, 4);
        gridPane.add(verifyPassword, 0, 5);
        gridPane.add(roleLabel, 0, 6);

        // gridPane.add(signInBtn, 1, 0);
        gridPane.add(firstNameTF, 1, 1);
        gridPane.add(lastNameTF, 1, 2);
        gridPane.add(emailTF, 1, 3);
        gridPane.add(passwordTF, 1, 4);
        gridPane.add(verifyPasswordTF, 1, 5);
        gridPane.add(roleTF, 1, 6);

        // adding some styling

        pane.setPadding(new Insets(20, 20, 20, 20));
        pane.setAlignment(Pos.CENTER);
        gridPane.setHgap(10);
        gridPane.setVgap(5);
        firstNameTF.setAlignment(Pos.BASELINE_RIGHT);
        lastNameTF.setAlignment(Pos.BASELINE_RIGHT);

        // creating a scene and add to main pane
        scene2 = new Scene(pane);

        // adding title and a stage to the scene
        primaryStage.setTitle("MVC Program");

        // showing stage


        signUpButton.setOnAction(e -> {
			String messages = " ";
            boolean isCreated = false;
            newUserList.signUp(firstNameTF.getText(), lastNameTF.getText(), emailTF.getText(),
                    passwordTF.getText(), verifyPasswordTF.getText(), roleTF.getValue(), fileName, messages, managers, librarians, admins);
            if (user[0] != null)
                isCreated = true;

            System.out.println("Creating user: " + isCreated);

            if (isCreated) {
                user[0] = new User(firstNameTF.getText(), lastNameTF.getText(), emailTF.getText(),
                        passwordTF.getText());

                firstNameTF.clear();
                lastNameTF.clear();
                emailTF.clear();
                passwordTF.clear();
                verifyPasswordTF.clear();
                roleTF.hide();


                successLabel.setText("Success creating the user!");
                newUserList.printUsers();

            } else {
                successLabel.setText(messages);
            }
        });

        VBox adminpane = new VBox(10);

        // create grid pane for elements
        GridPane admingridPane = new GridPane();

        // create box pane for button
        HBox adminhBoxPane = new HBox(10);
        Label message = new Label("");
        adminhBoxPane.setAlignment(Pos.CENTER);

        adminpane.getChildren().addAll(admingridPane, adminhBoxPane, message);

        // creating labels
        Label adminfirstNameLabel = new Label("First Name");
        Label adminlastNameLabel = new Label("Last Name");
        Label adminemailLabel = new Label("Email");
        Label adminpasswordLabel = new Label("Password");
        Label adminverifyPassword = new Label("Verify Password");
        Label adminroleLabel = new Label("Role");

        // create button
        Button adminsignUpButton = new Button("Finish");

        // add button to hBoxPane
        adminhBoxPane.getChildren().addAll(adminsignUpButton);

        // create text fields
        TextField adminfirstNameTF = new TextField();
        TextField adminlastNameTF = new TextField();
        TextField adminemailTF = new TextField();

		ChoiceBox<String> adminroleTF = new ChoiceBox<>();
		adminroleTF.setItems(FXCollections.observableArrayList("Librarian", "Manager", "Admin"));

		PasswordField adminpasswordTF = new PasswordField();
        PasswordField adminverifyPasswordTF = new PasswordField();

        // adding text field to the gridpane
        // gridPane.add(signUpBtn, 0, 0);
        admingridPane.add(adminfirstNameLabel, 0, 1);
        admingridPane.add(adminlastNameLabel, 0, 2);
        admingridPane.add(adminemailLabel, 0, 3);
        admingridPane.add(adminpasswordLabel, 0, 4);
        admingridPane.add(adminverifyPassword, 0, 5);
        admingridPane.add(adminroleLabel, 0, 6);

        // gridPane.add(signInBtn, 1, 0);
        admingridPane.add(adminfirstNameTF, 1, 1);
        admingridPane.add(adminlastNameTF, 1, 2);
        admingridPane.add(adminemailTF, 1, 3);
        admingridPane.add(adminpasswordTF, 1, 4);
        admingridPane.add(adminverifyPasswordTF, 1, 5);
        admingridPane.add(adminroleTF, 1, 6);

        // adding some styling

        adminpane.setPadding(new Insets(20, 20, 20, 20));
        adminpane.setAlignment(Pos.CENTER);
        admingridPane.setHgap(10);
        admingridPane.setVgap(5);
        adminfirstNameTF.setAlignment(Pos.BASELINE_RIGHT);
        adminlastNameTF.setAlignment(Pos.BASELINE_RIGHT);

        Label notification = new Label("");

        // creating a scene and add to main pane

        scene10 = new Scene(adminpane);

        // adding title and a stage to the scene

        // showing stage

        adminsignUpButton.setOnAction(e2 -> {
			String messages = "";
            String filename2 = "";
            if (adminroleTF.getValue().equalsIgnoreCase("librarian"))
                filename2 = "files/librarians.txt";
            else if (adminroleTF.getValue().equalsIgnoreCase("manager"))
                filename2 = "files/librarians.txt";
            else if (adminroleTF.getValue().equalsIgnoreCase("admin"))
                filename2 = "files/admins.txt";
            else
                notification.setText("wrong Role");

            boolean isCreated = false;

			user[0] = newUserList.signUp(adminfirstNameTF.getText(), adminlastNameTF.getText(), adminemailTF.getText(),
                    adminpasswordTF.getText(), adminverifyPasswordTF.getText(), adminroleTF.getValue(), filename2, messages, managers, librarians, admins);
            if (user[0] != null)
                isCreated = true;
            System.out.println("Creating user: " + isCreated);

            if (isCreated) {
                primaryStage.setScene(scene9);
            }
			else
				message.setText(messages);
        });
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

        VBox pane2 = new VBox(10);

        // create grid pane for elements
        GridPane gridPane2 = new GridPane();

        // create box pane for button
        HBox hBoxPane2 = new HBox(10);
        hBoxPane2.setAlignment(Pos.CENTER);

        Label successLabel2 = new Label("");
        successLabel2.setAlignment(Pos.CENTER);

        // Adding the grid pane and hbox pane to main pane
        pane2.getChildren().addAll(gridPane2, successLabel2, hBoxPane2);

        // creating labels
        Label emailLbl = new Label("Email");
        Label passwordLbl = new Label("Password");
        Label roleLbl = new Label("Role");

        // create button
        Button signInButton = new Button("Sign In");

        // add button to hBoxPane
        hBoxPane2.getChildren().add(signInButton);

        // create text fields
        TextField emailTF2 = new TextField();
        PasswordField passwordTF2 = new PasswordField();
		ChoiceBox<String> roleTF2 = new ChoiceBox<>();
		roleTF2.setItems(FXCollections.observableArrayList("Librarian", "Manager", "Admin"));


		// adding text field to the gridpane
        // gridPane.add(signUpBtn, 0, 0);
        gridPane2.add(emailLbl, 0, 1);
        gridPane2.add(passwordLbl, 0, 2);
        gridPane2.add(roleLbl, 0, 3);

        gridPane2.add(emailTF2, 1, 1);
        gridPane2.add(passwordTF2, 1, 2);
        gridPane2.add(roleTF2, 1, 3);

        // adding some styling

        pane2.setPadding(new Insets(20, 20, 20, 20));
        pane2.setAlignment(Pos.CENTER);
        gridPane2.setHgap(10);
        gridPane2.setVgap(5);

        mainPageButton.setOnAction(event -> {
            if (roleTF2.getValue().equalsIgnoreCase("librarian")) {
                mainPageButton.setText("Go Back");
                primaryStage.setScene(scene9);
            } else primaryStage.setScene(scene3);
        });
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        HBox managerScene = new HBox(30);
        Button checkPerformance = new Button("Check Performance");
        Button addBook = new Button("Add Book");
        Button getSoldBooks = new Button("Show sold Books");
        Button SignOut = new Button("Sign Out");
        HBox.setMargin(SignOut, new Insets(20, 20, 20, 20));
        HBox.setMargin(checkPerformance, new Insets(20, 20, 20, 20));
        HBox.setMargin(getSoldBooks, new Insets(20, 20, 20, 20));
        HBox.setMargin(addBook, new Insets(20, 20, 20, 20));
        managerScene.getChildren().addAll(checkPerformance, getSoldBooks, addBook, SignOut);
        scene6 = new Scene(managerScene);

        SignOut.setOnAction(e ->
        {
            primaryStage.setScene(scene2);
        });
        checkPerformance.setOnAction(e ->
        {
            primaryStage.setScene(scene7);
        });

        getSoldBooks.setOnAction(e ->
        {
            File file = new File("files/soldBooks.txt");
            Main.openFile(file);
        });

        VBox performanceScene = new VBox();
        ListView<Librarian> librarianList = new ListView<>();

        librarianList.getItems().addAll(librarians);
        FlowPane tekst = new FlowPane();
        Button check = new Button("Check");
        Button goBack = new Button("Go back");
        HBox butonat = new HBox(30);
        butonat.getChildren().addAll(check, goBack);

        goBack.setOnAction(e ->
        {
            if (roleTF2.getValue().equalsIgnoreCase("manager"))
                primaryStage.setScene(scene6);
            else
                primaryStage.setScene(scene9);
        });

        Label performancaLabel = new Label("");
        tekst.getChildren().add(performancaLabel);

        performanceScene.getChildren().addAll(librarianList, butonat, tekst);
        scene7 = new Scene(performanceScene);

        check.setOnAction(e ->
        {
            Librarian librarian = librarianList.getSelectionModel().getSelectedItem();
            performancaLabel.setText(librarian.checkPerformance());
        });
        addBook.setOnAction(e ->
        {
            primaryStage.setScene(scene8);
        });

        VBox rigels = new VBox();
        rigels.setPadding(new Insets(10, 10, 10, 10));
        HBox lilKoli = new HBox();
        HBox varrosi = new HBox();

        Label ISBN = new Label("ISBN");
        ISBN.setPrefWidth(150);
        Label Title = new Label("Title");
        Title.setPrefWidth(150);
        Label Category = new Label("Category");
        Category.setPrefWidth(150);
        Label Author = new Label("Author");
        Author.setPrefWidth(150);
        Label Price = new Label("Price");
        Price.setPrefWidth(150);
        lilKoli.getChildren().addAll(ISBN, Title, Category, Author, Price);

        Button done = new Button("Done");
        Button Backpls = new Button("Go Back");

        Backpls.setOnAction(e ->
        {
            if (roleTF2.getValue().equalsIgnoreCase("manager"))
                primaryStage.setScene(scene6);
            else
                primaryStage.setScene(scene9);
        });


        Label Donelbl = new Label("");

        TextField ISBNTF = new TextField();
        TextField TitleTF = new TextField();
        TextField CategoryTF = new TextField();
        TextField AuthorTF = new TextField();
        TextField PriceTF = new TextField();

        varrosi.getChildren().addAll(ISBNTF, TitleTF, CategoryTF, AuthorTF, PriceTF);
        HBox klestiOTR = new HBox();
        klestiOTR.getChildren().addAll(done, Backpls);
        rigels.getChildren().addAll(lilKoli, varrosi, klestiOTR, Donelbl);
        scene8 = new Scene(rigels);

        done.setOnAction(e ->
        {
            ArrayList<Book> books = Main.getBooks("files/bookList.txt");
            Main.checkQuantity(books);
            Donelbl.setText(Manager.addBook(books,Integer.parseInt(ISBNTF.getText()), TitleTF.getText(), CategoryTF.getText(), AuthorTF.getText(),
                    Double.parseDouble(PriceTF.getText())));

            Librarian.reWriteBooks(books, "files/bookList.txt");
            System.out.println(books);
            Donelbl.setText("Successfully added");
            ISBNTF.clear();
            TitleTF.clear();
            CategoryTF.clear();
            AuthorTF.clear();
            PriceTF.clear();
        });

        signInButton.setOnAction(e -> {
            user[0] = newUserList.signIn(emailTF2.getText(), passwordTF2.getText(), roleTF2.getValue(),
                    managers, librarians, admins);
            if (user[0] instanceof Librarian) {
                primaryStage.setScene(scene4);
            } else if (user[0] instanceof Manager) {
                primaryStage.setScene(scene6);
            }
            else if (user[0] instanceof Administrator) {
                primaryStage.setScene(scene9);
            }
            else{
                successLabel2.setText("Error logging in, please try again");
                System.out.println("otr");

            }

        });

        scene3 = new Scene(pane2);

        HBox adminScene = new HBox();
        Button adminCheckPerformance = new Button("Check Performance");
        HBox.setMargin(adminCheckPerformance, new Insets(10));
        Button adminAddBook = new Button("Add Book");
        HBox.setMargin(adminAddBook, new Insets(10));
        Button removeUser = new Button("Remove User");
        HBox.setMargin(removeUser, new Insets(10));
        Button addUser = new Button("Add User");
        HBox.setMargin(addUser, new Insets(10));

        adminScene.getChildren().addAll(adminCheckPerformance, adminAddBook, removeUser, addUser);
        scene9 = new Scene(adminScene);

        adminCheckPerformance.setOnAction(e ->
        {
            primaryStage.setScene(scene7);
        });

        adminAddBook.setOnAction(e -> {
            primaryStage.setScene(scene8);
        });

        addUser.setOnAction(e ->
        {
            primaryStage.setScene(scene10);
        });

        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        ArrayList<Book> books;
        books = Main.getBooks("files/bookList.txt");
        Main.checkQuantity(books);

        System.out.println(emailTF2.getText());

        HBox hbox = new HBox();

        primaryStage.setTitle("Welcome Biblotekar");
        Button ChooseBookBtn = new Button("Choose Book");
        Button GetBillBtn = new Button("Get Bill");
        Button SignOut2 = new Button("Sign Out");

        HBox.setMargin(ChooseBookBtn, new Insets(20, 20, 20, 20));
        HBox.setMargin(GetBillBtn, new Insets(20, 20, 20, 20));
        HBox.setMargin(SignOut2, new Insets(20, 20, 20, 20));
        hbox.getChildren().addAll(ChooseBookBtn, GetBillBtn, SignOut2);

        SignOut2.setOnAction(e ->
        {
            primaryStage.setScene(scene2);
        });

        scene4 = new Scene(hbox);

        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        ListView<Book> booksInStore = new ListView<>();
        System.out.println(books);
        booksInStore.getItems().addAll(books);
        ListView<Book> selectedBooks = new ListView<>();
        for (int i = 0; i < books.size(); i++) {
            System.out.println(booksInStore.getItems().get(i).toString());
            System.out.println(booksInStore.getItems().get(i).getQuantity());
        }
        booksInStore.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);

        Button add = new Button("Add");
        Button finish = new Button("Finish");
        HBox searchBar = new HBox();
        Button search = new Button("Search");
        search.setPrefWidth(150);
        TextField searchTF = new TextField();
        Button clear = new Button("Clear");
        searchBar.getChildren().addAll(searchTF, search, clear);
        // submit.setOnAction(e-> librarian.checkOutBook());
        VBox layout = new VBox(10);
        HBox buttons = new HBox();
        Label Stock = new Label("Books in Stock");
        Label order = new Label("Your order");
        buttons.getChildren().addAll(add, finish);
        layout.setPadding(new Insets(20, 20, 20, 20));
        layout.getChildren().addAll(searchBar, Stock, booksInStore, order, selectedBooks, buttons);
        booksInStore.setPrefHeight(200);
        layout.setPrefWidth(1000);
        selectedBooks.setPrefHeight(200);
        layout.fillWidthProperty();
        scene5 = new Scene(layout);


        ChooseBookBtn.setOnAction(e -> {

            try {
                // Overwrite the file with an empty string
                PrintWriter writer = new PrintWriter("files/Bill.txt");
                writer.print("");
                writer.close();

                System.out.println("File contents deleted successfully.");
            } catch (IOException ioe) {
                System.out.println("Error deleting file contents: " + ioe.getMessage());
            }
            primaryStage.setScene(scene5);

        });
        search.setOnAction(e -> {
            searchBooks(booksInStore, searchTF.getText());
            primaryStage.setScene(scene5);
        });
        clear.setOnAction(e -> {
            retrieveBooks(booksInStore);
            primaryStage.setScene(scene5);
        });
        add.setOnAction(e -> {
            Librarian librarian = (Librarian) user[0];
            //Librarian librarian = newLibrarianList.getLibrarian(emailTF2.getText(), passwordTF2.getText());//
            System.out.println(emailTF2.getText());
            librarian.addBooks(booksInStore, selectedBooks);
            try {
                librarian.buttonClicked(booksInStore);
            } catch (NoBooksException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            } catch (IOException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            }
            try (FileWriter f = new FileWriter(
                    "files/librarians.txt");
                 BufferedWriter b = new BufferedWriter(f);
                 PrintWriter p = new PrintWriter(b)) {
                for (Librarian value : librarians) {
                    p.println(
                            value.getName() + "," + value.getSurname() + "," + value.getEmail() + "," +
                                    value.getPassword() + "," + value.getProfit() + "," +
                                    value.getbooksNO());

                }
            } catch (IOException e1) {
                e1.printStackTrace();
            }
            primaryStage.setScene(scene5);
        });

        finish.setOnAction(e -> {
            selectedBooks.getItems().clear();
            primaryStage.setScene(scene4);
        });

        GetBillBtn.setOnAction(e ->
        {
            File file = new File("files/Bill.txt");
            Main.openFile(file);

        });
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
 /*
    private void addButton(ListView<Book> booksInStore, ListView<Book> auga) {

        ArrayList<Book> books2 = new ArrayList<>(auga.getItems());
        ObservableList<Book> selectedBooks = booksInStore.getSelectionModel().getSelectedItems();
        ArrayList<Book> books = new ArrayList<>(selectedBooks);
        for (Book book : books) {
            try (FileWriter f = new FileWriter(
                    "soldBooks.txt", true);
                 BufferedWriter b = new BufferedWriter(f);
                 PrintWriter p = new PrintWriter(b)) {
                p.println(book.getISBN() + ", " + book.getTitle() + ", " + book.getCategory()
                        + ", " + book.getSupplier() + ", " + book.getPurchasedPrice() + ", ");
            } catch (IOException e2) {
                e2.printStackTrace();
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
*/
    /*
    private static void buttonClicked(ListView<Book> booksInStore, Librarian librarian) throws NoBooksException, IOException {

        ObservableList<Book> temp;
        temp = booksInStore.getSelectionModel().getSelectedItems();
        ArrayList<Book> books = new ArrayList<>(booksInStore.getItems());

        ArrayList<Book> soldBooks = new ArrayList<>(temp);
        reWriteBooks(books);
        // get indices of all the selected books
        ArrayList<Integer> indices = new ArrayList<>(booksInStore.getSelectionModel().getSelectedIndices());
        System.out.println(indices);

        // System.out.println(books);
        librarian.CheckOutBook(soldBooks);

        System.out.println(librarian.checkPerformance());
        librarian.CreateBill(soldBooks);
        booksInStore.getItems().clear();
        booksInStore.getItems().addAll(books);

        // fix soldBooks arrayList so we can avoid table redundancy

    }
*/
    /*
    public static void reWriteBooks(ArrayList<Book> books) {

        try (FileWriter f = new FileWriter(
                "BookList.txt");
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

     */

    public void retrieveBooks(ListView<Book> booksInStore) {
        ArrayList<Book> books;
        books = Main.getBooks("files/bookList.txt");
        Main.checkQuantity(books);
        booksInStore.getItems().addAll(books);
    }

    public void searchBooks(ListView<Book> booksInStore, String searchTerm) {
        ArrayList<Book> books;
        books = Main.getBooks("files/bookList.txt");
        Main.checkQuantity(books);
        booksInStore.getItems().clear();
        for (Book book : books) {
            if (book.getTitle().contains(searchTerm) || book.getSupplier().contains(searchTerm)) {
                booksInStore.getItems().add(book);
            }
        }
    }
}
