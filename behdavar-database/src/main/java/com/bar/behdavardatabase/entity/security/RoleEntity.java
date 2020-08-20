package com.bar.behdavardatabase.entity.security;

import com.bar.behdavardatabase.common.BaseAuditorEntity;
import com.bar.behdavardatabase.constant.ContactConstant;
import com.bar.behdavardatabase.constant.common.BaseConstant;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

import static com.bar.behdavardatabase.constant.common.BaseConstant.BASE_TABLE_PREFIX;

@Setter
@Getter
@Entity
@Table(name = RoleEntity.TABLE_NAME, schema = ContactConstant.SCHEMA, uniqueConstraints = @UniqueConstraint(columnNames = "NAME"))
public class RoleEntity extends BaseAuditorEntity<String, Long> {

    public static final String TABLE_NAME = BASE_TABLE_PREFIX + "ROLE";
    public static final String SEQ_NAME = "ROLE" + BaseConstant.SEQUENCE;

    @Column(name = "ID")
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = RoleEntity.SEQ_NAME)
    @SequenceGenerator(name = RoleEntity.SEQ_NAME, sequenceName = RoleEntity.SEQ_NAME)
    private Long id;

    private String name;
    @ManyToMany(mappedBy = "roles")
    private Set<UserEntity> users;

    @ManyToMany
    @JoinTable(
            name = "roles_privileges",
            joinColumns = @JoinColumn(
                    name = "ROLE_ID", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(
                    name = "PRIVILEGE_ID", referencedColumnName = "id"))
    private Set<PrivilegeEntity> privileges;

}