package com.example.demo.entity;

public enum Role {
    ADMIN,
    COMPANY,
    USER;
    public Role convertRoleStringToEnum(String roleString) {
        switch(roleString.toLowerCase()) {
            case "user":
                return Role.USER;
            case "company":
                return Role.COMPANY;
            case "admin":
                return Role.ADMIN;
            default:
                throw new IllegalArgumentException("Invalid role: " + roleString);
        }
    }

}
