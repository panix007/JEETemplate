package com.ntahr.common.util;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

public class JSONUtil<T> {

    private static final Gson gson = new Gson();
    private Type typeList = new TypeToken<List<T>>() {
    }.getType();
    private Type type = new TypeToken<T>() {
    }.getType();

    public String toJson(T instance) {
        return gson.toJson(instance, type);
    }

    public T fromJson(String json) {
        return gson.fromJson(json, type);
    }

    public String toJson(List<T> list) {
        return gson.toJson(list, typeList);
    }

    public List<T> listFromJson(String json) {
        return gson.fromJson(json, typeList);
    }

}
