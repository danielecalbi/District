package com.shalion.district.school.repository;

import com.shalion.district.school.domain.School;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface SchoolRepository extends JpaRepository<School, Long> {

    Optional<School> findById(long id);
    void deleteById(long id);
    List<School> findByNameContaining(String name);
    Optional<School> findByName(String name);
}
