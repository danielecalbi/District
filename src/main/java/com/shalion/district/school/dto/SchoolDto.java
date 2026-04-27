package com.shalion.district.school.dto;

import com.shalion.district.student.dto.StudentDto;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;

@Data
public class SchoolDto implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    private long id;
    private String name;
    private int maximumCapacity;
    private List<StudentDto> students;
}
