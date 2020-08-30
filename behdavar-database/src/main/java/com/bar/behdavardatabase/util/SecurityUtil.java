package com.bar.behdavardatabase.util;

import com.bar.behdavardatabase.entity.security.UserEntity;
import com.bar.behdavardatabase.repository.security.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Optional;

@Component
public class SecurityUtil {
    private static UserRepository userRepositoryStatic;
    @Autowired
    private UserRepository userRepository;

    public static Long getCurrentUserId() {
        User user = (User) (SecurityContextHolder.getContext().getAuthentication().getPrincipal());
        Optional<UserEntity> userEntity = userRepositoryStatic.findByUsername(user.getUsername());
        return userEntity.isPresent() ? userEntity.get().getId() : null;
    }

    public static String getCurrentUser() {
        return ((User) (SecurityContextHolder.getContext().getAuthentication().getPrincipal())).getUsername();
    }

    @PostConstruct
    private void setUserBusiness() {
        this.userRepositoryStatic = userRepository;
    }
}


