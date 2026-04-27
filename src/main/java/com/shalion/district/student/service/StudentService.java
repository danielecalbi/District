package com.shalion.district.student.service;

import com.shalion.district.exception.ConflictException;
import com.shalion.district.school.domain.School;
import com.shalion.district.school.service.SchoolService;
import com.shalion.district.student.domain.Student;
import com.shalion.district.student.repository.StudentRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class StudentService {

    private final SchoolService schoolService;
    private final StudentRepository studentRepository;

    public void create(Student student) {
        if (schoolService.get(student.getSchool().getId()).getMaximumCapacity() >= School.MAX_MAXIMUM_CAPACITY) {
            throw new ConflictException("Maximum capacity exceeded for school with id '" + student.getSchool().getId() + "'! Student '" + student.getName() + "' has been not created!");
        }
        studentRepository.save(student);
    }

    public Student get(long id) {
        return studentRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Student with id '" + id + "' not found!"));
    }

    public Student update(Student student) {
        if (studentRepository.findById(student.getId()).isPresent()) {
            return studentRepository.save(student);
        }
        throw new EntityNotFoundException("Student with id '" + student.getId() + "' not found!");
    }

    public void delete(long id) {
        studentRepository.deleteById(id);
    }

    public List<Student> getBySchoolIdOrderById(long schoolId) {
        return studentRepository.findAllBySchoolIdByOrderById(schoolId);
    }

    public List<Student> getBySchoolIdAndStudentNameOrderByStudentName(long schoolId, String name) {
        return studentRepository.findAllBySchoolIdAndByNameContainingByOrderByName(schoolId, name);
    }

}
