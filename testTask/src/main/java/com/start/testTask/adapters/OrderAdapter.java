package com.start.testTask.adapters;

import com.google.gson.*;
import com.start.testTask.TestFolder.TestClass;
import com.start.testTask.TestFolder.TmpTestAdapter;
import com.start.testTask.entity.Goods;
import com.start.testTask.entity.Order;
import com.start.testTask.entity.Person;

import java.lang.reflect.Type;

public class OrderAdapter implements JsonSerializer<Order> {

    @Override
    public JsonElement serialize(Order order, Type type, JsonSerializationContext jsonSerializationContext) {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("order_id", order.getId());
        jsonObject.addProperty("person_id", order.getPersonId().getId());
        jsonObject.addProperty("person_name", order.getPersonId().getName());

//        Gson gsonPerson = new GsonBuilder()
//                .registerTypeAdapter(Person.class, new PersonAdapter())
//                .create();
//        jsonObject.add("personId", gsonPerson.toJsonTree(order.getPersonId()));

        Gson gsonGoods = new GsonBuilder()
                .registerTypeAdapter(Goods.class, new GoodsAdapter())
                .create();
        jsonObject.add("goods_set", gsonGoods.toJsonTree(order.getGoods()));

        return jsonObject;
    }
}