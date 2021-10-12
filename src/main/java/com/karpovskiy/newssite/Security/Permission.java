package com.karpovskiy.newssite.Security;

public enum Permission {
    USER_WRITE("user:write"),
    ADMIN_WRITE("admin:write");

    private final String permission;

    Permission(String permission){
        this.permission = permission;
    }

    public String getPermission() {
        return permission;
    }
}
