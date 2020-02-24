package com.start.testTask.service;

import com.start.testTask.entity.Person;

import java.util.List;

public interface PersonService {

    public Person savePerson(Person person);

    public List<Person> getAllPerson();

    public Person getPersonById(Long id);

    public void deletePerson(Long id);
}
