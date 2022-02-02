package com.elattar.example.service;

import com.elattar.example.search.SearchCriteria;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static org.springframework.data.jpa.repository.query.QueryUtils.toOrders;

/**
 * FilterService
 */
@Service
public class FilterService<T> {
    @PersistenceContext
    private EntityManager entityManager;


    public Page<T> search(String filter, Pageable pageable, Class<T> entityClass) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<T> criteriaQuery = criteriaBuilder.createQuery(entityClass);
        Root<T> root = criteriaQuery.from(entityClass);

        Predicate predicate = this.predicateRootBuilder(criteriaBuilder, getCriteriaParams(filter), root);
        Sort sort = pageable.isPaged() ? pageable.getSort() : Sort.unsorted();
        if (sort.isSorted())
            criteriaQuery.orderBy(toOrders(sort, root, criteriaBuilder));
        criteriaQuery.distinct(true);
        criteriaQuery.where(predicate);

        TypedQuery<T> typedQuery = entityManager.createQuery(criteriaQuery);
        long total = typedQuery.getResultList().size();

        // Sets the offset position in the result set to start pagination
        typedQuery.setFirstResult((int) pageable.getOffset());

        // Sets the maximum number of entities that should be included in the page
        typedQuery.setMaxResults(pageable.getPageSize());

        List<T> content = total > pageable.getOffset() ? typedQuery.getResultList() : Collections.<T>emptyList();

        return new PageImpl<T>(content, pageable, total);
    }

    /**
     * supports ":" for like, "!: not like, "<" less than, "<=" less than or equal, ">" greater than, ">=" greater than or equal
     * Split filter string by ","
     * Example: firstName:abdelrahman,lastName:elattar
     *
     * @param filter String containing search parameters separated by , in case multiple search
     * @return list of SearchCriteria Object
     */
    public static List<SearchCriteria> getCriteriaParams(String filter) {
        List<SearchCriteria> params = new ArrayList<>();
        if (filter != null) {
            Pattern pattern = Pattern.compile("(\\w+?)(:|!:|<|<=|>|>=)(\\w+?),");
            Matcher matcher = pattern.matcher(filter + ",");
            while (matcher.find()) {
                params.add(new SearchCriteria(matcher.group(1),
                        matcher.group(2), matcher.group(3)));
            }
        }
        return params;
    }

    private Predicate predicateRootBuilder(CriteriaBuilder builder, List<SearchCriteria> params, Root<T> root) {
        Predicate predicate = builder.conjunction();

        for (SearchCriteria param : params) {
            switch (param.getOperation().toLowerCase()) {
                case ">":
                    predicate = builder.and(predicate,
                            builder.greaterThan(root.get(param.getFieldName()),
                                    param.getFieldValue().toString()));
                    break;
                case ">=":
                    predicate = builder.and(predicate,
                            builder.greaterThanOrEqualTo(root.get(param.getFieldName()),
                                    param.getFieldValue().toString()));
                    break;
                case "<":
                    predicate = builder.and(predicate,
                            builder.lessThan(root.get(param.getFieldName()),
                                    param.getFieldValue().toString()));
                    break;
                case "<=":
                    predicate = builder.and(predicate,
                            builder.lessThanOrEqualTo(root.get(param.getFieldName()),
                                    param.getFieldValue().toString()));
                    break;
                case ":":
                    if (root.get(param.getFieldName()).getJavaType() == String.class) {
                        predicate = builder.and(predicate,
                                builder.like(root.get(param.getFieldName()),
                                        "%" + param.getFieldValue() + "%"));
                    } else {
                        predicate = builder.and(predicate,
                                builder.equal(root.get(param.getFieldName()), param.getFieldValue()));
                    }
                    break;
                case "!:":
                    if (root.get(param.getFieldName()).getJavaType() == String.class) {
                        predicate = builder.and(predicate,
                                builder.notLike(root.get(param.getFieldName()),
                                        "%" + param.getFieldValue() + "%"));
                    } else {
                        predicate = builder.and(predicate,
                                builder.notEqual(root.get(param.getFieldName()), param.getFieldValue()));
                    }
                    break;
            }
        }
        return predicate;
    }
}
