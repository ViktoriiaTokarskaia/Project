package service;

import dao.PersonDao;
import models.Person;

public class ValidationService {

    public Person getLogin(String email, String password){
        //TODO: Email validation and Exception handling
        if(!email.contains("@")){
            return null;
        }

        PersonDao personDao = new PersonDao();
        Person person = personDao.getPersonByEmailAndPassword(email,password);
        if(person == null){
            return null;
        }

        return person;
    }
}
