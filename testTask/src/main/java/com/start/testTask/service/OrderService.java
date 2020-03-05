package com.start.testTask.service;

import com.start.testTask.entity.Order;

import java.util.List;

public interface OrderService {

    public Order saveOrder(Order order);

    public List<Order> getAllOrders();

    public Order getOrdersById(Long id);

    public void deleteOrder(Long id);

    public List<Order> findByPersonId(Long id);

    public int countByPersonId(Long id);

    public String getOrderListByPersonId(Long id);
}
