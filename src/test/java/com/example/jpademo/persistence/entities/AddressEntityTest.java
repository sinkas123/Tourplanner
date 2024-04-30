package com.example.jpademo.persistence.entities;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class AddressEntityTest {

    @Mock
    PersonEntity person;

    @Test
    void testAllArgsConstructor() {
        AddressEntity addressEntity = new AddressEntity(0L, 1, "city", "street", person);
        assertEquals(addressEntity);
    }

    @Test
    void testNoArgsConstructor() {
        AddressEntity addressEntity = new AddressEntity();
        Assertions.assertInstanceOf(AddressEntity.class, addressEntity);
    }

    @Test
    void testSetters() {
        AddressEntity addressEntity = new AddressEntity();
        addressEntity.setId(0L);
        addressEntity.setPostcode(1);
        addressEntity.setCity("city");
        addressEntity.setStreet("street");
        addressEntity.setPerson(person);
        assertEquals(addressEntity);
    }

    void assertEquals(AddressEntity addressEntity) {
        Assertions.assertEquals(0L, addressEntity.getId());
        Assertions.assertEquals(1, addressEntity.getPostcode());
        Assertions.assertEquals("city", addressEntity.getCity());
        Assertions.assertEquals("street", addressEntity.getStreet());
        Assertions.assertEquals(person, addressEntity.getPerson());
    }

}
