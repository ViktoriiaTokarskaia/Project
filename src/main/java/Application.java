import dao.BookDao;
import models.Author;
import models.Book;
import models.Genre;
import models.Person;
import utils.DataGenerator;

import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.List;


public class Application {
    public static void main(String[] args) {
        System.out.println("Welcome to Library");
        System.out.println("Starting Data generation");
        DataGenerator dataGenerator = new DataGenerator();
        dataGenerator.generateBooks();

        Person person = new Person();
        if(person.getPersonType().getType() == "admin") {
            System.out.println("Choose one of the option below: ");
            System.out.println("1. List of my Loans \\n 2. Loan book \\n 3.Return book \\n 4.Exit");
        }

    }
}
