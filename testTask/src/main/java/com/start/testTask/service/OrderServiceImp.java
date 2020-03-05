package com.start.testTask.service;

import com.google.gson.Gson;
import com.start.testTask.dao.OrderDAO;
import com.start.testTask.entity.Order;
import com.start.testTask.entity.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class OrderServiceImp implements OrderService {

    @Autowired
    private OrderDAO orderDAO;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private PersonService personService;

    @Override
    public Order saveOrder(Order order) {
        return orderDAO.saveAndFlush(order);
    }

    @Override
    public List<Order> getAllOrders() {
        return orderDAO.findAll();
    }

    @Override
    public Order getOrdersById(Long id) {
        Order order = orderDAO.findById(id).orElse(new Order());
        return order;
    }

    @Override
    public void deleteOrder(Long id) {
        if (orderDAO.existsById(id)) {
            orderDAO.deleteById(id);
        }
    }

    @Override
    public List<Order> findByPersonId(Long id) {
        Person person = personService.getPersonById(id);
//        jdbcTemplate.
        return orderDAO.findByPersonId(person);
    }

    @Override
    public int countByPersonId(Long id) {
        Person person = personService.getPersonById(id);
        return orderDAO.countByPersonId(person);
    }

    @Override
    public String getOrderListByPersonId(Long id) {
        String sql =
                "SELECT ord.order_id, per.name\n" +
                        " FROM orders as ord\n" +
                        " LEFT JOIN persons as per\n" +
                        " ON per.person_id = ord.person_id\n" +
                        " WHERE ord.person_id = " + id ;

        List<Map<String,Object>> array = jdbcTemplate.queryForList(sql);
        for (Map<String, Object> stringObjectMap : array) {
            String sql2 =
                    "SELECT gds.*, ordList.*\n" +
                    " FROM order_list as ordList\n" +
                    " LEFT JOIN goods as gds\n" +
                    " ON ordList.goods_id = gds.goods_id\n" +
                    " WHERE ordList.order_id = " + id;
            List<Map<String,Object>> arrayGoods = jdbcTemplate.queryForList(sql2);
            stringObjectMap.put("list_goods", arrayGoods);
        }
        Gson gson = new Gson();
        String json = gson.toJson(array);   // Auto casting from List<Map<String,Object>> to Json
        return json;
    }
}


