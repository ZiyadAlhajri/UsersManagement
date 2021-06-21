package com.example.data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "User_Account")
public class User {
    @Id
    private String id;
    private String name;
    private String salary;

    public User(){

    }
    public User(String id , String name ,String Sal){
        super();
        this.id=id;
        this.name=name;
        this.salary=Sal;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSalary(String salary) {
        this.salary =salary;
    }
    public String getSalary() {
        return salary;

    }
    public void setId(String id) {
        this.id = id;

    }

    public String getId() {
        return id;

    }

}
