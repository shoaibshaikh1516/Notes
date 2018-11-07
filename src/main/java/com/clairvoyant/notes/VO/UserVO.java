package com.clairvoyant.notes.VO;

import com.clairvoyant.notes.service.impl.ValidPassword;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class UserVO {

    @NotNull
    @Size(min = 1, message = "Name should have atleast 1 characters ")
    private String name;
    @NotNull
    @Size(min = 1, message = "Last name should have atleast 1 characters ")
    private String lastName;


    @NotNull
    @NotEmpty
    @Email
    private String email;
    @NotNull
    @ValidPassword
    private String password;
    @NotNull
    @ValidPassword
    private String passwordConfirmation;
    @NotNull
    private Integer role;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPasswordConfirmation() {
        return passwordConfirmation;
    }

    public void setPasswordConfirmation(String passwordConfirmation) {
        this.passwordConfirmation = passwordConfirmation;
    }

    public Integer getRole() {
        return role;
    }

    public void setRole(Integer role) {
        this.role = role;
    }

    public UserVO(String name, String lastName, String email, String password, String passwordConfirmation, Integer role) {
        this.name = name;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.passwordConfirmation = passwordConfirmation;
        this.role = role;
    }

    public UserVO() {

    }
}
