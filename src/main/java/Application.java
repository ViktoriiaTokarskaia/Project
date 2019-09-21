import dao.BookDao;
import models.Author;
import models.Book;
import models.Genre;
import service.ValidationService;
import models.Person;
import utils.DataGenerator;

import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class Application {
    public static void main(String[] args) {
        System.out.println("Welcome to Library");
        System.out.println("Starting Data generation");
        DataGenerator dataGenerator = new DataGenerator();
        dataGenerator.generateBooks();


        Scanner scan = new Scanner(System.in);
        System.out.println("Enter username:");
        String email= scan.nextLine();
        System.out.println("Enter password:");
        String password= scan.nextLine();

        ValidationService validationService = new ValidationService();
        if(!validationService.checkEmail(email)){
            System.out.println("User doesn't exists, Enter the correct email:");
            email= scan.nextLine();


        }



        String pass = scan.nextLine(); // looks at selected file in scan


    }

    }
