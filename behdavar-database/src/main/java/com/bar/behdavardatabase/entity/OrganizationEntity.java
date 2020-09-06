package com.bar.behdavardatabase.entity;

import com.bar.behdavardatabase.common.BaseAuditorEntity;
import com.bar.behdavardatabase.constant.OrganizationConstant;
import org.hibernate.envers.AuditOverride;
import org.hibernate.envers.AuditOverrides;
import org.hibernate.envers.Audited;

import javax.persistence.*;

@Audited
@AuditOverrides({@AuditOverride(forClass = BaseAuditorEntity.class)})
@Entity
@Table(name = OrganizationConstant.TABLE_NAME, schema = OrganizationConstant.SCHEMA)
public class OrganizationEntity extends BaseAuditorEntity<String, Long> {

    @Id
    @Column(name = "ID")
    private Long id;

    @Column(name = OrganizationConstant.TITLE, nullable = false)
    private String title;

    @Column(name = OrganizationConstant.CONTACT_NUMBER)
    private String contactNumber;

    @Column(name = OrganizationConstant.FAX)
    private String fax;

    @Column(name = OrganizationConstant.EMAIL)
    private String email;

    @Column(name = OrganizationConstant.DESCRIPTION)
    private String description;

    @JoinColumn(name = OrganizationConstant.FK_ADDRESS, nullable = false, foreignKey = @ForeignKey(name = OrganizationConstant.ADDRESS_FK_CONSTRAINT))
    @OneToOne(fetch = FetchType.LAZY)
    private AddressEntity address;


    @JoinColumn(name = OrganizationConstant.FK_PERSON, foreignKey = @ForeignKey(name = OrganizationConstant.PERSON_FK_CONSTRAINT))
    @ManyToOne(fetch = FetchType.LAZY)
    private PersonEntity person;

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContactNumber() {
        return contactNumber;
    }

    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }

    public String getFax() {
        return fax;
    }

    public void setFax(String fax) {
        this.fax = fax;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public AddressEntity getAddress() {
        return address;
    }

    public void setAddress(AddressEntity address) {
        this.address = address;
    }

    public PersonEntity getPerson() {
        return person;
    }

    public void setPerson(PersonEntity person) {
        this.person = person;
    }
}
