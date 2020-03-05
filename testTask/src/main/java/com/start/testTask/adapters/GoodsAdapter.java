package com.start.testTask.adapters;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import com.start.testTask.entity.Goods;

import java.lang.reflect.Type;

public class GoodsAdapter implements JsonSerializer<Goods> {

    @Override
    public JsonElement serialize(Goods goods, Type type, JsonSerializationContext jsonSerializationContext) {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("goods_id", goods.getId());
        jsonObject.addProperty("goods_name", goods.getName());

        return jsonObject;
    }
}