package com.start.testTask.dao;

import com.start.testTask.entity.Order;
import com.start.testTask.entity.Person;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderDAO extends JpaRepository<Order, Long> {

    List<Order> findByPersonId(Person id);

    int countByPersonId(Person id);

}


