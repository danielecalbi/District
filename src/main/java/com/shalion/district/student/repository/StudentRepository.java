package com.shalion.district.student.repository;

import com.shalion.district.student.domain.Student;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface StudentRepository extends JpaRepository<Student, Long> {

    Optional<Student> findById(long id);
    void deleteById(long id);
    List<Student> findAllBySchoolIdByOrderById(long schoolId);
    List<Student> findAllBySchoolIdAndByNameContainingByOrderByName(long schoolId, String name);
}
