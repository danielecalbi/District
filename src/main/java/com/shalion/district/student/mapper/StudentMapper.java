package com.shalion.district.student.mapper;

import com.shalion.district.school.domain.School;
import com.shalion.district.school.dto.SchoolDto;
import com.shalion.district.student.domain.Student;
import com.shalion.district.student.dto.StudentDto;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

@Component
public class StudentMapper {

    public StudentDto map (Student student){
        StudentDto studentDto = new StudentDto();
        BeanUtils.copyProperties(student, studentDto);
        SchoolDto schoolDto = new SchoolDto();
        BeanUtils.copyProperties(student.getSchool(), schoolDto);
        studentDto.setSchoolDto(schoolDto);
        return studentDto;
    }

    public Student map(StudentDto studentDto) {
        Student student = new Student();
        BeanUtils.copyProperties(studentDto, student);
        School school = new School();
        BeanUtils.copyProperties(studentDto.getSchoolDto(), school);
        student.setSchool(school);
        return student;
    }

}
