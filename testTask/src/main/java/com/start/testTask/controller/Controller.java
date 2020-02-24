package com.start.testTask.controller;
// !!!!!! remember /api
import com.start.testTask.entity.Person;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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


    @GetMapping("/")
    public List<Person> getAllPersons() {
        return new ArrayList<>(persons.values());
    }

    @GetMapping("/{id}")
    public Person getPersonById(@PathVariable("id") Long id) {
        return persons.get(id);
    }

    @PostMapping("/")
    public Person addNewPerson(@RequestBody Person person) {
        persons.put(person.getId(), person);
        return persons.get(person.getId());
    }


}
