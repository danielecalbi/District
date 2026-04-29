package com.shalion.district.student.mapper;

import com.shalion.district.student.domain.Student;
import com.shalion.district.student.dto.StudentDto;
import com.shalion.district.util.DistrictUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class StudentMapperTest {

    private final StudentMapper studentMapper = new StudentMapper();

    @Test
    void whenMapStudentIsSuccessful() {
        Student student = DistrictUtils.getStudent();

        StudentDto studentDto = studentMapper.map(student);

        Assertions.assertEquals(student.getId(), studentDto.getId());
        Assertions.assertEquals(student.getName(), studentDto.getName());
        Assertions.assertEquals(student.getSchoolId(), studentDto.getSchoolId());
    }

    @Test
    void whenMapStudentDtoIsSuccessful() {
        StudentDto studentDto = DistrictUtils.getStudentDto();

        Student student = studentMapper.map(studentDto);

        Assertions.assertEquals(studentDto.getId(), student.getId());
        Assertions.assertEquals(studentDto.getName(), student.getName());
        Assertions.assertEquals(studentDto.getSchoolId(), student.getSchoolId());
    }

}
