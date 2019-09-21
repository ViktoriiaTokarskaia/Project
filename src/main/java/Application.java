import dao.BookDao;
import dao.PersonDao;
import models.*;
import service.ValidationService;
import dao.LoanDao;
import utils.DataGenerator;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;

public class Application {
    private static boolean isAuthenticated = false;

    public static void main(String[] args) {
        @SuppressWarnings("unused")
        org.jboss.logging.Logger logger = org.jboss.logging.Logger.getLogger("org.hibernate");
        java.util.logging.Logger.getLogger("org.hibernate").setLevel(java.util.logging.Level.WARNING);

        //TODO: Make some interactive design. Example: https://miro.medium.com/max/2912/1*8jHmQ7RRxLwH6aXCO4ssAg.png
        System.out.println("Welcome to Library");
        java.util.logging.Logger.getLogger("org.hibernate").setLevel(Level.OFF);
        DataGenerator dataGenerator = new DataGenerator();
        dataGenerator.generateBooks();
        Person person = new Person();
        do {
            person = login();
        } while (!isAuthenticated);

        if(isAuthenticated) {
            if(person.getPersonType() == PersonType.USER) {
                //TODO: Check if this person's loans has surpased the return date. If so, then change the status of loan to OVERDUE.
                //TODO: Check if this user has OVERDUE loans and display a message a notification that he has OVERDUE loans to look over.
                displayLoanOptions(person);
            }
            else if(person.getPersonType() == PersonType.ADMIN) {
                //TODO: Add admin functionality (Should be similarly like displayOptions() see below.
            }
        }
    }

    private static Person login() {
        Scanner scan = new Scanner(System.in);
        System.out.println("Enter username:");
        String email = scan.nextLine();
        System.out.println("Enter password:"); //TODO: When entering password characters shouldn't be visible instead '*' characters
        String password = scan.nextLine();

        ValidationService validationService = new ValidationService();
        Person person = validationService.getLogin(email, password);
        if (person == null) {
            System.out.println("User not found or invalid credentials.");
            System.out.println("Choose the option:\n1. Re-login\n2. Register");
            int option = Integer.parseInt(scan.nextLine().trim());

            switch(option){
                case 1:
                    login();
                    break;
                case 2:
                    registration();
                    break;

            }
        } else {
            System.out.println("\nWelcome " + person.getFirstName() + " " + person.getLastName() + "!\n\n");
            isAuthenticated = true;
        }
        return person;
    }

    private static void registration() {
        Scanner scan = new Scanner(System.in);

        System.out.println("Please enter the details for registration:");
        System.out.println("First name:");
        String firstName = scan.nextLine();
        System.out.println("Last name:");
        String lastName = scan.nextLine();
        System.out.println("E-mail address (username):");
        String email = scan.nextLine();
        System.out.println("Password (Use at least 8 characters, and special characters):");
        String password = scan.nextLine();
        //TODO: Retype password and have password validation
        System.out.println("Choose your type:\n1. " + PersonType.USER + "\n2. " + PersonType.ADMIN);
        PersonType personType = Integer.parseInt(scan.nextLine().trim()) == 1 ? PersonType.USER :PersonType.ADMIN;

        Person person = new Person();
        person.setFirstName(firstName);
        person.setLastName(lastName);
        person.setEmail(email);
        person.setPassword(password);
        person.setPersonType(personType);
        person.setPersonStatus(PersonStatus.ACTIVE);

        PersonDao personDao = new PersonDao();
        personDao.savePerson(person);
        System.out.println("You have successfully registered. Please login:");
    }

    private static void displayLoanOptions(Person person) {
        System.out.println("Choose one of the option below: ");
        System.out.println("1. List of my Loans\n2. Loan book\n3. Return book\n4. Exit");
        Scanner scanner = new Scanner(System.in);

        int option = Integer.parseInt(scanner.nextLine().trim());
        LoanDao loanDao = new LoanDao();

        switch (option) {
            case 1:
                List<Loan> loanList = loanDao.getLoansByPersonId(person.getId());
                //TODO: if list empty, then show message that No loans found instead of table below
                //TODO: Display good like table structure. See example: https://stackoverflow.com/questions/15215326/how-can-i-create-table-using-ascii-in-a-console
                System.out.println("LIST OF LOANS:");
                System.out.println("        LOAN ID            |        " + "       BOOK TITLE         |        " + "  LOAN STATUS        |        " + "  CREATED DATE       |        " + "  RETURN DATE        |        ");

                for (Loan loan : loanList) {
                    System.out.println(loan.getId() + "  |  " + loan.getBook().getTitle() + "    |    " + loan.getLoanStatus() + "     |     " + loan.getCreatedDate() + "      |     " + loan.getReturnDate());
                }
                displayLoanOptions(person);
                break;
            case 2:
                BookDao bookDao = new BookDao();
                List<Book> bookList = bookDao.getAllBooks(); //TODO: Modify this list to get list of all available books (Book should not be taken/loan by other person)
                //TODO: If list empty, then show message that No books found instead of table below
                //TODO: Display good like table structure. See example: https://stackoverflow.com/questions/15215326/how-can-i-create-table-using-ascii-in-a-console
                System.out.println("List of available books:");
                for (Book book : bookList) {
                    System.out.println(book.getId() + ". " + book.getTitle());
                }
                System.out.println("Choose 1 book from the above list and enter the Book ID:");
                Long bookId = Long.parseLong(scanner.nextLine().trim());
                Book book = bookDao.getBookByID(bookId);
                Loan loan = loanDao.createLoan(book, person);
                System.out.println("Loan created successfully. Here are the loan details:");
                System.out.println("Loan Id:" + loan.getId());
                System.out.println("Book Title:" + loan.getBook().getTitle());
                System.out.println("Loan Status:" + loan.getLoanStatus());
                System.out.println("Loan Date:" + loan.getCreatedDate());
                System.out.println("Return Date:" + loan.getReturnDate());
                displayLoanOptions(person);
                break;
            case 3:
                List<Loan> loanList1 = loanDao.getLoansByPersonId(person.getId());
                //TODO: If list empty, then show message that No loans found instead of table below
                //TODO: Display good like table structure. See example: https://stackoverflow.com/questions/15215326/how-can-i-create-table-using-ascii-in-a-console
                System.out.println("LIST OF LOANS:");
                System.out.println("  LOAN ID            |        " + "BOOK TITLE         |        " + "  LOAN STATUS        |        " + "  CREATED DATE       |        " + "  RETURN DATE        |        ");
                for (Loan l : loanList1) {
                    System.out.println(l.getId() + "  |  " + l.getBook().getTitle() + "    |    " + l.getLoanStatus().toString() + "     |     " + l.getCreatedDate().toString() + "      |     " + l.getReturnDate().toString());
                }

                System.out.println("Choose 1 loan to return and enter the Loan ID");
                Long loanId = Long.parseLong(scanner.nextLine().trim());
                Loan loan2 = loanDao.getLoanById(loanId);
                //TODO: Ask user if the book is DAMAGED/LOST, if yes set LoanStatus accordingly
                loanDao.returnBook(loan2);
                System.out.println("The loaned book has been returned successfully");
                displayLoanOptions(person);
                break;
            case 4:
                System.out.println("Thank you for choosing our library. Logged out.");
                break;

            default:
                System.out.println("Invalid option, please enter the correct option");
                displayLoanOptions(person);
                break;
        }
    }
}
