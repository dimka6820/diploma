package com.dmma.diploma.model.security;

import org.springframework.util.CollectionUtils;

import java.util.Set;
import java.util.TreeSet;

public enum Role {
    ROLE_USER,
    ROLE_ADMIN;

    public static Set<Role> getAllRoles() {
        return new TreeSet<Role>(CollectionUtils.arrayToList(Role.values()));
    }
}
