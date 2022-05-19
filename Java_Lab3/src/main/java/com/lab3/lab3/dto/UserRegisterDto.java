package com.lab3.lab3.dto;
import com.lab3.lab3.model.Users;
import org.springframework.lang.Nullable;

public class UserRegisterDto {
    private String firstName;
    private String lastName;
    private String email;
    private String password;

    private Users.UserRole role;

    public UserRegisterDto() {

    }

    public UserRegisterDto(String firstName, String lastName, String email, String password,
                           @Nullable Users.UserRole role) {
        super();
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.role = role;
    }

    public String getFirstName() {
        return firstName;
    }
    public void setFirstName(String firstName) {
        this.firstName = firstName;
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

    public void setRole(Users.UserRole role) {
        this.role = role;
    }

    public Users.UserRole getRole() {
        return this.role;
    }
}