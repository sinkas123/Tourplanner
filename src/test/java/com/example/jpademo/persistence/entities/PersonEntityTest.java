package com.example.jpademo.persistence.entities;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class PersonEntityTest {

    @Test
    void testAllArgsConstructor() {
        PersonEntity personEntity = new PersonEntity(0L, "name", "email", 1);
        assertEquals(personEntity);
    }

    @Test
    void testNoArgsConstructor() {
        PersonEntity personEntity = new PersonEntity();
        Assertions.assertInstanceOf(PersonEntity.class, personEntity);
    }

    @Test
    void testSetters() {
        PersonEntity personEntity = new PersonEntity();
        personEntity.setId(0L);
        personEntity.setName("name");
        personEntity.setEmail("email");
        personEntity.setAge(1);
        assertEquals(personEntity);
    }

    void assertEquals(PersonEntity personEntity) {
        Assertions.assertEquals(0L, personEntity.getId());
        Assertions.assertEquals("name", personEntity.getName());
        Assertions.assertEquals("email", personEntity.getEmail());
        Assertions.assertEquals(1, personEntity.getAge());
    }

}
