package com.shalion.district.service;

import com.shalion.district.school.domain.School;
import com.shalion.district.school.repository.SchoolRepository;
import com.shalion.district.student.domain.Student;
import com.shalion.district.student.repository.StudentRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class DistrictService {

    protected final SchoolRepository schoolRepository;
    protected final StudentRepository studentRepository;

    public School getSchool(long id) {
        return schoolRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("School with id '" + id + "' not found!"));
    }

    public List<Student> getBySchoolIdOrderById(long schoolId) {
        return studentRepository.findAllBySchoolIdByOrderById(schoolId);
    }

}
