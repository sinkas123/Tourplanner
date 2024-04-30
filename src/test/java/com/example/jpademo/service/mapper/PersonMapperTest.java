package com.example.jpademo.service.mapper;

import com.example.jpademo.persistence.entities.PersonEntity;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class PersonMapperTest {

    @InjectMocks
    PersonMapper personMapper;

    @Test
    void testMapToDto() {
        PersonEntity personEntity = Mockito.mock(PersonEntity.class);
        Assertions.assertNotNull(personMapper.mapToDto(personEntity));
    }

}
