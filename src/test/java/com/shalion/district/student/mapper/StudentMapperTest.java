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
        Assertions.assertEquals(student.getSchool().getId(), studentDto.getSchoolDto().getId());
        Assertions.assertEquals(student.getSchool().getName(), studentDto.getSchoolDto().getName());
        Assertions.assertEquals(student.getSchool().getMaximumCapacity(), studentDto.getSchoolDto().getMaximumCapacity());
    }

    @Test
    void whenMapStudentDtoIsSuccessful() {
        StudentDto studentDto = DistrictUtils.getStudentDto();

        Student student = studentMapper.map(studentDto);

        Assertions.assertEquals(studentDto.getId(), student.getId());
        Assertions.assertEquals(studentDto.getName(), student.getName());
        Assertions.assertEquals(studentDto.getSchoolDto().getId(), student.getSchool().getId());
        Assertions.assertEquals(studentDto.getSchoolDto().getName(), student.getSchool().getName());
        Assertions.assertEquals(studentDto.getSchoolDto().getMaximumCapacity(), student.getSchool().getMaximumCapacity());
    }

}
