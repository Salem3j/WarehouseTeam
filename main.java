import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class main {
    public static void main(String[] args) throws IOException {
        //The current only file we have.
        String csvFile = "user_1.csv";
        //A test user using the basic User class
        User testUser = new User("John", "Doe", "jdoe1", "password", "jdoe1@email.com", "Intern", 100.00);

        boolean running = true;
        boolean logged_in = false;
        String session_username = "";
        String session_password = "";
        String user_action = "";
        Scanner scanner = new Scanner(System.in);
        while(running){
            if(!logged_in){
                System.out.println("Enter Your Username: ");
                session_username = scanner.nextLine();
                System.out.println("Enter Your Password: ");
                session_password = scanner.nextLine();


                //Need to add verification, as of now any username and password works.
                logged_in = true;
            }
            System.out.println("Please select an action(Add User, Log Out):");
            user_action = scanner.nextLine();
            if (user_action.equals("Add User")){
                //Collect new user details
                System.out.println("Enter the new user's first name: ");
                String newUserFirstName = scanner.nextLine();
                System.out.println("Enter the new user's last name: ");
                String newUserLastName = scanner.nextLine();
                System.out.println("Enter the new user's username: ");
                String newUserUsername = scanner.nextLine();
                System.out.println("Enter the new user's password: ");
                String newUserPassword = scanner.nextLine();
                System.out.println("Enter the new user's email: ");
                String newUserEmail = scanner.nextLine();
                System.out.println("Enter the new user's role: ");
                String newUserRole = scanner.nextLine();
                System.out.println("Enter the new user's salary(double): ");
                double newUserSalary = Double.parseDouble(scanner.nextLine());
                //New user object
                User newUser = new User(newUserFirstName, newUserLastName, newUserUsername, newUserPassword, newUserEmail, newUserRole, newUserSalary);
                //Open the file and add them the new user to the file
                FileWriter csvWriter_newUser = new FileWriter(csvFile, true);
                addUser(csvWriter_newUser, newUser);
                csvWriter_newUser.close();
                System.out.println("Returning to menu.");
            } else if (user_action.equals("Log Out")) {
                running = false;
            } else{
                System.out.println("Command not recognized. Please try again.");
            }
        }
        //The csv file writer.
        //FileWriter csvWriter_user_1 = new FileWriter(csvFile, true);
        //addUser(csvWriter_user_1, testUser);
        //csvWriter_user_1.close();



        //Salem's contribution for the SQL side of the database
        //Creating the users table
        /*
        UserTableManager.createUsersTable();
        //Adding a column to the users table
        UserTableManager.addColumnToUsersTable("age", "INT");
        //Adding a user to the users table
        UserTableManager.addUser("JohnDoe", "john.doe@example.com");
         */
    }

//AddCol() function not working.
    public static void addCol(FileWriter csvWriter, String colName) throws IOException {
        csvWriter.append(",");
        csvWriter.append(colName);
        return;
    }
    public static void addUser(FileWriter csvWriter, User newUser) throws IOException {
        csvWriter.write(newUser.getFirstName() + "," +
                newUser.getLastName() + "," +
                newUser.getUsername() + "," +
                newUser.getPassword() + "," +
                newUser.getEmail() + "," +
                newUser.getRole() + "," +
                newUser.getSalary() + ",");
        csvWriter.write("\n");
        System.out.println("User added successfully.");
        return;
    }

}

