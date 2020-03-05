package com.start.testTask.adapters;

import com.google.gson.*;
import com.start.testTask.entity.Person;
import com.start.testTask.entity.Role;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class PersonAdapter implements JsonSerializer<Person> {

    @Override
    public JsonElement serialize(Person person, Type type, JsonSerializationContext jsonSerializationContext) {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("person_id", person.getId());
        jsonObject.addProperty("person_name", person.getName());
        jsonObject.addProperty("person_login", person.getLogin());
        jsonObject.addProperty("person_password", person.getPassword());


        Gson gson = new GsonBuilder()
                .registerTypeAdapter(Role.class, new RoleAdapter())
                .create();
        jsonObject.add("role_set", gson.toJsonTree(person.getRoles()));

        return jsonObject;
    }
}