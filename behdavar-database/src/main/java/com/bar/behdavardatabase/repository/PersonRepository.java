package com.bar.behdavardatabase.repository;

import com.bar.behdavardatabase.entity.PersonEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PersonRepository extends AbstractRepository<PersonEntity, Long> {

    @Query("select p from PersonEntity p where " +
            "concat(p.firstName, p.lastName) like %:suggest% " +
            " or p.nationalCode like %:suggest%")
    List<PersonEntity> findSuggestion(@Param("suggest") String suggest);

    PersonEntity findByNationalCode(String nationalCode);

    @Query(value = "update BST_PERSON  p " +
            " set p.FULL_NAME = REPLACE(p.FULL_NAME , 'ي' , 'ی'), " +
            " p.LAST_NAME = REPLACE(p.LAST_NAME , 'ي' , 'ی'), " +
            " p.FIRST_NAME = REPLACE(p.FIRST_NAME , 'ي' , 'ی')" , nativeQuery = true)
    void convertArabicLetters();


}
