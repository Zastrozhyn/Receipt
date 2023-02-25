package by.zastr.service.util.json.impl.entity;


import java.util.Objects;

public class ParentTestPojo {
    String name;
    Integer value;

    public ParentTestPojo(String name, Integer value){
        this.name = name;
        this.value = value;
    }

    public ParentTestPojo(){
        name = "Test name";
        value = 10;
    }

    public String getName(){
        return name;
    }

    public void setName(String name){
        this.name = name;
    }

    public Integer getValue(){
        return value;
    }

    public void setValue(Integer value){
        this.value = value;
    }

    @Override
    public boolean equals(Object o){
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ParentTestPojo that = (ParentTestPojo) o;

        if (!Objects.equals(name, that.name)) return false;
        return Objects.equals(value, that.value);
    }

    @Override
    public int hashCode(){
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + (value != null ? value.hashCode() : 0);
        return result;
    }

    @Override
    public String toString(){
        return "ParentTestPojo{" +
                "name='" + name + '\'' +
                ", value=" + value +
                '}';
    }
}
