package com.liupeng.dubbo.api;

import org.hibernate.validator.constraints.Email;

import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Date;

public class MyParameter implements Serializable{
    private static final long serialVersionUID = 7158911668568000392L;

    @NotNull // Required
    @Size(min = 1, max = 20) // range
    private String name;

    //@Pattern(regexp = "^\\s*\\w+(?:\\.{0,1}[\\w-]+)*@[a-zA-Z0-9]+(?:[-.][a-zA-Z0-9]+)*\\.[a-zA-Z]+\\s*$")
    @NotNull//(groups = DemoService.TestValidation.class) // It is not allowed to be blank when saving. When it is updated, it is allowed to be blank, indicating that the field is not updated
    @Email
    private String email;

    @Min(18) // min value
    @Max(100) // max value
    private int age;

    @Past // Must be a past time
    private Date loginDate;

    @Future // Must be a future time
    private Date expiryDate;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Date getLoginDate() {
        return loginDate;
    }

    public void setLoginDate(Date loginDate) {
        this.loginDate = loginDate;
    }

    public Date getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(Date expiryDate) {
        this.expiryDate = expiryDate;
    }
}
