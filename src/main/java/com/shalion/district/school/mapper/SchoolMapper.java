package com.shalion.district.school.mapper;

import com.shalion.district.school.domain.School;
import com.shalion.district.school.dto.SchoolDto;
import com.shalion.district.student.domain.Student;
import com.shalion.district.student.dto.StudentDto;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class SchoolMapper {

    public SchoolDto map(School school, List<Student> students) {
        SchoolDto schoolDto = new SchoolDto();
        List<StudentDto> studentDtos = new ArrayList<>();
        for (Student student : students) {
            StudentDto studentDto = new StudentDto();
            BeanUtils.copyProperties(student, studentDto);
            studentDtos.add(studentDto);
        }
        schoolDto.setStudents(studentDtos);
        BeanUtils.copyProperties(school, schoolDto);
        return schoolDto;
    }

    public School map(SchoolDto schoolDto) {
        School school = new School();
        BeanUtils.copyProperties(schoolDto, school);
        return school;
    }

    public SchoolDto map(School school) {
        SchoolDto schoolDto = new SchoolDto();
        BeanUtils.copyProperties(school, schoolDto);
        return schoolDto;
    }

}
