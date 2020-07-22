package com.bar.behdavardatabase.entity;

import com.bar.behdavardatabase.common.BaseCodeTitleEntity;
import com.bar.behdavardatabase.constant.CityConstant;
import com.bar.behdavardatabase.constant.common.BaseCodeTitleConstant;

import javax.persistence.*;


@Entity
@Table(name = CityConstant.TABLE_NAME, schema = CityConstant.SCHEMA, uniqueConstraints = @UniqueConstraint(columnNames = BaseCodeTitleConstant.CODE))
public class CityEntity extends BaseCodeTitleEntity<String, Long> {

    @Column(name = CityConstant.ID)
    private Long id;

    @JoinColumn(name = CityConstant.FK_PROVINCE, nullable = false, foreignKey = @ForeignKey(name = CityConstant.PROVINCE_FK_CONSTRAINT))
    @ManyToOne(fetch = FetchType.LAZY)
    private ProvinceEntity province;

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    public ProvinceEntity getProvince() {
        return province;
    }

    public void setProvince(ProvinceEntity province) {
        this.province = province;
    }
}
