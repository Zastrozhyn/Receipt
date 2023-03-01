package by.zastr.service.util.json.impl.entity;

import java.util.Objects;

public class TestEntity {
    String testName;
    int cost;

    public TestEntity(String testName, int cost){
        this.testName = testName;
        this.cost = cost;
    }

    public TestEntity(){
        testName = "Test";
        cost = 25;
    }

    public String getTestName(){
        return testName;
    }

    public void setTestName(String testName){
        this.testName = testName;
    }

    public int getCost(){
        return cost;
    }

    public void setCost(int cost){
        this.cost = cost;
    }

    @Override
    public boolean equals(Object o){
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TestEntity that = (TestEntity) o;

        if (cost != that.cost) return false;
        return Objects.equals(testName, that.testName);
    }

    @Override
    public int hashCode(){
        int result = testName != null ? testName.hashCode() : 0;
        result = 31 * result + cost;
        return result;
    }

    @Override
    public String toString(){
        return "TestEntity{" +
                "testName='" + testName + '\'' +
                ", cost=" + cost +
                '}';
    }
}
