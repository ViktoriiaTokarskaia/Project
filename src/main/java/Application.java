import dao.AuthorDao;
import models.Author;

import java.util.List;

public class Application {
    public static void main(String[] args) {

        AuthorDao authorDao = new AuthorDao();

        //Add new author
        Author author = new Author();
        author.setName("Shakespere");
        authorDao.saveAuthor(author);

        //List all authors
        List<Author> authors = authorDao.getAuthors();
        authors.forEach(a -> System.out.println(a.getName()));
    }
}
