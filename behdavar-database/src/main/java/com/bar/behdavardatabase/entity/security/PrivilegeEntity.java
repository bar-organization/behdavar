package com.bar.behdavardatabase.entity.security;

import com.bar.behdavardatabase.common.BaseAuditorEntity;
import com.bar.behdavardatabase.constant.ContactConstant;
import com.bar.behdavardatabase.constant.common.BaseConstant;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.envers.AuditJoinTable;
import org.hibernate.envers.AuditOverride;
import org.hibernate.envers.AuditOverrides;
import org.hibernate.envers.Audited;

import javax.persistence.*;
import java.util.Set;

import static com.bar.behdavardatabase.constant.common.BaseConstant.BASE_TABLE_PREFIX;

@Setter
@Getter
@Audited
@AuditOverrides({@AuditOverride(forClass = BaseAuditorEntity.class)})
@Entity
@Table(name = PrivilegeEntity.TABLE_NAME, schema = ContactConstant.SCHEMA, uniqueConstraints = @UniqueConstraint(columnNames = "NAME"))
public class PrivilegeEntity extends BaseAuditorEntity<String, Long> {

    public static final String TABLE_NAME = BASE_TABLE_PREFIX + "PRIVILEGE";
    public static final String SEQ_NAME = "PRIVILEGE" + BaseConstant.SEQUENCE;

    @Column(name = "ID")
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = PrivilegeEntity.SEQ_NAME)
    @SequenceGenerator(name = PrivilegeEntity.SEQ_NAME, sequenceName = PrivilegeEntity.SEQ_NAME, allocationSize = ALLOCATION_SIZE)
    private Long id;

    private String name;

    @AuditJoinTable
    @ManyToMany(mappedBy = "privileges")
    private Set<RoleEntity> roles;

}