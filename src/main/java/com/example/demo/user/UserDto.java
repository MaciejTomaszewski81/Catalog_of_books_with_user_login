package com.example.demo.user;

public class UserDto {

    private Long id;
    private String email;
    private boolean administratorAcces;

    public UserDto() {
    }

    public UserDto(Long id, String email, boolean administratorAcces) {
        this.id = id;
        this.email = email;
        this.administratorAcces = administratorAcces;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean isAdministratorAccess() {
        return administratorAcces;
    }

    public void setAdministratorAccess(boolean administratorAcces) {
        this.administratorAcces = administratorAcces;
    }
}