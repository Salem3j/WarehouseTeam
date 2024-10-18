
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Arrays;
import java.util.List;

public class main {
    public static void main(String[] args) throws IOException {
        //The current only file we have.
        String csvFile = "user_1.csv";
        //A test user using the basic User class
        User testUser = new User("John", "Doe", "jdoe1", "password", "jdoe1@email.com", "Intern", 100.00);

        //The csv file writer.
        FileWriter csvWriter_user_1 = new FileWriter(csvFile, true);

        addUser(csvWriter_user_1, testUser);

        csvWriter_user_1.close();

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

