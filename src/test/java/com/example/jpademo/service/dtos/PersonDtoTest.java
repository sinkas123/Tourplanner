package com.example.jpademo.service.dtos;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class PersonDtoTest {

    @Test
    void testAllArgsConstructor() {
        PersonDto personDto = new PersonDto(0L, "name", "email", 1);
        assertEquals(personDto);
    }

    @Test
    void testNoArgsConstructor() {
        PersonDto personDto = new PersonDto();
        Assertions.assertInstanceOf(PersonDto.class, personDto);
    }

    @Test
    void testSetters() {
        PersonDto personDto = new PersonDto();
        personDto.setId(0L);
        personDto.setName("name");
        personDto.setEmail("email");
        personDto.setAge(1);
        assertEquals(personDto);
    }

    void assertEquals(PersonDto personDto) {
        Assertions.assertEquals(0L, personDto.getId());
        Assertions.assertEquals("name", personDto.getName());
        Assertions.assertEquals("email", personDto.getEmail());
        Assertions.assertEquals(1, personDto.getAge());
    }

}
