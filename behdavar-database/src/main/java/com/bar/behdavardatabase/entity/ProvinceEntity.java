package com.bar.behdavardatabase.entity;

import com.bar.behdavardatabase.common.BaseCodeTitleEntity;
import com.bar.behdavardatabase.constant.ProvinceConstant;
import com.bar.behdavardatabase.constant.common.BaseCodeTitleConstant;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = ProvinceConstant.TABLE_NAME, schema = ProvinceConstant.SCHEMA, uniqueConstraints = @UniqueConstraint(columnNames = BaseCodeTitleConstant.CODE))
public class ProvinceEntity extends BaseCodeTitleEntity<String, Long> {
    @Id
    @Column(name = "ID")
    private Long id;

    @OneToMany(mappedBy = ProvinceConstant.PROVINCE_MAPPED_BY)
    private Set<CityEntity> cities;

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    public Set<CityEntity> getCities() {
        return cities;
    }

    public void setCities(Set<CityEntity> cities) {
        this.cities = cities;
    }
}
