package com.shalion.district.school.service;

import com.shalion.district.exception.ConflictException;
import com.shalion.district.school.domain.School;
import com.shalion.district.school.dto.SchoolDto;
import com.shalion.district.school.mapper.SchoolMapper;
import com.shalion.district.school.repository.SchoolRepository;
import com.shalion.district.student.domain.Student;
import com.shalion.district.student.service.StudentService;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class SchoolService {

    private final SchoolMapper schoolMapper;
    private final SchoolRepository schoolRepository;
    private final StudentService studentService;

    public void create(School school) {
        if (this.getSchoolByName(school.getName()).isPresent()) {
            throw new ConflictException("School '" + school.getName() + "' already exists!");
        }
        schoolRepository.save(school);
    }

    public School get(long id) {
        return schoolRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("School with id '" + id + "' not found!"));
    }

    public School update(School school) {
       if (schoolRepository.findById(school.getId()).isPresent()) {
           return schoolRepository.save(school);
       }
       throw new EntityNotFoundException("School with id '" + school.getId() + "' not found!");
    }

    public void delete(long id) {
        schoolRepository.deleteById(id);
    }

    public List<School> getByName(String name) {
        return schoolRepository.findByNameContaining(name);
    }

    public SchoolDto getByIdWithEnlistedStudents(long id) {
        List<Student> students = studentService.getBySchoolIdOrderById(id);
        return schoolMapper.map(get(id), students);
    }

    private Optional<School> getSchoolByName(String name) {
        return schoolRepository.findByName(name);
    }

}
