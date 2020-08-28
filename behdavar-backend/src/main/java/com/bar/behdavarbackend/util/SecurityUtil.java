package com.bar.behdavarbackend.util;

import com.bar.behdavarbackend.business.api.UserBusiness;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class SecurityUtil {
    private static UserBusiness userBusinessStatic;
    @Autowired
    private UserBusiness userBusiness;

    public static Long getCurrentUserId() {
        User user = (User) (SecurityContextHolder.getContext().getAuthentication().getPrincipal());
        return userBusinessStatic.findByUserName(user.getUsername()).getId();
    }

    @PostConstruct
    private void setUserBusiness() {
        this.userBusinessStatic = userBusiness;
    }
}


