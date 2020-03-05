package com.start.testTask.service;

import com.start.testTask.entity.Goods;

import java.util.List;

public interface GoodsService {

    public Goods saveGoods(Goods goods);

    public List<Goods> getAllGoods();

    public Goods getGoodsById(Long id);

    public void deleteGoods(Long id);
}
