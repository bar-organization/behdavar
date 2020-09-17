package com.bar.behdavarbackend.config;

import com.bar.behdavarcommon.AuthorityConstant;
import com.bar.behdavardatabase.entity.PersonEntity;
import com.bar.behdavardatabase.entity.security.PrivilegeEntity;
import com.bar.behdavardatabase.entity.security.RoleEntity;
import com.bar.behdavardatabase.entity.security.UserEntity;
import com.bar.behdavardatabase.repository.PersonRepository;
import com.bar.behdavardatabase.repository.security.PrivilegeRepository;
import com.bar.behdavardatabase.repository.security.RoleRepository;
import com.bar.behdavardatabase.repository.security.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

@Component
public class StartupPreparation {
    public static final String SUPERVISOR_ROLE = "SUPERVISOR_ROLE";
    public static final String SUPERVISOR_USER = "SUPERVISOR_USER";
    public static final String SUPERVISOR_PWD = "SUPER@BAR";
    @Autowired
    PrivilegeRepository privilegeRepository;
    @Autowired
    RoleRepository roleRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    PersonRepository personRepository;
    @Value("${update.user.role.privileges.at.startup}")
    private boolean updateAtStartup;

    @PostConstruct
    private void init() {
        if (updateAtStartup) {
            List<Field> staticFields = getFields();

            staticFields.forEach(field -> {
                PrivilegeEntity entity = new PrivilegeEntity();
                try {
                    entity.setName(field.get(null).toString());
                    privilegeRepository.save(entity);

                } catch (Exception e) {
                    Logger.getLogger(this.getClass().getName()).log(Level.INFO, "privilege is duplicated ");
                }
            });

            RoleEntity roleEntity = roleRepository.findByName(SUPERVISOR_ROLE).orElse(new RoleEntity());
            if (roleEntity.getId() == null) {
                roleEntity.setName(SUPERVISOR_ROLE);
                roleEntity.setTitle(SUPERVISOR_ROLE);
            }
            Set<PrivilegeEntity> allPrivileges = new HashSet<>((ArrayList<PrivilegeEntity>) privilegeRepository.findAll());
            roleEntity.setPrivileges(allPrivileges);
            roleEntity = roleRepository.save(roleEntity);

            UserEntity superUser = userRepository.findByUsername(SUPERVISOR_USER).orElse(new UserEntity());
            if (superUser.getId() == null) {
                PersonEntity personEntity = personRepository.findById(0L).orElse(new PersonEntity());
                if (personEntity.getId() == null) {
                    personEntity.setFirstName(SUPERVISOR_USER);
                    personEntity.setLastName(SUPERVISOR_USER);
                    personEntity.setNationalCode(SUPERVISOR_USER);
                    personEntity.setId(0L);
                    personEntity = personRepository.save(personEntity);
                }
                superUser.setPerson(personEntity);
                superUser.setUsername(SUPERVISOR_USER);
            }
            superUser.setEnabled(true);
            superUser.setDeleted(false);
            superUser.setAccountNonExpired(true);
            superUser.setCredentialsNonExpired(true);
            superUser.setAccountNonLocked(true);
            BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
            superUser.setPassword(bCryptPasswordEncoder.encode(SUPERVISOR_PWD));
            Set<RoleEntity> roleEntitySet = new HashSet<>();
            roleEntitySet.add(roleEntity);
            superUser.setRoles(roleEntitySet);
            userRepository.save(superUser);
        }
    }

    private List<Field> getFields() {
        Field[] declaredFields = AuthorityConstant.class.getDeclaredFields();
        List<Field> staticFields = new ArrayList<>();
        for (Field field : declaredFields) {
            if (java.lang.reflect.Modifier.isStatic(field.getModifiers())
                    && field.getType().equals(String.class)) {
                staticFields.add(field);
            }
        }
        return staticFields;
    }
}
