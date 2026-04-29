package com.shalion.district.student.dto;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import lombok.Data;

import java.io.IOException;
import java.io.Serial;
import java.io.Serializable;

@Data
public class StudentDto implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    private long id;
    private String name;
    private long schoolId;

    public static class StudentDtoSerializer extends StdSerializer<StudentDto> {

        public StudentDtoSerializer(Class<StudentDto> t) {
            super(t);
        }

        @Override
        public void serialize(StudentDto studentDto, JsonGenerator jgen, SerializerProvider provider) throws IOException {
            jgen.writeStartObject();
            jgen.writeNumberField("id", studentDto.id);
            jgen.writeStringField("itemName", studentDto.name);
            jgen.writeNumberField("schoolId", studentDto.schoolId);
            jgen.writeEndObject();
        }

    }

}
