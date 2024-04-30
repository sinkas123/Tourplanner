package com.example.jpademo.api;

import com.example.jpademo.service.PersonService;
import com.example.jpademo.service.dtos.PersonDto;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class PersonApiTest {

    @InjectMocks
    PersonApi personApi;

    @Mock
    PersonService personService;

    @Test
    void testConstructor() {
        PersonApi personApi = new PersonApi(personService);
        Assertions.assertInstanceOf(PersonApi.class, personApi);
    }

    @Test
    void testGetAllPersons() {
        Assertions.assertTrue(personApi.getAllPersons().isEmpty());
    }

    @Test
    void testGetPersonByName() {
        Assertions.assertTrue(personApi.getPersonByName("name").isEmpty());
    }

    @Test
    void testInsertNewPerson() {
        PersonDto personDto = Mockito.mock(PersonDto.class);
        personApi.insertNewPerson(personDto);
        Mockito.verify(personService).saveNewPerson(personDto);
    }

}
