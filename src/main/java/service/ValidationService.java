package service;

import dao.PersonDao;
import models.Person;

public class ValidationService {

    public boolean checkLogin(String email, String password){

        boolean result = true;

        if(!email.contains("@")){
            return false;
        }

        PersonDao personDao = new PersonDao();
        Person person = personDao.getPersonByEmail(email);
        if(person == null){
            return false;
        }

        return result;
    }
}
