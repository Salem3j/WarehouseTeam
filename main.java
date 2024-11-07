import java.io.*;
import java.nio.file.Files;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class main {
    public static void main(String[] args) throws IOException {
        //The current only file we have.
        String csvFile_UserFile = "user_1.csv";
        //A test user using the basic User class
        User testUser = new User("John", "Doe", "jdoe1", "password", "jdoe1@email.com", "Intern", 100.00);

        boolean running = true;
        boolean logged_in = false;
        String session_username = "";
        String session_password = "";
        String user_action = "";
        Scanner scanner = new Scanner(System.in);
        while (running) {
            if (!logged_in) {
                System.out.println("Enter Your Username: ");
                session_username = scanner.nextLine();
                System.out.println("Enter Your Password: ");
                session_password = scanner.nextLine();

                if (findInFile(session_username, csvFile_UserFile) && findInFile(session_password, csvFile_UserFile)) {
                    System.out.println("Welcome to the Warehouse Management System.");
                    logged_in = true;
                }else{
                    System.out.println("Username and/or Password was incorrect. \n" +
                            "Unauthorized Access is prohibited.");
                    running = false;
                    break;
                }
            }
            System.out.println("Please select an action(Add User, Log Out):");
            user_action = scanner.nextLine();
            if (user_action.equals("Add User")) {
                addUser(scanner, csvFile_UserFile);
            } else if (user_action.equals("Log Out")) {
                running = false;
            } else {
                System.out.println("Command not recognized. Please try again.");
            }
        }

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

    public static void addUser(Scanner scanner, String csvFile) throws IOException {
        boolean userAlreadyExists;
        //Collect new user details
        System.out.println("Enter the new user's first name: ");
        String newUserFirstName = scanner.nextLine();
        System.out.println("Enter the new user's last name: ");
        String newUserLastName = scanner.nextLine();
        System.out.println("Enter the new user's username: ");
        String newUserUsername = scanner.nextLine();
        //To see if a user with this username already exists
        userAlreadyExists = findInFile(newUserUsername, csvFile);
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

        if (userAlreadyExists) {
            System.out.println("User with that username already exists. Usernames must be unique. \nPlease try again.");
            return;
        } else {
            csvWriter_newUser.write(newUser.getFirstName() + "," +
                    newUser.getLastName() + "," +
                    newUser.getUsername() + "," +
                    newUser.getPassword() + "," +
                    newUser.getEmail() + "," +
                    newUser.getRole() + "," +
                    newUser.getSalary());
            csvWriter_newUser.write("\n");
            csvWriter_newUser.close();
            System.out.println("User added successfully.");
            System.out.println("Returning to menu.");
            return;
        }
    }
    public static boolean findInFile(String searchTerm, String fileName) throws FileNotFoundException {
        Scanner fileInput = new Scanner(new File(fileName));
        String line;
        while(fileInput.hasNextLine()){
            line = fileInput.nextLine().trim();
            //Skip blank lines (if any)
            if(line.equals("")){
                continue;
            }
            // Split the curently read line into a String Array
            // based on  the comma (,) delimiter
            String[] lineParts = line.split("\\s{0,},\\s{0,}"); // Split on any comma/space situation.
            // Iterate through the lineParts array to see if any
            // delimited portion equals the search term.
            for (int i = 0; i < lineParts.length; i++) {
                if (lineParts[i].equals(searchTerm)) {
                    // Found a match
                    return true;
                    // Get out of this loop. Don't need it anymore for this line.
                }
            }
        }
        return false;
    }

}

