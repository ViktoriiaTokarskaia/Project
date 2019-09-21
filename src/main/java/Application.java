import dao.BookDao;
import models.Author;
import models.Book;
import models.Genre;
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

    }
}
