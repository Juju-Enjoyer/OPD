package com.example.opd.models.enums;

import com.example.opd.repositories.GradeRepository;
import org.springframework.security.core.GrantedAuthority;

public enum Role implements GrantedAuthority {
    ROLE_USER, ROLE_ADMIN,ROLE_EXPERT;

    @Override
    public String getAuthority() {
        return name();
    }
}
