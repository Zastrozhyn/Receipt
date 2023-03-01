package by.zastr.service.util.json;

import java.lang.reflect.InvocationTargetException;

public interface JsonParser {

    public String writeObject(Object obj) throws IllegalAccessException;
    public <T> T readObject(String json, Class<T> clazz) throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException;
}
