package com.bar.behdavardatabase.entity;

import com.bar.behdavardatabase.common.BaseAuditorEntity;
import com.bar.behdavardatabase.constant.AddressConstant;

import javax.persistence.*;

@Entity
@Table(name = AddressConstant.TABLE_NAME, schema = AddressConstant.SCHEMA)
public class AddressEntity extends BaseAuditorEntity<String, Long> {

    @Column(name = AddressConstant.ID)
    private Long id;

    @JoinColumn(name = AddressConstant.FK_CITY, nullable = false, foreignKey = @ForeignKey(name = AddressConstant.CITY_FK_CONSTRAINT))
    @OneToOne
    private CityEntity city;

    @Column(name = AddressConstant.ADDRESS_DETAIL, nullable = false)
    private String addressDetail;

    public CityEntity getCity() {
        return city;
    }

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    public void setCity(CityEntity city) {
        this.city = city;
    }

    public String getAddressDetail() {
        return addressDetail;
    }

    public void setAddressDetail(String addressDetail) {
        this.addressDetail = addressDetail;
    }
}
