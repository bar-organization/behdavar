package com.bar.behdavardatabase.entity.security;

import com.bar.behdavardatabase.common.BaseAuditorEntity;
import com.bar.behdavardatabase.constant.ContactConstant;
import com.bar.behdavardatabase.constant.common.BaseConstant;
import com.bar.behdavardatabase.entity.PersonEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.envers.AuditJoinTable;
import org.hibernate.envers.AuditOverride;
import org.hibernate.envers.AuditOverrides;
import org.hibernate.envers.Audited;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Set;

import static com.bar.behdavardatabase.constant.common.BaseConstant.BASE_TABLE_PREFIX;

@Setter
@Getter
@NoArgsConstructor
@Audited
@AuditOverrides({@AuditOverride(forClass = BaseAuditorEntity.class)})
@Entity
@Table(name = UserEntity.TABLE_NAME, schema = ContactConstant.SCHEMA, uniqueConstraints = @UniqueConstraint(columnNames = "USERNAME"))
public class UserEntity extends BaseAuditorEntity<String, Long> {

    public static final String TABLE_NAME = BASE_TABLE_PREFIX + "USER";
    public static final String SEQ_NAME = "USER" + BaseConstant.SEQUENCE;
    public static final String ROLES = "roles";

    @Column(name = "ID")
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = SEQ_NAME)
    @SequenceGenerator(name = SEQ_NAME, sequenceName = SEQ_NAME, allocationSize = ALLOCATION_SIZE)
    private Long id;

    @Column(name = "USERNAME", nullable = false)
    @NotNull
    private String username;

    @Column(name = "PASSWORD", nullable = false)
    @NotNull
    private String password;

    @Column(name = "ENABLED", nullable = false)
    @NotNull
    private boolean enabled;

    @Column(name = "TOKEN_EXIPIRED", nullable = false)
    @NotNull
    private boolean tokenExpired;

    @Column(name = "IS_ACCOUNT_NONEXPIRED", nullable = false)
    @NotNull
    private boolean isAccountNonExpired;

    @Column(name = "IS_ACCOUNT_NON_LOCKED", nullable = false)
    @NotNull
    private boolean isAccountNonLocked;

    @Column(name = "IS_CREDENTIALS_NON_EXPIRED", nullable = false)
    @NotNull
    private boolean isCredentialsNonExpired;

    @AuditJoinTable
    @ManyToMany
    @JoinTable(
            name = BASE_TABLE_PREFIX + "USERS_ROLES",
            joinColumns = @JoinColumn(
                    name = "USER_ID", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(
                    name = "ROLE_ID", referencedColumnName = "id"))
    private Set<RoleEntity> roles;

    @OneToOne
    @JoinColumn(name = "PERSON_ID", referencedColumnName = "ID", foreignKey = @ForeignKey(name = "USER_PRESON_FK"), nullable = false)
    @NotNull
    private PersonEntity person;
}