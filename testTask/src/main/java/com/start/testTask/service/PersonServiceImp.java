package com.start.testTask.service;

import com.start.testTask.entity.Person;
import com.start.testTask.dao.PersonDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class PersonServiceImp implements PersonService {

    @Autowired
    private PersonDAO personDAO;

    @Autowired
    private JdbcTemplate jdbcTemplate;

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
//        Person person = personDAO.getOne(id);
        Person person = personDAO.findById(id).orElse(new Person());
        return person;
    }

    @Override
    public void deletePerson(Long id) {
        if (personDAO.existsById(id)) {
            personDAO.deleteById(id);
        }
    }

}

