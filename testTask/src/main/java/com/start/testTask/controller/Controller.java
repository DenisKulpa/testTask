package com.start.testTask.controller;
// !!!!!! remember /api
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.start.testTask.adapters.PersonAdapter;
import com.start.testTask.entity.Person;
import com.start.testTask.entity.Role;
import com.start.testTask.service.PersonService;
import com.start.testTask.service.PersonServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/api")
public class Controller {
    Map<Long, Person> persons = new HashMap<Long, Person>() {{
        put(1L, new Person(1L, "Mike", "mike", "123"));
        put(2L, new Person(2L, "Bob", "bob", "234"));
        put(3L, new Person(3L, "Smith", "smith", "345"));
        put(4L, new Person(4L, "Rob", "rob", "567"));
        put(5L, new Person(5L, "Jane", "jane", "789"));
    }};

    @Autowired
    private PersonService personService;

    @GetMapping("/")
    public List<Person> getAllPersons() {
        return new ArrayList<>(persons.values());
    }

    @GetMapping("/{id}")
    public Person getPersonById(@PathVariable("id") Long id) {
        Person person = personService.getPersonById(id);
        return person;
    }

    @PostMapping("/")
    public Person addNewPerson(@RequestBody Person person) {
        persons.put(person.getId(), person);
        return persons.get(person.getId());
    }


}
