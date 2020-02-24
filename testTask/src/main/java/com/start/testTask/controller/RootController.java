package com.start.testTask.controller;

import com.start.testTask.entity.Person;
//import io.swagger.models.Model;
//import com.start.testTask.service.PersonService;
import com.start.testTask.entity.Role;
import com.start.testTask.service.PersonServiceImp;
import com.start.testTask.service.RoleServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


import javax.websocket.server.PathParam;
import java.util.HashMap;
import java.util.List;
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

    @Autowired
    private PersonServiceImp personServiceImp;

    @Autowired
    private RoleServiceImpl roleServiceImp;

    @GetMapping
    public String root(Model model) {
        List<Person> allPerson = personServiceImp.getAllPerson();
        List<Role> allRole = roleServiceImp.getAllRoles();
        model.addAttribute("personList", allPerson);
        model.addAttribute("roleList", allRole);
//        model.addAttribute("personList", new ArrayList<>(persons.values()));
        return "home";
    }


    @PostMapping
    public Person addNewPerson(@RequestBody Person person) {
        persons.put(person.getId(), person);
        return persons.get(person.getId());
    }

    @GetMapping("/userAdd")
    public String userAdd(Model model) {
        List<Person> allPerson = personServiceImp.getAllPerson();
        model.addAttribute("personList", allPerson);
        return "userAdd";
    }

    @PostMapping("/userAdd")
    public String personSubmit(
//            @PathParam("id") Long id,
            @PathParam("name") String name,
            @PathParam("login") String login,
            @PathParam("password") String password,
            Model model) {
        Person person = new Person();
//        person.setId(id);
        person.setName(name);
        person.setLogin(login);
        person.setPassword(password);
        personServiceImp.savePerson(person);
        List<Person> allPerson = personServiceImp.getAllPerson();
        model.addAttribute("personList", allPerson);
//        persons.put(id,person);
//        model.addAttribute("personList", new ArrayList<>(persons.values()));
        return "userAdd";
    }

    @GetMapping("/roleAdd")
    public String roleAdd(Model model) {
        List<Role> allRoles = roleServiceImp.getAllRoles();
        model.addAttribute("roleList", allRoles);
        return "roleAdd";
    }

    @PostMapping("/roleAdd")
    public String roleSubmit(
            @PathParam("id") Long id,
            @PathParam("name") String name,
            Model model) {
        Role role = new Role();
        role.setId(id);
        role.setName(name);
        roleServiceImp.saveRole(role);
        List<Role> allRoles = roleServiceImp.getAllRoles();
        model.addAttribute("roleList", allRoles);
        return "roleAdd";
    }
    // redirect to the page
    @GetMapping("/userDel")
    public String userDel(Model model) {
        List<Role> allRoles = roleServiceImp.getAllRoles();
        model.addAttribute("roleList", allRoles);
        return "userDel";
    }

    // delete user
    @PostMapping("/userDel")
    public String userDel(@PathParam("id") Long id, Model model) {
        personServiceImp.deletePerson(id);
        List<Person> allPerson = personServiceImp.getAllPerson();
        model.addAttribute("personList", allPerson);
        return "userDel";
    }

}
