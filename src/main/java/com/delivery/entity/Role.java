package com.delivery.entity;

public enum Role {
    CLIENT,MANAGER;

    public static Role getRole(User user) {
        int roleId = user.getRoleId()-1;
        return Role.values()[roleId];
    }

    public String getName() {
        return name().toLowerCase();
    }

}
