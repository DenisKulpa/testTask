package com.start.testTask.controller;

import com.start.testTask.entity.Person;
//import io.swagger.models.Model;
import org.springframework.ui.Model;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


import javax.websocket.server.PathParam;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/")
public class RootController {
    Map<Long, Person> persons = new HashMap<Long, Person>() {{
        put(1L, new Person(1L, "Mike1", "mike", "123"));
        put(2L, new Person(2L, "Bob", "bob", "234"));
        put(3L, new Person(3L, "Smith", "smith", "345"));
        put(4L, new Person(4L, "Rob", "rob", "567"));
        put(5L, new Person(5L, "Jane", "jane", "789"));
    }};

    @GetMapping
    public String root(Model model) {
        model.addAttribute("personList", new ArrayList<>(persons.values()));
        return "home";
    }

    @PostMapping
    public Person addNewPerson(@RequestBody Person person) {
        persons.put(person.getId(), person);
        return persons.get(person.getId());
    }

    @GetMapping("/userAdd")
    public String userAdd(Model model) {
        model.addAttribute("personList", new ArrayList<>(persons.values()));
        model.addAttribute("person", new Person());
        return "userAdd";
    }

    @PostMapping("/userAdd")
    public String personSubmit(
            @PathParam("id") Long id,
            @PathParam("name") String name,
            @PathParam("login") String login,
            @PathParam("password") String password,
            Model model) {
        Person person = new Person();
        person.setId(id);
        person.setName(name);
        person.setLogin(login);
        person.setPassword(password);
        persons.put(id,person);
        model.addAttribute("personList", new ArrayList<>(persons.values()));
        return "userAdd";
    }


}
