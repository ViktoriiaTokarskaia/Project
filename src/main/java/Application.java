import dao.BookDao;
import dao.LoanDao;
import models.*;
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
    private static boolean isAuthenticated = false;

    public static void main(String[] args) {
        System.out.println("Welcome to Library");
        System.out.println("Starting Data generation");
        DataGenerator dataGenerator = new DataGenerator();
        dataGenerator.generateBooks();

        do{
            Application.login();
        }while(!isAuthenticated);

        Scanner scan = new Scanner(System.in);
        System.out.println("Enter username:");
        String email= scan.nextLine();
        System.out.println("Enter password:");
        String password= scan.nextLine();

        Person person = new Person();
//        if(person.getPersonType() == PersonType.ADMIN) {
//            System.out.println("Choose one of the option below: ");
//            System.out.println("1. List of my Loans \\n 2. Loan book \\n 3.Return book \\n 4.Exit");
//        }


      if(person.getPersonType() == null){
          displayOptions();
          Scanner scanner = new Scanner(System.in);
          int option = Integer.parseInt(scanner.nextLine());
          LoanDao loanDao = new LoanDao();


          switch(option){
              case 1:
                  List<Loan> loanList = loanDao.getLoansByPerson(person);
                  System.out.println("LIST OF LOANS:");
                  System.out.println("  LOAN ID            |        ");
                  System.out.println("  BOOK TITLE         |        ");
                  System.out.println("  LOAN STATUS        |        ");
                  System.out.println("  CREATED DATE       |        ");
                  System.out.println("  UPDATE DATE        |        ");




                  for(Loan loan: loanList){
                      System.out.println(loan.getId().toString() + "  |  " +  loan.getBook().getTitle()  + "    |    "  +  loan.getLoanStatus().toString()  +  "     |     "  + loan.getCreatedDate().toString()  + "      |     " + loan.getUpdateDate().toString());
                  }
                  break;

              case 2:

                  break;

              case 3:


                   break;

              case 4:


                  break;

              default:
                  System.out.println("Invalid option, plese enter the correct option");
                  displayOptions();
                  break;


          }
        ValidationService validationService = new ValidationService();
        if(!validationService.checkLogin(password, email)){
            System.out.println("User doesn't exists, Enter the correct email:");
            email= scan.nextLine();


        }

    }
        String pass = scan.nextLine(); // looks at selected file in scan

    private static void displayOptions(){
        System.out.println("Choose one of the option below: ");
        System.out.println("1. List of my Loans \\n 2. Loan book \\n 3.Return book \\n 4.Exit");
    }

    private static void login() {
        Scanner scan = new Scanner(System.in);
        System.out.println("Enter username:");
        String email= scan.nextLine();
        System.out.println("Enter password:");
        String password= scan.nextLine();

        ValidationService validationService = new ValidationService();
        if(!validationService.checkLogin(password, email)) {
            System.out.println("User doesn't exists, Enter the correct email:");

        }else {
            isAuthenticated = true;
        }


    }



    }
