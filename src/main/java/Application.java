import dao.BookDao;
import dao.LoanDao;
import models.*;
import models.Author;
import models.Book;
import models.Genre;
import service.ValidationService;
import models.Person;
import models.*;
import utils.DataGenerator;


import java.util.List;
import java.util.Scanner;


public class Application {
    public static void main(String[] args) {
        System.out.println("Welcome to Library");
        System.out.println("Starting Data generation");
        DataGenerator dataGenerator = new DataGenerator();
        dataGenerator.generateBooks();


        Person person = new Person();

        do {
            Application.login();
        } while (!isAuthenticated);

        if (isAuthenticated && person.getPersonType() == PersonType.ADMIN) {
            System.out.println("Choose one of the option below: ");
            System.out.println("1. Show persons list ");
            System.out.println("2. Update persons info");
            System.out.println("3. Remove person ");
        }

      if(person.getPersonType() == null) {
          displayLoanOptions(person);
      }

    }

    private static void displayLoanOptions(Person person) {
        System.out.println("Choose one of the option below: ");
        System.out.println("1. List of my Loans \\n 2. Loan book \\n 3.Return book \\n 4.Exit");
        Scanner scanner = new Scanner(System.in);

        int option = Integer.parseInt(scanner.nextLine());
        LoanDao loanDao = new LoanDao();

        switch (option) {
            case 1:

                List<Loan> loanList = loanDao.getLoansByPerson(person);
                System.out.println("LIST OF LOANS:");
                System.out.println("  LOAN ID            |        " + "BOOK TITLE         |        " + "  LOAN STATUS        |        " + "  CREATED DATE       |        " + "  UPDATE DATE        |        ");

                for (Loan loan : loanList) {
                    System.out.println(loan.getId() + "  |  " + loan.getBook().getTitle() + "    |    " + loan.getLoanStatus().toString() + "     |     " + loan.getCreatedDate().toString() + "      |     " + loan.getUpdateDate().toString());
                }

                displayLoanOptions(person);
                break;
            case 2:
                BookDao bookDao = new BookDao();
                List<Book> bookList = bookDao.getBooks();
                System.out.println("Choose 1 book and enter the Book ID");
                Long bookId = Long.parseLong(scanner.nextLine());
                Book book = bookDao.getBookByID(bookId);
                Loan loan = loanDao.createLoan(book, person);
                System.out.println("Loan created successfully. Here are the loan details:");
                System.out.println("Loan Id:" + loan.getId());
                System.out.println("Book Title:" + loan.getBook().getTitle());
                System.out.println("Loan Status:" + loan.getLoanStatus());
                System.out.println("Created Date:" + loan.getCreatedDate());
                displayLoanOptions(person);
                break;
            ValidationService validationService = new ValidationService();
            if (!validationService.checkLogin(password, email)) {
                System.out.println("User doesn't exists, Enter the correct email:");

            case 3:
            } else {
                isAuthenticated = true;
            }

                List<Loan> loanList1 = loanDao.getLoansByPerson(person);
                System.out.println("LIST OF LOANS:");
                System.out.println("  LOAN ID            |        " + "BOOK TITLE         |        " + "  LOAN STATUS        |        " + "  CREATED DATE       |        " + "  UPDATE DATE        |        ");

                for (Loan l : loanList1) {
                    System.out.println(l.getId() + "  |  " + l.getBook().getTitle() + "    |    " + l.getLoanStatus().toString() + "     |     " + l.getCreatedDate().toString() + "      |     " + l.getUpdateDate().toString());
                }

                System.out.println("Choose 1 loan to return and enter the Loan ID");
                Long loanId = Long.parseLong(scanner.nextLine());
                Loan loan2 = loanDao.getLoanById(loanId);
                loanDao.returnBook(loan2);
                System.out.println("The loaned book has been returned successfully");

                displayLoanOptions(person);
                break;

            case 4:
                System.out.println("Thank you for choosing our library");
        }


                break;

            default:
                System.out.println("Invalid option, plese enter the correct option");
                displayLoanOptions(person);
                break;





        }

    }
}
}
