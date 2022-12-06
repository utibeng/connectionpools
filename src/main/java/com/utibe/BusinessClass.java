package com.utibe;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.utibe.Email;

public class BusinessClass {

    private String name;
    private int age;

    private Email email;

    private static final Logger logger = LogManager.getLogger();

    public BusinessClass() {
    }

    public BusinessClass(String name, int age) {
        this.name = name;
        this.age = age;
    }

    @Override
    public String toString() {
        return "BusinessClass{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
        logger.info("Business class name has been set to {}", name);
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void dummy() {
        logger.info("dummy confuser");
    }

    public void setEmail(Email email){
        this.email = email;

    }

    public void logToString(){

        this.dummy();

        logger.info("Object is {}", this.toString());
    }

}
