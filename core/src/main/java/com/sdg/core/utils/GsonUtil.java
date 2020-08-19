package com.sdg.core.utils;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

/**
 * gson转换工具类
 */
public class GsonUtil {

    //将bean转换成Json字符串
    public static String beanToJSONString(Object bean) {
        return new Gson().toJson(bean);
    }

    //将Json字符串转换成对象
    public static Object JSONToObject(String json, Class beanClass) {
        Gson gson = new Gson();
        Object res = gson.fromJson(json, beanClass);
        return res;
    }

    public static <T> List<T> parseString2List(String json) {
        Type type = new TypeToken<List<T>>() {
        }.getType();
        List<T> list = new Gson().fromJson(json, type);
        return list;
    }

    public static <T> List<T> parseString2List(String json, Class clazz) {
        Type type = new ParameterizedTypeImpl(clazz);
        List<T> list = new Gson().fromJson(json, type);
        return list;
    }

    private static class ParameterizedTypeImpl implements ParameterizedType {
        Class clazz;

        public ParameterizedTypeImpl(Class clz) {
            clazz = clz;
        }

        @Override
        public Type[] getActualTypeArguments() {
            return new Type[]{clazz};
        }

        @Override
        public Type getRawType() {
            return List.class;
        }

        @Override
        public Type getOwnerType() {
            return null;
        }
    }


}
