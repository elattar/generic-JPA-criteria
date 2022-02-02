package com.elattar.example.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.Iterator;
import java.util.List;

public class JsonResponseCreator<T> {
    protected final long total;
    protected int page;
    protected int size;
    protected String direction;
    protected String orderBy;
    protected final List<T> entity;

    @JsonCreator
    public JsonResponseCreator(long total,
                               Pageable pageable,
                               List<T> entity) {
        this.total = total;
        this.page = pageable.getPageNumber();
        this.size = pageable.getPageSize();
        Iterator<Sort.Order> iterator = pageable.getSort().iterator();
        while (iterator.hasNext()) {
            Sort.Order next = iterator.next();
            this.direction = next.getDirection().toString();
            this.orderBy = next.getProperty();
        }
        this.entity = entity;
    }

    public List<T> getRows() {
        return this.entity;
    }

    public long getTotal() {
        return this.total;
    }

    public int getPage() {
        return this.page;
    }

    public int getSize() {
        return this.size;
    }

    public String getDirection() {
        return this.direction;
    }

    public String getOrderBy() {
        return this.orderBy;
    }

    @JsonProperty("rows")
    public List<T> getEntity() {
        return entity;
    }
}