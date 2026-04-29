package com.shalion.district.school.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;
import org.hibernate.validator.constraints.Range;

import java.io.Serial;
import java.io.Serializable;

@Entity
@Data
public class School implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    public static final int MIN_MAXIMUM_CAPACITY = 50;
    public static final int MAX_MAXIMUM_CAPACITY = 2000;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long id;
    @Column(unique = true, nullable = false)
    private String name;
    @Range(min = MIN_MAXIMUM_CAPACITY, max = MAX_MAXIMUM_CAPACITY)
    private int maximumCapacity;
}
