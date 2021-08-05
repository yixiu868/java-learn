package com.ww.reflect;

public class Customer {

    private Long id;
    
    private String name;
    
    private Integer age;
    
    public Customer() {
    }

    public Customer(String name, Integer age) {
        super();
        this.name = name;
        this.age = age;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "Customer [id=" + id + ", name=" + name + ", age=" + age + "]";
    }
}
