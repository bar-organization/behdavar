package com.bar.behdavardatabase.entity;

import com.bar.behdavardatabase.common.BaseAuditorEntity;
import com.bar.behdavardatabase.constant.PersonConstant;

import javax.persistence.*;
import java.util.Set;


@Entity
@Table(name = PersonConstant.TABLE_NAME, schema = PersonConstant.SCHEMA,
        uniqueConstraints = @UniqueConstraint(columnNames = {PersonConstant.FIRST_NAME, PersonConstant.LAST_NAME}))
public class PersonEntity extends BaseAuditorEntity<String, Long> {

    @Id
    @Column(name = "ID")
    private Long id;

    @Column(name = PersonConstant.FIRST_NAME)
    private String firstName;

    @Column(name = PersonConstant.LAST_NAME)
    private String lastName;

    @Column(name = PersonConstant.EMAIL)
    private String email;

    @Column(name = PersonConstant.PHONE_NUMBER)
    private String phoneNumber;

    @Column(name = PersonConstant.DESCRIPTION)
    private String description;

    @OneToMany(mappedBy = "person")
    private Set<OrganizationEntity> organizations;

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Set<OrganizationEntity> getOrganizations() {
        return organizations;
    }

    public void setOrganizations(Set<OrganizationEntity> organizations) {
        this.organizations = organizations;
    }
}
