package by.zastr.service.util.json.impl.entity;

import java.util.List;
import java.util.Objects;

public class ChildTestPojo extends ParentTestPojo{
    List<String> list;
    double secondValue;
    TestEntity entity;

    public ChildTestPojo(String name, Integer value, List<String> list, double secondValue, TestEntity entity){
        super(name, value);
        this.list = list;
        this.secondValue = secondValue;
        this.entity = entity;
    }

    public ChildTestPojo(List<String> list, double secondValue, TestEntity entity){
        this.list = list;
        this.secondValue = secondValue;
        this.entity = entity;
    }

    public ChildTestPojo(){
        list = List.of("Hello", "Java");
        secondValue = 2.6;
        entity = new TestEntity();
    }

    public List<String> getList(){
        return list;
    }

    public void setList(List<String> list){
        this.list = list;
    }

    public double getSecondValue(){
        return secondValue;
    }

    public void setSecondValue(double secondValue){
        this.secondValue = secondValue;
    }

    public TestEntity getEntity(){
        return entity;
    }

    public void setEntity(TestEntity entity){
        this.entity = entity;
    }

    @Override
    public boolean equals(Object o){
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        ChildTestPojo that = (ChildTestPojo) o;

        if (Double.compare(that.secondValue, secondValue) != 0) return false;
        if (!Objects.equals(list, that.list)) return false;
        return Objects.equals(entity, that.entity);
    }

    @Override
    public int hashCode(){
        int result = super.hashCode();
        long temp;
        result = 31 * result + (list != null ? list.hashCode() : 0);
        temp = Double.doubleToLongBits(secondValue);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + (entity != null ? entity.hashCode() : 0);
        return result;
    }

    @Override
    public String toString(){
        return "ChildTestPojo{" +
                "list=" + list +
                ", secondValue=" + secondValue +
                ", entity=" + entity +
                ", name='" + name + '\'' +
                ", value=" + value +
                '}';
    }
}
