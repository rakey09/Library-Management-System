import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class LibraryApp {
    private static Scanner scanner = new Scanner(System.in);
    private static BookDAO bookDAO = new BookDAO();
    private static UserDAO userDAO = new UserDAO();
    private static User loggedInUser = null;

    public static void main(String[] args) {
        while (true) {
            if (loggedInUser == null) {
                login();
            } else {
                showMenu();
            }
        }
    }

    private static void login() {
        System.out.println("Library Management System - Login");
        System.out.print("Enter username: ");
        String username = scanner.nextLine();
        System.out.print("Enter password: ");
        String password = scanner.nextLine();

        try {
            loggedInUser = userDAO.authenticateUser(username, password);
            if (loggedInUser != null) {
                System.out.println("Login successful. Welcome, " + loggedInUser.getUsername());
            } else {
                System.out.println("Invalid credentials. Please try again.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void showMenu() {
        System.out.println("Library Management System");
       
