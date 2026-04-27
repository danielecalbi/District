package com.shalion.district.student.dto;

import com.shalion.district.school.dto.SchoolDto;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;

@Data
public class StudentDto implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    private long id;
    private String name;
    private SchoolDto schoolDto;
}
