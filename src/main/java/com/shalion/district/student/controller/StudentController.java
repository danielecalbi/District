package com.shalion.district.student.controller;

import com.shalion.district.student.domain.Student;
import com.shalion.district.student.dto.StudentDto;
import com.shalion.district.student.mapper.StudentMapper;
import com.shalion.district.student.service.StudentService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/student")
@AllArgsConstructor
public class StudentController {

    private static final String PAGE_SIZE = "1";

    private final StudentMapper studentMapper;
    private final StudentService studentService;

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public void create(@RequestParam StudentDto studentDto) {
        studentService.create(studentMapper.map(studentDto));
    }

    @GetMapping(path = "/{id}")
    public StudentDto read(@PathVariable long id) {
        return studentMapper.map(studentService.get(id));
    }

    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public StudentDto update(@RequestParam StudentDto studentDto) {
        return studentMapper.map(studentService.update(studentMapper.map(studentDto)));
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable long id) {
        studentService.delete(id);
    }

    @GetMapping(path = "/{schoolId}/{name}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Page<StudentDto> read(@PathVariable long schoolId, @PathVariable String name,
                                 @RequestParam int page, @RequestParam(defaultValue = PAGE_SIZE) int size) {
        List<Student> students = studentService.getBySchoolIdAndStudentNameOrderByStudentName(schoolId, name);
        List<StudentDto> studentDtos = new ArrayList<>();
        students.forEach(student -> studentDtos.add(studentMapper.map(student)));
        return new PageImpl<>(studentDtos, PageRequest.of(page, size), studentDtos.size());
    }

}
