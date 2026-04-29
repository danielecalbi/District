package com.shalion.district.school.controller;

import com.shalion.district.school.domain.School;
import com.shalion.district.school.dto.SchoolDto;
import com.shalion.district.school.mapper.SchoolMapper;
import com.shalion.district.school.service.SchoolService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
    import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
public class SchoolController {

    private static final String PAGE_SIZE = "1";

    private final SchoolMapper schoolMapper;
    private final SchoolService schoolService;

    @PostMapping(path = "/school", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void create(@RequestBody SchoolDto schoolDto) {
        schoolService.create(schoolMapper.map(schoolDto));
    }

    @GetMapping(path = "/school/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public SchoolDto read(@PathVariable long id) {
        return schoolService.getByIdWithEnlistedStudents(id);
    }

    @PutMapping(path = "/school", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public SchoolDto update(@RequestBody SchoolDto schoolDto) {
        return schoolMapper.map(schoolService.update(schoolMapper.map(schoolDto)));
    }

    @DeleteMapping("/school/{id}")
    public void delete(@PathVariable long id) {
        schoolService.delete(id);
    }

    @GetMapping(path = "/schools/{name}",  produces = MediaType.APPLICATION_JSON_VALUE)
    public Page<School> read(@PathVariable String name, @RequestParam int page, @RequestParam(defaultValue = PAGE_SIZE) int size) {
        Pageable pageable = PageRequest.of(page, size);
        List<School> schools = schoolService.getByName(name, pageable);
        return new PageImpl<>(schools, pageable,  schoolService.getByName(name,  PageRequest.of(0, Integer.MAX_VALUE)).size());
    }

}

