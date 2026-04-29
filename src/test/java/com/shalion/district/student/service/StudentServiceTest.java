package com.shalion.district.student.service;

import com.shalion.district.exception.ConflictException;
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
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
class StudentServiceTest {

    @Mock
    private SchoolRepository schoolRepository;
    @Mock
    private StudentRepository studentRepository;

    @InjectMocks
    private StudentService studentService;

    @Test
    void whenCreateAndMaximumCapacityLessThanMaxMaximumCapacitySaveStudent() {
        Student student = DistrictUtils.getStudent();
        Mockito.when(schoolRepository.findById(student.getId())).thenReturn(Optional.of(DistrictUtils.getSchool(DistrictUtils.GOOD_MAXIMUM_CAPACITY)));
        Mockito.when(studentRepository.save(student)).thenReturn(student);

        studentService.create(student);

        Mockito.verify(studentRepository).save(student);
    }

    @Test
    void whenCreateAndMaximumCapacityMoreThanMaxMaximumCapacityThrowAConflictException() {
        Student student = DistrictUtils.getStudent();

        Mockito.when(schoolRepository.findById(student.getId())).thenReturn(Optional.of(DistrictUtils.getSchool(DistrictUtils.BAD_MAXIMUM_CAPACITY)));

        ConflictException ce = Assertions.assertThrows(ConflictException.class, () -> studentService.create(student));

        Mockito.verify(studentRepository, Mockito.never()).save(student);
        Assertions.assertEquals("Maximum capacity exceeded for school with id '" + student.getSchoolId() + "'! Student '" + student.getName() + "' has been not created!", ce.getMessage());
    }

    @Test
    void whenGetIsSuccessful() {
        long studentId = DistrictUtils.STUDENT_ID;
        Mockito.when(studentRepository.findById(studentId)).thenReturn(Optional.of(DistrictUtils.getStudent()));

        Student studentResult = studentService.get(studentId);

        Assertions.assertEquals(studentId, studentResult.getId());
    }

    @Test
    void whenGetAStudentAndDoNotFoundItAndThrowAnEntityNotFoundException() {
        long studentId = DistrictUtils.STUDENT_ID;
        Mockito.when(studentRepository.findById(studentId)).thenReturn(Optional.empty());

        EntityNotFoundException enfe = Assertions.assertThrowsExactly(EntityNotFoundException.class, () -> studentService.get(studentId));

        Mockito.verify(studentRepository).findById(studentId);
        Assertions.assertEquals("Student with id '" + studentId + "' not found!", enfe.getMessage());
    }

    @Test
    void whenUpdateIsSuccessful() {
        Student student = DistrictUtils.getStudent();
        Student studentToSave = DistrictUtils.getStudent();
        studentToSave.setSchoolId(DistrictUtils.getOtherSchool().getId());
        Mockito.when(studentRepository.findById(studentToSave.getId())).thenReturn(Optional.of(student));
        Mockito.when(studentRepository.save(studentToSave)).thenReturn(studentToSave);

        Student studentUpdated = studentService.update(studentToSave);

        Mockito.verify(studentRepository).findById(student.getId());
        Mockito.verify(studentRepository).save(studentToSave);
        Assertions.assertNotEquals(studentUpdated.getSchoolId(), student.getSchoolId());
    }

    @Test
    void whenUpdateIsUnsuccessfulBecauseDoNotFindThePreviousStudentAndThrowAnEntityNotFoundException() {
        Student student = DistrictUtils.getStudent();
        Mockito.when(studentRepository.findById(student.getId())).thenReturn(Optional.empty());

        EntityNotFoundException enfe = Assertions.assertThrowsExactly(EntityNotFoundException.class, () -> studentService.update(student));

        Mockito.verify(studentRepository).findById(student.getId());
        Mockito.verify(studentRepository, Mockito.never()).save(student);
        Assertions.assertEquals("Student with id '" + student.getId() + "' not found!", enfe.getMessage());
    }

    @Test
    void whenDeleteIsSuccessful() {
        long studentId = DistrictUtils.STUDENT_ID;
        Mockito.doNothing().when(studentRepository).deleteById(studentId);

        studentService.delete(studentId);

        Mockito.verify(studentRepository).deleteById(studentId);
    }

    @Test
    void whenGetBySchoolIdOrderById() {
        Student student = DistrictUtils.getStudent();
        long schoolId = DistrictUtils.SCHOOL_ID;
        Mockito.when(studentRepository.findAllBySchoolIdOrderById(schoolId)).thenReturn(List.of(DistrictUtils.getStudent()));

        List<Student> students = studentService.getBySchoolIdOrderById(schoolId);

        Assertions.assertEquals(1, students.size());
        Assertions.assertEquals(student, students.getFirst());
    }

    @Test
    void whenGetBySchoolIdAndStudentNameOrderByStudentName() {
        Student student = DistrictUtils.getStudent();
        long schoolId = DistrictUtils.SCHOOL_ID;
        String name = DistrictUtils.STUDENT_NAME;
        Pageable pageable = DistrictUtils.PAGEABLE;
        Mockito.when(studentRepository.findAllBySchoolIdAndNameContainingOrderByName(schoolId, name, pageable)).thenReturn(List.of(student));

        List<Student> students = studentService.getBySchoolIdAndStudentNameOrderByStudentName(schoolId, name, pageable);

        Assertions.assertEquals(1, students.size());
        Assertions.assertEquals(student, students.getFirst());
        Assertions.assertEquals(schoolId, students.getFirst().getId());
        Assertions.assertEquals(name, students.getFirst().getName());
    }

}
