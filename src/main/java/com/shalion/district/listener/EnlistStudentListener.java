package com.shalion.district.listener;

import com.shalion.district.enums.Process;
import com.shalion.district.student.dto.StudentDto;
import com.shalion.district.student.service.StudentService;
import lombok.AllArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@AllArgsConstructor
public class EnlistStudentListener {

    private StudentService studentService;

    @KafkaListener(topics = "${spring.kafka.topicName}")
    public Process listen(@Payload List<StudentDto> studentsDto,
                          @Header(KafkaHeaders.PARTITION) List<Integer> partitions,
                          @Header(KafkaHeaders.OFFSET) List<Long> offsets) {
        try {
            studentsDto.forEach(student -> studentService.getSchool(student.getSchoolId()));
            return Process.OK;
        } catch (Exception e) {
            return Process.FAILED;
        }
    }

}
