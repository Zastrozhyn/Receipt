package by.zastr.service.util.json.impl;

import by.zastr.service.util.json.JsonParser;
import org.apache.commons.lang3.ClassUtils;

import java.beans.PropertyEditor;
import java.beans.PropertyEditorManager;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.ParameterizedType;
import java.util.*;

public class JsonParserImpl implements JsonParser {

    private final static char QUOTE = '"';
    private final static char LEFT_BRACE = '{';
    private final static char RIGHT_BRACE = '}';
    private final static char LEFT_BRACKET = '[';
    private final static char RIGHT_BRACKET = ']';
    private final static char COMMA = ',';
    private final static char COLON = ':';

    @Override
    public String writeObject(Object obj) throws IllegalAccessException{
        StringBuilder result = new StringBuilder();
        result.append(LEFT_BRACE);
        Class<?> superclass = obj.getClass().getSuperclass();
        for(Field field : superclass.getDeclaredFields()){
            result.append(writeField(field, obj));
        }
        if (superclass.getDeclaredFields().length > 0){
            result.append(COMMA);
        }
        Class<?> clazz = obj.getClass();
        for(Field field : clazz.getDeclaredFields()){
            result.append(writeField(field, obj));
        }
        result.append(RIGHT_BRACE);
        return result.toString();
    }

    @Override
    public <T> T readObject(String json, Class<T> clazz) throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException{
        T result = clazz.getConstructor().newInstance();
        Field[] fields = clazz.getDeclaredFields();
        for (Field field : fields){
            field.setAccessible(true);
            field.set(result, readObjectValue(json, field));
        }
        return result;
    }

    private Object readObjectValue(String json, Field field) throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException{
        String value = findValue(json, field.getName(), field.getType());

        if(ClassUtils.isPrimitiveOrWrapper(field.getType()) || field.getType().equals(String.class)){
            return convert(value, field.getType());
        }
        boolean isCollection = false;
        for (Class<?> clazz : field.getType().getInterfaces()){
            if(clazz.equals(Collection.class)){
                isCollection = true;
                break;
            }
        }
        if (isCollection){
            return readCollection(value, field);
        }
        return readObject(value, field.getType());
    }

    private Object readCollection(String value, Field field){
        ParameterizedType type = (ParameterizedType) field.getGenericType();
        Class<?> generic = (Class<?>) type.getActualTypeArguments()[0];
        String[] objects = value.split(",");
        Collection collection;
        if(field.getType().equals(List.class)){
            collection = new ArrayList();
        }else {
            collection = new HashSet();
        }
        for (String str : objects){
            if (generic.equals(String.class)){
                collection.add(convert(str.substring(1, str.length()-1), generic));
            }else {
                collection.add(convert(str, generic));
            }

        }
        return collection;
    }

    private Object convert(String text, Class<?> clazz) {
        PropertyEditor editor = PropertyEditorManager.findEditor(clazz);
        editor.setAsText(text);
        return editor.getValue();
    }

    private String findValue(String json, String fieldName, Class<?> clazz){
        StringBuilder result = new StringBuilder();
        int index = json.indexOf(fieldName);
        index += fieldName.length() + 2;
        if (json.charAt(index) == LEFT_BRACE){
            index++;
            while (json.charAt(index) != RIGHT_BRACE){
                result.append(json.charAt(index));
                index++;
            }
            return result.toString();
        }
        if (json.charAt(index) == LEFT_BRACKET){
            index++;
            while (json.charAt(index) != RIGHT_BRACKET){
                result.append(json.charAt(index));
                index++;
            }
            return result.toString();
        }
        if (json.charAt(index) == QUOTE){
            index++;
        }
        while (index < json.length() && json.charAt(index) != QUOTE){
            result.append(json.charAt(index));
            index++;
        }
        if (ClassUtils.isPrimitiveOrWrapper(clazz)  && result.charAt(result.length() - 1) == COMMA){
            result.deleteCharAt(result.length() - 1);
        }
        if (ClassUtils.isPrimitiveOrWrapper(clazz)  && result.charAt(result.length() - 1) == RIGHT_BRACE){
            result.deleteCharAt(result.length() - 1);
        }
        return result.toString();
    }

    private String writeField(Field field, Object obj) throws IllegalAccessException{
        StringBuilder result = new StringBuilder();
        field.setAccessible(true);
        if(field.getType().isPrimitive() || ClassUtils.isPrimitiveOrWrapper(field.getType())){
            result.append(writePrimitive(obj, field));
        }else {
            if (field.getType().equals(String.class)){
                result.append(writeString(obj, field));
            } else {
                boolean isCollectionOrMap = false;
                for (Class<?> clazz : field.getType().getInterfaces()){
                    if(clazz.equals(Collection.class)){
                        result.append(writeCollection(obj, field));
                        result.append(COMMA);
                        isCollectionOrMap = true;
                        break;
                    }
                }
                if(field.getType().equals(Map.class)){
                    result.append(writeMap(obj, field));
                    isCollectionOrMap = true;
                }
                if (!isCollectionOrMap){
                    result
                            .append(COMMA)
                            .append(QUOTE)
                            .append(field.getName())
                            .append(QUOTE)
                            .append(COLON)
                            .append(writeObject(field.get(obj)));
                }
            }
        }
        return result.toString();
    }

    private String writePrimitive(Object obj, Field field) throws IllegalAccessException{
        StringBuilder result = new StringBuilder();
        result.append(QUOTE)
                .append(field.getName())
                .append(QUOTE)
                .append(COLON)
                .append(field.get(obj));
        return result.toString();
    }

    private String writeString(Object obj, Field field) throws IllegalAccessException{
        StringBuilder result = new StringBuilder();
        result
                .append(QUOTE)
                .append(field.getName())
                .append(QUOTE)
                .append(COLON)
                .append(QUOTE)
                .append(field.get(obj))
                .append(QUOTE)
                .append(COMMA);
        return result.toString();
    }

    private String writeCollection(Object object, Field field) throws IllegalAccessException{
        StringBuilder result = new StringBuilder();
        result
                .append(QUOTE)
                .append(field.getName())
                .append(QUOTE)
                .append(COLON)
                .append(LEFT_BRACKET);
        for (Object obj : (Collection<?>)field.get(object)){
            if (ClassUtils.isPrimitiveOrWrapper(obj.getClass())){
                result
                        .append(obj)
                        .append(COMMA);
            }else{
                if (obj.getClass().equals(String.class)){
                    result
                            .append(QUOTE)
                            .append(obj)
                            .append(QUOTE)
                            .append(COMMA);
                } else {
                    result.append(writeObject(obj));
                }
            }
        }
        result.deleteCharAt(result.length()-1);
        result.append(RIGHT_BRACKET);
        return result.toString();
    }

    private String writeMap(Object obj, Field field) throws IllegalAccessException{
        StringBuilder result = new StringBuilder();
        result
                .append(QUOTE)
                .append(field.getName())
                .append(COMMA)
                .append(COLON)
                .append(LEFT_BRACE);
        Map<?,?> m = (Map<?,?>)field.get(obj);
        for (Map.Entry<?,?> entry: m.entrySet()){
            result.append(QUOTE)
                    .append(entry.getKey())
                    .append(QUOTE)
                    .append(COLON);
            if (!ClassUtils.isPrimitiveOrWrapper(entry.getValue().getClass())){
                result.append(QUOTE);
            }
            result.append(entry.getValue());
            if (!ClassUtils.isPrimitiveOrWrapper(entry.getValue().getClass())){
                result.append(QUOTE);
            }
            result.append(COMMA);
        }
        result.deleteCharAt(result.length()-1);
        result.append(RIGHT_BRACE);
        return result.toString();
    }
}
