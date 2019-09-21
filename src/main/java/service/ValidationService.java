package service;

import dao.PersonDao;
import models.Person;

public class ValidationService {

    public Person checkLogin(String email, String password){

        if(!email.contains("@")){
            return null;
        }

        PersonDao personDao = new PersonDao();
        Person person = personDao.getPersonByEmailAndPassword(email,password);
        if(person == null){
            return person;
        }
        return null;

    }
}
