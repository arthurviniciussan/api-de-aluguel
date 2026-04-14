package com.arthur.rentalapi.repository;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import com.arthur.rentalapi.entity.RentalType;

@SpringBootTest
@TestPropertySource(locations = "classpath:application-test.properties")
class RentalTypeRepositoryTest {

    @Autowired
    private RentalTypeRepository rentalTypeRepository;

    @Test
    void testSaveAndFindById() {
        // Given
        RentalType rentalType = new RentalType();
        rentalType.setName("Car Rental");
        rentalType.setDescription("Daily car rental service");

        // When
        RentalType savedRentalType = rentalTypeRepository.save(rentalType);

        // Then
        assertThat(savedRentalType.getId()).isNotNull();
        assertThat(savedRentalType.getName()).isEqualTo("Car Rental");
        assertThat(savedRentalType.getDescription()).isEqualTo("Daily car rental service");

        Optional<RentalType> foundRentalType = rentalTypeRepository.findById(savedRentalType.getId());
        assertThat(foundRentalType).isPresent();
        assertThat(foundRentalType.get().getName()).isEqualTo("Car Rental");
    }

    @Test
    void testFindAll() {
        // Given
        RentalType rentalType1 = new RentalType();
        rentalType1.setName("Bike Rental");
        rentalType1.setDescription("Hourly bike rental");

        RentalType rentalType2 = new RentalType();
        rentalType2.setName("Boat Rental");
        rentalType2.setDescription("Weekly boat rental");

        rentalTypeRepository.save(rentalType1);
        rentalTypeRepository.save(rentalType2);

        // When
        List<RentalType> rentalTypes = rentalTypeRepository.findAll();

        // Then
        assertThat(rentalTypes).hasSizeGreaterThanOrEqualTo(2);
    }

    @Test
    void testFindByNameContaining() {
        // Given
        RentalType rentalType = new RentalType();
        rentalType.setName("Luxury Car Rental");
        rentalType.setDescription("Premium car rental");

        rentalTypeRepository.save(rentalType);

        // When
        List<RentalType> rentalTypes = rentalTypeRepository.findByNameContaining("Car");

        // Then
        assertThat(rentalTypes).isNotEmpty();
        assertThat(rentalTypes.get(0).getName()).isEqualTo("Luxury Car Rental");
    }
}