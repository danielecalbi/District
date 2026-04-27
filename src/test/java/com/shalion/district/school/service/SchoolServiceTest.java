package com.shalion.district.school.service;

import com.shalion.district.exception.ConflictException;
import com.shalion.district.school.domain.School;
import com.shalion.district.school.dto.SchoolDto;
import com.shalion.district.school.mapper.SchoolMapper;
import com.shalion.district.school.repository.SchoolRepository;
import com.shalion.district.student.domain.Student;
import com.shalion.district.student.dto.StudentDto;
import com.shalion.district.student.service.StudentService;
import com.shalion.district.util.DistrictUtils;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.BeanUtils;

import java.util.List;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
class SchoolServiceTest {

    @Mock
    private SchoolMapper schoolMapper;
    @Mock
    private SchoolRepository schoolRepository;
    @Mock
    private StudentService studentService;

    @InjectMocks
    private SchoolService schoolService;

    @Test
    void whenCreateIsSuccessful() {
        School school = DistrictUtils.getSchool();
        Mockito.when(schoolRepository.findByName(school.getName())).thenReturn(Optional.empty());

        schoolService.create(school);

        Mockito.verify(schoolRepository).findByName(school.getName());
        Mockito.verify(schoolRepository).save(school);
    }

    @Test
    void whenCreateAlreadyExistAndThrowAConflictException() {
        School school = DistrictUtils.getSchool();
        Mockito.when(schoolRepository.findByName(school.getName())).thenReturn(Optional.of(school));

        ConflictException ce = Assertions.assertThrows(ConflictException.class, () -> schoolService.create(school));

        Mockito.verify(schoolRepository).findByName(school.getName());
        Mockito.verify(schoolRepository, Mockito.never()).save(school);
        Assertions.assertEquals("School '" + school.getName() + "' already exists!", ce.getMessage());
    }

    @Test
    void whenGetIsSuccessful() {
        long schoolId = DistrictUtils.SCHOOL_ID;
        Mockito.when(schoolRepository.findById(schoolId)).thenReturn(Optional.of(DistrictUtils.getSchool()));

        School school = schoolService.get(schoolId);

        Assertions.assertEquals(schoolId, school.getId());
    }

    @Test
    void whenGetASchoolAndDoNotFoundItAndThrowAnEntityNotFoundException() {
        long schoolId = DistrictUtils.SCHOOL_ID;
        Mockito.when(schoolRepository.findById(schoolId)).thenReturn(Optional.empty());

        EntityNotFoundException enfe = Assertions.assertThrowsExactly(EntityNotFoundException.class, () -> schoolService.get(schoolId));

        Mockito.verify(schoolRepository).findById(schoolId);
        Assertions.assertEquals("School with id '" + schoolId + "' not found!", enfe.getMessage());
    }

    @Test
    void whenUpdateIsSuccessful() {
        School school = DistrictUtils.getSchool();
        School schoolToSave = DistrictUtils.getSchool();
        schoolToSave.setMaximumCapacity(350);
        Mockito.when(schoolRepository.findById(schoolToSave.getId())).thenReturn(Optional.of(school));
        Mockito.when(schoolRepository.save(schoolToSave)).thenReturn(schoolToSave);

        School schoolUpdated = schoolService.update(schoolToSave);

        Mockito.verify(schoolRepository).findById(school.getId());
        Mockito.verify(schoolRepository).save(schoolToSave);
        Assertions.assertNotEquals(school.getMaximumCapacity(), schoolUpdated.getMaximumCapacity());
    }

    @Test
    void whenUpdateIsUnsuccessfulBecauseDoNotFindThePreviousSchoolAndThrowAnEntityNotFoundException() {
        School school = DistrictUtils.getSchool();
        Mockito.when(schoolRepository.findById(school.getId())).thenReturn(Optional.empty());

        EntityNotFoundException enfe = Assertions.assertThrowsExactly(EntityNotFoundException.class, () -> schoolService.update(school));

        Mockito.verify(schoolRepository).findById(school.getId());
        Mockito.verify(schoolRepository, Mockito.never()).save(school);
        Assertions.assertEquals("School with id '" + school.getId() + "' not found!", enfe.getMessage());
    }

    @Test
    void whenDeleteIsSuccessful() {
        long schoolId = DistrictUtils.SCHOOL_ID;
        Mockito.doNothing().when(schoolRepository).deleteById(schoolId);

        schoolService.delete(schoolId);

        Mockito.verify(schoolRepository).deleteById(schoolId);
    }

    @Test
    void whenGetByNameIsSuccessful() {
        String name = DistrictUtils.STUDENT_NAME;
        School school = DistrictUtils.getSchool();
        Mockito.when(schoolRepository.findByNameContaining(name)).thenReturn(List.of(school));

        List<School> schools = schoolService.getByName(name);

        Assertions.assertEquals(1, schools.size());
        Assertions.assertEquals(school, schools.getFirst());
    }

    @Test
    void whenGetByIdWithEnlistedStudents() {
        Student student = DistrictUtils.getStudent();
        StudentDto studentDto = new StudentDto();
        BeanUtils.copyProperties(student, studentDto);
        SchoolDto schoolDto = new SchoolDto();
        schoolDto.setStudents(List.of(studentDto));
        Mockito.when(schoolRepository.findById(student.getSchool().getId())).thenReturn(Optional.of(student.getSchool()));
        Mockito.when(studentService.getBySchoolIdOrderById(student.getId())).thenReturn(List.of(student));
        Mockito.when(schoolMapper.map(student.getSchool(), List.of(student))).thenReturn(schoolDto);

        SchoolDto schoolDtoResult = schoolService.getByIdWithEnlistedStudents(student.getId());

        Assertions.assertEquals(schoolDto, schoolDtoResult);
        Assertions.assertEquals(1, schoolDtoResult.getStudents().size());
        Assertions.assertEquals(schoolDto.getStudents().getFirst(), studentDto);
    }

}
