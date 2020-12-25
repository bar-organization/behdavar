package com.bar.behdavarbackend.util.pagination;

import com.bar.behdavardatabase.common.BaseEntity;
import org.hibernate.query.criteria.internal.path.RootImpl;
import org.springframework.data.jpa.domain.Specification;
import sun.reflect.generics.reflectiveObjects.ParameterizedTypeImpl;

import javax.persistence.criteria.*;
import java.lang.reflect.Field;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

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
        List<String> items;

        //add add criteria to predicates
        for (SearchCriteria criteria : list) {
            Class aClass = ((RootImpl) root).getEntityType().getJavaType();
            boolean isEntity = false;
            items = Arrays.asList(criteria.getKey().split("\\."));
            try {

                Class baseClass = Class.forName(aClass.getName());
                for (int i = 0; i < items.size(); i++) {
                    Field declaredField = baseClass.getDeclaredField(items.get(i));
                    if (i == (items.size() - 1) && declaredField.getType().equals(LocalDate.class)) {
                            criteria.setValue(LocalDate.parse(criteria.getValue().toString()));
                    }

                    if (Set.class.isAssignableFrom(declaredField.getType()) ||
                            List.class.isAssignableFrom(declaredField.getType())) {
                        baseClass = Class.forName(Arrays.stream(((ParameterizedTypeImpl) declaredField.getGenericType()).getActualTypeArguments()).iterator().next().getTypeName());
                    } else if (BaseEntity.class.isAssignableFrom(declaredField.getType())) {
                        baseClass = declaredField.getType();
                    } else {
                        break;
                    }
                }

                Field field = aClass.getDeclaredField(criteria.getKey());

                if (BaseEntity.class.isAssignableFrom(field.getType())) {
                    isEntity = true;
                }
            } catch (NoSuchFieldException | ClassNotFoundException e) {
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
                        if (i == 0) {
                            join = root.join(items.get(i));
                        } else {
                            join = join.join(items.get(i));
                        }
                    }
                    if (join != null) {
                        predicates.add(builder.equal(
                                join.get(items.get(items.size() - 1))
                                , criteria.getValue()));
                    }
                }
            } else if (criteria.getOperation().equals(SearchOperation.MATCH)) {
                if (items.size() == 1) {
                    predicates.add(builder.like(
                            !isEntity ? root.get(criteria.getKey()) : root.join(criteria.getKey()).get("id")
                            , "%" + criteria.getValue().toString().toLowerCase() + "%"));
                } else {
                    Join<Object, Object> join = null;
                    for (int i = 0; i < (items.size() - 1); i++) {
                        if (i == 0) {
                            join = root.join(items.get(i));
                        } else {
                            join = join.join(items.get(i));
                        }
                    }
                    if (join != null) {
                        predicates.add(builder.like(
                                join.get(items.get(items.size() - 1))
                                , "%" + criteria.getValue().toString().toLowerCase() + "%"));
                    }
                }
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
            } else if (criteria.getOperation().equals(SearchOperation.IS_NULL)) {
                predicates.add(builder.isNull(root.get(criteria.getKey())));
            } else if (criteria.getOperation().equals(SearchOperation.NOT_NULL)) {
                predicates.add(builder.isNotNull(root.get(criteria.getKey())));
            }
        }

        return builder.and(predicates.toArray(new Predicate[0]));
    }
}