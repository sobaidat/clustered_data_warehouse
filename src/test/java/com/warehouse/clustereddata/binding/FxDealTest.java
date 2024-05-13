package com.warehouse.clustereddata.binding;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Set;

import org.junit.jupiter.api.Test;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;

public class FxDealTest {

    @Test
    public void testFxDealMethods() {
        FxDeal deal1 = new FxDeal();
        deal1.setId(1L);
        deal1.setDealId("DEAL123");
        deal1.setFromCurrency("USD");
        deal1.setToCurrency("EUR");
        deal1.setTimestamp("2024-05-19T12:00:00");
        deal1.setAmount(25.0);

        FxDeal deal2 = new FxDeal();
        deal2.setId(1L);
        deal2.setDealId("DEAL123");
        deal2.setFromCurrency("USD");
        deal2.setToCurrency("EUR");
        deal2.setTimestamp("2024-05-19T12:00:00");
        deal2.setAmount(25.0);

        assertTrue(deal1.equals(deal2));
        assertTrue(deal2.equals(deal1));

        assertEquals(deal1.hashCode(), deal2.hashCode());

        assertNotNull(deal1.toString());

        assertEquals(1L, deal1.getId());

        assertTrue(deal1.canEqual(deal2));
        assertTrue(deal2.canEqual(deal1));
    }

    @Test
    public void testValidDeal() {
        FxDeal deal = new FxDeal();
        deal.setDealId("DEAL123");
        deal.setFromCurrency("USD");
        deal.setToCurrency("EUR");
        deal.setTimestamp("2024-05-19T12:00:00");
        deal.setAmount(25.0);

        assertAll("valid deal",
                () -> assertEquals("DEAL123", deal.getDealId()),
                () -> assertEquals("USD", deal.getFromCurrency()),
                () -> assertEquals("EUR", deal.getToCurrency()),
                () -> assertEquals("2024-05-19T12:00:00", deal.getTimestamp()),
                () -> assertEquals(25.0, deal.getAmount())
        );
    }

    @Test
    public void testInvalidDealId() {
        FxDeal deal = new FxDeal();
        deal.setDealId("");
        assertThrows(Exception.class, () -> validate(deal));
    }

    @Test
    public void testInvalidFromCurrency() {
        FxDeal deal = new FxDeal();
        deal.setDealId("DEAL123");
        deal.setFromCurrency("INVALID");

        assertThrows(Exception.class, () -> validate(deal));
    }

    @Test
    public void testInvalidToCurrency() {
        FxDeal deal = new FxDeal();
        deal.setDealId("DEAL123");
        deal.setFromCurrency("USD");
        deal.setToCurrency("INVALID");

        assertThrows(Exception.class, () -> validate(deal));
    }

    @Test
    public void testInvalidTimestamp() {
        FxDeal deal = new FxDeal();
        deal.setDealId("DEAL123");
        deal.setFromCurrency("USD");
        deal.setToCurrency("EUR");
        deal.setTimestamp("INVALID");

        assertThrows(Exception.class, () -> validate(deal));
    }

    @Test
    public void testInvalidAmount() {
        FxDeal deal = new FxDeal();
        deal.setDealId("DEAL123");
        deal.setFromCurrency("USD");
        deal.setToCurrency("EUR");
        deal.setTimestamp("2024-05-19T12:00:00");
        deal.setAmount(60.0);

        assertThrows(Exception.class, () -> validate(deal));
    }

    private void validate(FxDeal deal) throws Exception {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();

        Set<ConstraintViolation<FxDeal>> violations = validator.validate(deal);
        if (!violations.isEmpty()) {
            throw new Exception("Validation failed: " + violations);
        }
    }
}
