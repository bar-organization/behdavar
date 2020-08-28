package com.bar.behdavarbackend.util.pagination;

import com.bar.behdavardatabase.common.BaseEntity;
import org.hibernate.query.criteria.internal.path.RootImpl;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.*;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CommonSpecification<E extends BaseEntity> implements Specification<E> {

    private List<SearchCriteria> list;

    public CommonSpecification() {
        this.list = new ArrayList<>();
    }

    public CommonSpecification(List<SearchCriteria> list) {
        this.list = list;
    }

    public void add(SearchCriteria criteria) {
        list.add(criteria);
    }

    @Override
    public Predicate toPredicate(Root<E> root, CriteriaQuery<?> query, CriteriaBuilder builder) {

        //create a new predicate list
        List<Predicate> predicates = new ArrayList<>();
        List<String> items = new ArrayList<>();

        //add add criteria to predicates
        for (SearchCriteria criteria : list) {
            Class aClass = ((RootImpl) root).getEntityType().getJavaType();
            boolean isEntity = false;
            items = Arrays.asList(criteria.getKey().split("\\."));
            try {
                Field field = aClass.getDeclaredField(criteria.getKey());
                if (BaseEntity.class.isAssignableFrom(field.getType())) {
                    isEntity = true;
                }
            } catch (NoSuchFieldException e) {
                //nothing
            }

            if (criteria.getOperation().equals(SearchOperation.GREATER_THAN)) {
                predicates.add(builder.greaterThan(
                        root.get(criteria.getKey()), criteria.getValue().toString()));
            } else if (criteria.getOperation().equals(SearchOperation.LESS_THAN)) {
                predicates.add(builder.lessThan(
                        root.get(criteria.getKey()), criteria.getValue().toString()));
            } else if (criteria.getOperation().equals(SearchOperation.GREATER_THAN_EQUAL)) {
                predicates.add(builder.greaterThanOrEqualTo(
                        root.get(criteria.getKey()), criteria.getValue().toString()));
            } else if (criteria.getOperation().equals(SearchOperation.LESS_THAN_EQUAL)) {
                predicates.add(builder.lessThanOrEqualTo(
                        root.get(criteria.getKey()), criteria.getValue().toString()));
            } else if (criteria.getOperation().equals(SearchOperation.NOT_EQUAL)) {
                predicates.add(builder.notEqual(
                        root.get(criteria.getKey()), criteria.getValue()));
            } else if (criteria.getOperation().equals(SearchOperation.EQUAL)) {
                if (items.size() == 1) {
                    predicates.add(builder.equal(
                            !isEntity ? root.get(criteria.getKey()) : root.join(criteria.getKey()).get("id")
                            , criteria.getValue()));
                } else {
                    Join<Object, Object> join = null;
                    for (int i = 0; i < (items.size() - 1); i++) {
                        join = root.join(items.get(i));
                    }
                    predicates.add(builder.equal(
                            join.get(items.get(items.size() - 1))
                            , criteria.getValue()));
                }
            } else if (criteria.getOperation().equals(SearchOperation.MATCH)) {
                predicates.add(builder.like(
                        builder.lower(root.get(criteria.getKey())),
                        "%" + criteria.getValue().toString().toLowerCase() + "%"));
            } else if (criteria.getOperation().equals(SearchOperation.MATCH_END)) {
                predicates.add(builder.like(
                        builder.lower(root.get(criteria.getKey())),
                        criteria.getValue().toString().toLowerCase() + "%"));
            } else if (criteria.getOperation().equals(SearchOperation.MATCH_START)) {
                predicates.add(builder.like(
                        builder.lower(root.get(criteria.getKey())),
                        "%" + criteria.getValue().toString().toLowerCase()));
            } else if (criteria.getOperation().equals(SearchOperation.IN)) {
                predicates.add(builder.in(root.get(criteria.getKey())).value(criteria.getValue()));
            } else if (criteria.getOperation().equals(SearchOperation.NOT_IN)) {
                predicates.add(builder.not(root.get(criteria.getKey())).in(criteria.getValue()));
            }
        }

        return builder.and(predicates.toArray(new Predicate[0]));
    }
}