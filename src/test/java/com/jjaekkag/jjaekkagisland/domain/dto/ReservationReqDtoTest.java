package com.jjaekkag.jjaekkagisland.domain.dto;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author jeongheekim
 * @date 12/7/23
 */
class ReservationReqDtoTest {
    private static Validator validator;
    @BeforeAll
    public static void setUpValidator() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();

    }

    @Test
    @DisplayName("dto not null value 위반 개수 테스트")
    void dtoNotNullTest(){
        assertAll(
                () -> assertViolationCount(new ReservationReqDto(null, null, 3),2),
                () -> assertViolationCount(new ReservationReqDto(null, null, null),3)
        );
    }

    static <T> void assertViolationCount(T obj, int expectedViolationSize) {

        Set<ConstraintViolation<T>> violations = validator.validate(obj);
        assertEquals(expectedViolationSize, violations.size());
    }

}