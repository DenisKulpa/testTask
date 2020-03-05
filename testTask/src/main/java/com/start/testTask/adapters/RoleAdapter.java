package com.start.testTask.adapters;

import com.google.gson.*;
import com.start.testTask.entity.Person;
import com.start.testTask.entity.Role;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class RoleAdapter implements JsonSerializer<Role> {

@Override
public JsonElement serialize(Role role, Type type, JsonSerializationContext jsonSerializationContext) {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("role_id", role.getId());
        jsonObject.addProperty("role_name", role.getName());

        return jsonObject;
        }
        }