package com.shalion.district.student.repository;

import com.shalion.district.student.domain.Student;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface StudentRepository extends JpaRepository<Student, Long> {

    Optional<Student> findById(long id);
    void deleteById(long id);
    List<Student> findAllBySchoolIdOrderById(long schoolId);
    List<Student> findAllBySchoolIdAndNameContainingOrderByName(long schoolId, String name);
}
