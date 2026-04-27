package com.shalion.district.school.mapper;

import com.shalion.district.school.domain.School;
import com.shalion.district.school.dto.SchoolDto;
import com.shalion.district.student.domain.Student;
import com.shalion.district.util.DistrictUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

class SchoolMapperTest {

    private final SchoolMapper schoolMapper = new SchoolMapper();

    @Test
    void whenMapSchoolAndStudentsIsSuccessful() {
        School school = DistrictUtils.getSchool();
        Student student = DistrictUtils.getStudent();

        SchoolDto schoolDto = schoolMapper.map(school, List.of(student));

        Assertions.assertEquals(school.getId(), schoolDto.getId());
        Assertions.assertEquals(school.getName(), schoolDto.getName());
        Assertions.assertEquals(school.getMaximumCapacity(), schoolDto.getMaximumCapacity());
        Assertions.assertEquals(1, schoolDto.getStudents().size());
        Assertions.assertEquals(student.getId(), schoolDto.getStudents().getFirst().getId());
        Assertions.assertEquals(student.getName(), schoolDto.getStudents().getFirst().getName());
    }

    @Test
    void whenMapSchoolIsSuccessful() {
        School school = DistrictUtils.getSchool();

        SchoolDto schoolDto = schoolMapper.map(school);

        Assertions.assertEquals(school.getId(), schoolDto.getId());
        Assertions.assertEquals(school.getName(), schoolDto.getName());
        Assertions.assertEquals(school.getMaximumCapacity(), schoolDto.getMaximumCapacity());
    }

    @Test
    void whenMapSchoolDtoIsSuccessful() {
        SchoolDto schoolDto = DistrictUtils.getSchoolDto();

        School school = schoolMapper.map(schoolDto);

        Assertions.assertEquals(schoolDto.getId(), school.getId());
        Assertions.assertEquals(schoolDto.getName(), school.getName());
        Assertions.assertEquals(schoolDto.getMaximumCapacity(), school.getMaximumCapacity());
    }

}
