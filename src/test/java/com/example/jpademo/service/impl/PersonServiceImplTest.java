package com.example.jpademo.service.impl;

import com.example.jpademo.persistence.entities.PersonEntity;
import com.example.jpademo.persistence.repositories.PersonRepository;
import com.example.jpademo.service.PersonService;
import com.example.jpademo.service.dtos.PersonDto;
import com.example.jpademo.service.mapper.PersonMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class PersonServiceImplTest {

    @InjectMocks
    PersonServiceImpl personService;

    @Mock
    PersonRepository personRepository;

    @Mock
    PersonMapper personMapper;

    @Test
    void testConstructor() {
        PersonService personService = new PersonServiceImpl(personRepository, personMapper);
        Assertions.assertInstanceOf(PersonService.class, personService);
    }

    @Test
    void testSaveNewPerson() {
        PersonDto personDto = Mockito.mock(PersonDto.class);
        personService.saveNewPerson(personDto);
        Mockito.verify(personRepository).save(ArgumentMatchers.any(PersonEntity.class));
    }

    @Test
    void testGetAllPersons() {
        Assertions.assertTrue(personService.getAllPersons().isEmpty());
    }

    @Test
    void testGetPersonByName() {
        Assertions.assertTrue(personService.getPersonByName("name").isEmpty());
    }

}
