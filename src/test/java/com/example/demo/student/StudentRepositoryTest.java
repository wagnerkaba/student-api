package com.example.demo.student;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalDate;
import java.time.Month;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@DataJpaTest //Esta anotação é necessária para utilizar o H2 database configurado para testes
class StudentRepositoryTest {

    @Autowired
    private StudentRepository underTest;

    @AfterEach
    void tearDown() {
        // Após cada teste, os dados do banco de dados são apagados
        underTest.deleteAll();
    }

    @Test
    void ItShouldCheckIfEmailExists() {
        //given
        String email = "carlos@gmail.com";
        Student student = new Student(
                "Carlos",
                email,
                LocalDate.of(2000, Month.JANUARY, 5),
                Gender.MALE
                );
        underTest.save(student);

        //when
        Boolean expectedEmail = underTest.checkIfEmailExists(email);

        //then
        assertThat(expectedEmail).isTrue();
    }

    @Test
    void ItShouldCheckIfStudentEmailDoesNotExists() {
        //given
        String email = "carlos@gmail.com";

        //when
        Boolean expectedEmail = underTest.checkIfEmailExists(email);

        //then
        assertThat(expectedEmail).isFalse();
    }
}