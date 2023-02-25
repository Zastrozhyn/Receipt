package by.zastr.service.util.json.impl;

import by.zastr.service.util.json.JsonParser;
import by.zastr.service.util.json.impl.entity.ChildTestPojo;
import by.zastr.service.util.json.impl.entity.ParentTestPojo;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.lang.reflect.InvocationTargetException;

import static org.assertj.core.api.Assertions.assertThat;

class JsonParserImplTest {

    private static JsonParser parser;
    private static ObjectMapper mapper;
    private static ChildTestPojo childTestPojo;
    private static ParentTestPojo parentTestPojo;

    @BeforeAll
    static void init(){
        parser = new JsonParserImpl();
        mapper = new ObjectMapper();
        parentTestPojo = new ParentTestPojo();
        childTestPojo = new ChildTestPojo();
    }

    @Test
    void checkWriteObjectChild() throws JsonProcessingException, IllegalAccessException{
        assertThat(parser.writeObject(childTestPojo)).isEqualTo(mapper.writeValueAsString(childTestPojo));
    }

    @Test
    void checkWriteObjectParent() throws JsonProcessingException, IllegalAccessException{
        assertThat(parser.writeObject(parentTestPojo)).isEqualTo(mapper.writeValueAsString(parentTestPojo));
    }

    @Test
    void checkReadObjectChild() throws JsonProcessingException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException{
        String jason = mapper.writeValueAsString(childTestPojo);
        assertThat(parser.readObject(jason, ChildTestPojo.class)).isEqualTo(childTestPojo);
    }

    @Test
    void checkReadObjectParent() throws JsonProcessingException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException{
        String jason = mapper.writeValueAsString(parentTestPojo);
        assertThat(parser.readObject(jason, ParentTestPojo.class)).isEqualTo(parentTestPojo);
    }
}