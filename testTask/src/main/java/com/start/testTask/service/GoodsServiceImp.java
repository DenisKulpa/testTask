package com.start.testTask.service;

import com.start.testTask.dao.GoodsDAO;
import com.start.testTask.entity.Goods;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GoodsServiceImp implements GoodsService {

    @Autowired
    private GoodsDAO goodsDAO;

    @Override
    public Goods saveGoods(Goods goods) {
        return goodsDAO.saveAndFlush(goods);
    }

    @Override
    public List<Goods> getAllGoods() {
        return goodsDAO.findAll();
    }

    @Override
    public Goods getGoodsById(Long id) {
        Goods goods = goodsDAO.findById(id).orElse(new Goods());
        return goods;
    }

    @Override
    public void deleteGoods(Long id) {
        if (goodsDAO.existsById(id)) {
            goodsDAO.deleteById(id);
        }
    }
}
