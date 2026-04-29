package com.shalion.district.service;

import com.shalion.district.school.domain.School;
import com.shalion.district.school.repository.SchoolRepository;
import com.shalion.district.student.domain.Student;
import com.shalion.district.student.repository.StudentRepository;
import com.shalion.district.util.DistrictUtils;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
class DistrictServiceTest {

    @Mock
    private SchoolRepository schoolRepository;
    @Mock
    private StudentRepository studentRepository;

    @InjectMocks
    private DistrictService districtService;

    @Test
    void whenGetSchoolIsSuccessful() {
        long schoolId = DistrictUtils.SCHOOL_ID;
        School school = DistrictUtils.getSchool();
        Mockito.when(schoolRepository.findById(schoolId)).thenReturn(Optional.of(school));

        School schoolReturned = districtService.getSchool(schoolId);

        Assertions.assertEquals(school.getId(), schoolReturned.getId());
        Assertions.assertEquals(school.getName(), schoolReturned.getName());
        Assertions.assertEquals(school.getMaximumCapacity(), schoolReturned.getMaximumCapacity());
    }

    @Test
    void whenGetASchoolAndDoNotFoundItAndThrowAnEntityNotFoundException() {
        long schoolId = DistrictUtils.SCHOOL_ID;
        Mockito.when(schoolRepository.findById(schoolId)).thenReturn(Optional.empty());

        EntityNotFoundException enfe = Assertions.assertThrowsExactly(EntityNotFoundException.class, () -> districtService.getSchool(schoolId));

        Mockito.verify(schoolRepository).findById(schoolId);
        Assertions.assertEquals("School with id '" + schoolId + "' not found!", enfe.getMessage());
    }

    @Test
    void getBySchoolIdOrderById() {
        long schoolId = DistrictUtils.SCHOOL_ID;
        Student student = DistrictUtils.getStudent();
        Mockito.when(studentRepository.findAllBySchoolIdOrderById(schoolId)).thenReturn(List.of(student));

        List<Student> studentsReturned = districtService.getBySchoolIdOrderById(schoolId);

        Assertions.assertEquals(1, studentsReturned.size());
        Assertions.assertEquals(schoolId, studentsReturned.getFirst().getSchoolId());
    }

}
