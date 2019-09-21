import dao.BookDao;
import dao.PersonDao;
import models.*;
import service.ValidationService;
import dao.LoanDao;
import utils.DataGenerator;


import java.util.List;
import java.util.Scanner;


public class Application {
    private static boolean isAuthenticated = false;


    public static void main(String[] args) {
        System.out.println("Welcome to Library");
        System.out.println("Starting Data generation");
        DataGenerator dataGenerator = new DataGenerator();
        dataGenerator.generateBooks();
        Person person = new Person();
        do {
            person = login();
        } while (!isAuthenticated);

        if(isAuthenticated) {
            if(person.getPersonType() == PersonType.USER) {
                displayLoanOptions(person);
            }
            else if(person.getPersonType() == PersonType.ADMIN) {

            }
        }

    }

    private static Person login() {
        Scanner scan = new Scanner(System.in);
        System.out.println("Enter username:");
        String email = scan.nextLine();
        System.out.println("Enter password:");
        String password = scan.nextLine();

        ValidationService validationService = new ValidationService();
        Person person = validationService.checkLogin(email, password);
        if (person == null) {
            System.out.println("User not found or invalid credentials.");
            System.out.println("Choose the option 1. Relogin 2. Register");
            int option = Integer.parseInt(scan.nextLine());

            switch(option){
                case 1:
                    login();
                    break;
                case 2: registration();
                    break;

            }

        } else {
            isAuthenticated = true;
        }
        return person;
    }

    private static void registration() {
        Scanner scan = new Scanner(System.in);

        System.out.println("Insert your firstname");
        String firstname = scan.nextLine();
        System.out.println("Insert your lastname");
        String lastname = scan.nextLine();
        System.out.println("Create username, Insert existing email address");
        String email = scan.nextLine();
        System.out.println("Create new password, Use at least 8 characters, and special characters:");
        String password = scan.nextLine();
        //System.out.println("Choose person type:");


        Person person = new Person();
        person.setFirstName(firstname);
        person.setLastName(lastname);
        person.setEmail(email);
        person.setPassword(password);
        person.setPersonType(PersonType.USER);
        person.setPersonStatus(PersonStatus.ACTIVE);

        PersonDao personDao = new PersonDao();
        personDao.savePerson(person);

        System.out.println("You have successfully registered");
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
            case 3:
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
                break;

            default:
                System.out.println("Invalid option, plese enter the correct option");
                displayLoanOptions(person);
                break;

        }

    }

}
