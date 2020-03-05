package com.start.testTask.controller;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.start.testTask.adapters.OrderAdapter;
import com.start.testTask.adapters.PersonAdapter;
import com.start.testTask.entity.Goods;
import com.start.testTask.entity.Order;
import com.start.testTask.entity.Person;
//import io.swagger.models.Model;
//import com.start.testTask.service.PersonService;
import com.start.testTask.entity.Role;
import com.start.testTask.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


import javax.websocket.server.PathParam;
import java.util.*;

@Controller
@RequestMapping("/")
public class RootController {

    @Autowired
    private PersonServiceImp personServiceImp;

    @Autowired
    private RoleServiceImpl roleServiceImp;

    @Autowired
    private GoodsServiceImp goodsServiceImp;

    @Autowired
    private OrderService orderService;

    @GetMapping
    public String root(Model model) {
        List<Person> allPerson = personServiceImp.getAllPerson();
        List<Role> allRole = roleServiceImp.getAllRoles();
        List<Goods> allGoods = goodsServiceImp.getAllGoods();
        model.addAttribute("personList", allPerson);
        model.addAttribute("roleList", allRole);
        model.addAttribute("goodsList", allGoods);
        return "home";
    }

    // ----------------------------- USER -----------------------------

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

    @PostMapping("/userRoleAdd")
    public String userRoleAdd(
            @PathParam("person_id") Long person_id,
            @PathParam("role_id") Long role_id,
            Model model) {
        Person person = personServiceImp.getPersonById(person_id);
        Role role = roleServiceImp.getRoleById(role_id);
        person.getRoles().add(role);
        personServiceImp.savePerson(person);
        List<Person> allPerson = personServiceImp.getAllPerson();
        model.addAttribute("personList", allPerson);
        List<Role> allRole = roleServiceImp.getAllRoles();
        model.addAttribute("roleList", allRole);
        return "home";
    }

    @PostMapping("/userGoodsAdd")
    public String userGoodsAdd(
            @PathParam("person_id") Long person_id,
            @PathParam("goods_id") Long[] goods_id,
            Model model) {
        Person person = personServiceImp.getPersonById(person_id);
        Order order = new Order();
        Set<Goods> goodsSet = new HashSet<>();
        if(goods_id != null) {
            for (int i = 0; i < goods_id.length; i++) {
                Goods goods = goodsServiceImp.getGoodsById(goods_id[i]);
                goodsSet.add(goods);
            }
        }
        order.setGoods(goodsSet);
        order.setPersonId(person);
        orderService.saveOrder(order);
        List<Person> allPerson = personServiceImp.getAllPerson();
        model.addAttribute("personList", allPerson);
        List<Role> allRole = roleServiceImp.getAllRoles();
        model.addAttribute("roleList", allRole);
        List<Goods> allGoods = goodsServiceImp.getAllGoods();
        model.addAttribute("goodsList", allGoods);
        return "home";
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

    @GetMapping("/userDetails/{id}")
    public String getPersonById(@PathVariable("id") Long id, Model model) {
        Person person = personServiceImp.getPersonById(id);
        Order order = new Order();
//        int tmp = orderService.countByPersonId(id); only for test
        List<Order> orderList = orderService.findByPersonId(id);
        model.addAttribute("person", person);
        return "userDetails";
    }

    @GetMapping("/userDetailsGson/{id}")
    @ResponseBody
    public String getPersonByIdGson(@PathVariable("id") Long id) {
        Person person = personServiceImp.getPersonById(id);
//        Order order = new Order();
        List<Order> orderList = orderService.findByPersonId(id);
        Gson gson = new GsonBuilder().registerTypeAdapter(Order.class, new OrderAdapter()).create();
        String json = gson.toJson(orderList);
        return json;
    }

    @GetMapping("/userEditForm/{id}")
    public String userEdit(@PathVariable("id") Long id, Model model) {
        Person person = personServiceImp.getPersonById(id);
        model.addAttribute("person", person);
        return "userEditForm";
    }

    @PostMapping("/userLoginPasswordEdit")
    public String userEdit(@PathParam("id") Long id,
                           @PathParam("login") String login,
                           @PathParam("password") String password,
                           Model model) {
        Person person = personServiceImp.getPersonById(id);
        person.setLogin(login);
        person.setPassword(password);
        personServiceImp.savePerson(person);
        Set<Role> personRoles = person.getRoles();
        model.addAttribute("person", person);
        model.addAttribute("roles", personRoles);

        return "userDetails";
    }

    @PostMapping("/addSerealizableUser")
    @ResponseBody
    public String addNewPerson(@RequestBody String personGson) {
        Gson gson = new GsonBuilder().registerTypeAdapter(Person.class, new PersonAdapter()).create();
        Person person = gson.fromJson(personGson, Person.class);
        Person newPerson = personServiceImp.savePerson(person);
        String json = gson.toJson(personServiceImp.getPersonById(newPerson.getId()));
        return json;
    }

    @GetMapping("/userRoleDelete/{person_id}/{role_id}")
    public String getRoleById(@PathVariable("person_id") Long person_id,@PathVariable("role_id") Long role_id, Model model) {
        Person person = personServiceImp.getPersonById(person_id);
        Role role = roleServiceImp.getRoleById(role_id);
        person.getRoles().remove(role);
        personServiceImp.savePerson(person);
//        personServiceImp.getPersonById(person_id).getRoles().remove(roleServiceImp.getRoleById(role_id));
        model.addAttribute("person", person);
        return "userDetails";
    }


    @GetMapping("/personq/{id}")
    @ResponseBody
    public String getPersonById(@PathVariable("id") Long id) {
        Person person = personServiceImp.getPersonById(id);
        Person newPerson = new Person();
        newPerson.setId(person.getId());
        newPerson.setName(person.getName());
        newPerson.setPassword(person.getPassword());
        newPerson.setLogin(person.getLogin());
        newPerson.setRoles(person.getRoles());
        Gson gson = new GsonBuilder().registerTypeAdapter(Person.class, new PersonAdapter()).create();
        String json = gson.toJson(newPerson);
        return json;
    }

    @GetMapping("/person-order/{id}")
    @ResponseBody
    public String getOrderListByPersonId(@PathVariable("id") Long id) {
        String personOrderList = orderService.getOrderListByPersonId(id);
        return personOrderList;
    }


    // ----------------------------- ROLE ---------------------------------!!!!!!!!!!!!!!!!!!!!

    @GetMapping("/roleAdd")
    public String roleAdd(Model model) {
        List<Role> allRoles = roleServiceImp.getAllRoles();
        model.addAttribute("roleList", allRoles);
        System.out.println("Ruslan was changed");
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


    // ----------------------------- GOODS ---------------------------------

    @GetMapping("/goodsAdd")
    public String goodsAdd(Model model) {
        List<Goods> allGoods = goodsServiceImp.getAllGoods();
        model.addAttribute("goodsList", allGoods);
        return "goodsAdd";
    }

    @PostMapping("/goodsAdd")
    public String goodsSubmit(
            @PathParam("id") Long id,
            @PathParam("name") String name,
            Model model) {
        Goods goods = new Goods();
        goods.setId(id);
        goods.setName(name);
        goodsServiceImp.saveGoods(goods);
        List<Goods> allGoods = goodsServiceImp.getAllGoods();
        model.addAttribute("goodsList", allGoods);
        return "goodsAdd";
    }


    //    When progect whas created query returness data to the postman
//    @PostMapping
//    public Person addNewPerson(@RequestBody Person person) {
//        persons.put(person.getId(), person);
//        return persons.get(person.getId());
//    }

}
