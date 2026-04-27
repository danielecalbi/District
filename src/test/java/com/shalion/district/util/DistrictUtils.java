package com.shalion.district.util;

import com.shalion.district.school.domain.School;
import com.shalion.district.school.dto.SchoolDto;
import com.shalion.district.student.domain.Student;
import com.shalion.district.student.dto.StudentDto;

public class DistrictUtils {

    public static final int BAD_MAXIMUM_CAPACITY = 2001;
    public static final int GOOD_MAXIMUM_CAPACITY = 100;
    public static final long SCHOOL_ID = 1;
    public static final String SCHOOL_NAME = "Columbia University";
    public static final long STUDENT_ID = 1;
    public static final String STUDENT_NAME = "John Doe";

    public static School getSchool() {
        School school = new School();
        school.setId(SCHOOL_ID);
        school.setName(SCHOOL_NAME);
        school.setMaximumCapacity(100);
        return school;
    }

    public static School getSchool(int maximumCapacity) {
        School school = new School();
        school.setId(SCHOOL_ID);
        school.setName(SCHOOL_NAME);
        school.setMaximumCapacity(maximumCapacity);
        return school;
    }

    public static School getOtherSchool() {
        School school = new School();
        school.setId(2);
        school.setName("Harvard");
        school.setMaximumCapacity(GOOD_MAXIMUM_CAPACITY);
        return school;
    }

    public static Student getStudent() {
        Student student = new Student();
        student.setId(STUDENT_ID);
        student.setName(STUDENT_NAME);
        student.setSchool(getSchool(GOOD_MAXIMUM_CAPACITY));
        return student;
    }

    public static SchoolDto getSchoolDto() {
        SchoolDto schoolDto = new SchoolDto();
        schoolDto.setId(1);
        schoolDto.setName(SCHOOL_NAME);
        schoolDto.setMaximumCapacity(100);
        return schoolDto;
    }

    public static StudentDto getStudentDto() {
        StudentDto studentDto = new StudentDto();
        studentDto.setId(1);
        studentDto.setName(STUDENT_NAME);
        studentDto.setSchoolDto(getSchoolDto());
        return studentDto;
    }

}
