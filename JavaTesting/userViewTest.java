package test;

import javafx.application.Platform;
import javafx.embed.swing.JFXPanel;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import main.*;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.testfx.api.FxToolkit;
import org.testfx.framework.junit.ApplicationTest;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.testfx.api.FxAssert.verifyThat;
import static org.testfx.util.NodeQueryUtils.hasText;

public class userViewTest extends ApplicationTest {

    @Override
    public void start(Stage stage) {
        //a part of the javafx components
        // Initialize JavaFX
        new JFXPanel();

        // Set up UI components
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

        Scene scene8 = new Scene(rigels, 600, 400);
        stage.setScene(scene8);
        stage.show();
    }
    ArrayList<Librarian> librarians = new ArrayList<>();

    //INITIALIZATION/PRE-CONDITION SETUP
    @BeforeAll
    public static void setup() throws Exception {
        // Initialize JavaFX
        FxToolkit.registerPrimaryStage();
        FxToolkit.setupApplication(userView::new);
    }

    //CLEANUP/POST-CONDITION SETUP
    @AfterAll
    public static void cleanup() throws Exception {
        FxToolkit.cleanupStages();
    }

    //ALL METHODS -> UI TESTING
    @Test
    public void testBookAdditionScene() {
        // Use TestFX to interact with the UI
        clickOn("#Done");

        verifyThat("#Donelbl", hasText("Successfully added"));

    }

    //INTEGRATION TESTING
    @Test
    public void testGenerateUsers() {
        // Run the test on the JavaFX Application Thread
        Platform.runLater(() -> {
            userController<User> newUserList = new userController<>();

            Manager manager = new Manager("ManagerName", "ManagerSurname", "manager1@email.com", "managerPassword");
            newUserList.addUser(manager);
            userView.generateUsers(newUserList, manager);
            assertEquals(manager,newUserList.getUser("manager1@email","managerPassword"));

        });
    }

    @Test
    public void testUserView() {
    // Create an instance of the UserView
    userView userView = new userView();

    Stage primaryStage = userView.getPrimaryStage();

    // Use JUnit assertions to test the UI components
    Scene scene1 = primaryStage.getScene();
    assertNotNull(scene1);

    VBox root = (VBox) scene1.getRoot();
    assertNotNull(root);

    Label welcomeLabel = (Label) root.getChildren().get(0);
    assertNotNull(welcomeLabel);
    assertEquals("Welcome to our bookstore!\n" + "Sign up or sign in.", welcomeLabel.getText());

    GridPane gridPane = (GridPane) root.getChildren().get(1);
    assertNotNull(gridPane);

    Button signUpButton = (Button) gridPane.getChildren().get(0);
    assertNotNull(signUpButton);
    assertEquals("Sign up", signUpButton.getText());

    Button signInButton = (Button) gridPane.getChildren().get(1);
    assertNotNull(signInButton);
    assertEquals("Sign in", signInButton.getText());

    Stage secondStage = new Stage();
        signUpButton.fireEvent(new MouseEvent(MouseEvent.MOUSE_CLICKED, 0, 0, 0, 0, MouseButton.PRIMARY, 1, true, true, true, true, true, true, true, true, true, true, null));
    assertEquals(scene1, primaryStage.getScene());

        signInButton.fireEvent(new MouseEvent(MouseEvent.MOUSE_CLICKED, 0, 0, 0, 0, MouseButton.PRIMARY, 1, true, true, true, true, true, true, true, true, true, true, null));
    // Assuming that scene3 is defined somewhere in your UserView class
    assertNotNull(primaryStage.getScene());
}
    //INTEGRATION TESTING
    @Test
    public void testPerformanceScene()
    {
        Librarian librarian = new Librarian();
        userView.generateUsers(userController,  librarian);
        librarians =userController.getUsers();

        clickOn("#check");

        verifyThat("#performancaLabel", hasText(librarians.get(0).checkPerformance()));
    }
    //INTEGRATION TESTING
    @Test
    public void testSearchBooks() {
        // Use TestFX to interact with the UI
        Platform.runLater(() -> {
            BookStore bookStore = new BookStore();
            bookStore.searchBooks(booksInStore, "SomeSearchTerm");

            List<Book> retrievedBooks = Main.getBooks("files/bookList.txt");
            Main.checkQuantity(retrievedBooks);

            // Count how many books match the search term
            long matchingBooks = retrievedBooks.stream()
                    .filter(book -> book.getTitle().contains("SomeSearchTerm") || book.getSupplier().contains("SomeSearchTerm"))
                    .count();

            assertEquals(matchingBooks, booksInStore.getItems().size());
        });
    }

}
