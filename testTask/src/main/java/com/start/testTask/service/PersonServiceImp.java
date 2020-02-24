package com.start.testTask.service;

import com.start.testTask.entity.Person;
import com.start.testTask.dao.PersonDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PersonServiceImp implements PersonService {

    @Autowired
    private PersonDAO personDAO;

    @Override
    public Person savePerson(Person person) {
        return personDAO.saveAndFlush(person);
    }

    @Override
    public List<Person> getAllPerson() {
        return personDAO.findAll();
    }

    @Override
    public Person getPersonById(Long id) {
        return personDAO.getOne(id);
    }

    @Override
    public void deletePerson(Long id) {
        if (personDAO.existsById(id)) {
            personDAO.deleteById(id);
        }
    }

}

