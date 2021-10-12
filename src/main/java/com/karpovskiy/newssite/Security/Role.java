package com.karpovskiy.newssite.Security;

import com.google.common.collect.Sets;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.HashSet;
import java.util.Set;

import static com.karpovskiy.newssite.Security.Permission.*;

public enum Role {
    USER(Sets.newHashSet(USER_WRITE)),
    ADMIN(Sets.newHashSet(USER_WRITE, ADMIN_WRITE));

    private final Set<Permission> permissions;

    Role(Set<Permission> permissions) {
        this.permissions = permissions;
    }

    public Set<Permission> getPermissions() {
        return permissions;
    }

    public Set<SimpleGrantedAuthority> getGrantedAuthority(){
        Set<SimpleGrantedAuthority> permissions = new HashSet<>();
        for (Permission permission : getPermissions()){
            permissions.add(new SimpleGrantedAuthority(permission.getPermission()));
        }
        permissions.add(new SimpleGrantedAuthority("ROLE_" + this.name()));

        return permissions;
    }
}
