package com.start.testTask.dao;

import com.start.testTask.entity.Goods;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GoodsDAO extends JpaRepository<Goods, Long> {
}
