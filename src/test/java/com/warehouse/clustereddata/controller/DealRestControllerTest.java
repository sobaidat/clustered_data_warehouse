package com.warehouse.clustereddata.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.warehouse.clustereddata.binding.FxDeal;
import com.warehouse.clustereddata.service.FxDealService;

public class DealRestControllerTest {

    @Mock
    private FxDealService dealService;

    @InjectMocks
    private DealRestController dealRestController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testProcessDeal_Success() {
        FxDeal deal = new FxDeal();
        when(dealService.processDeal(any())).thenReturn(deal);

        ResponseEntity<String> response = dealRestController.processDeal(deal);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals("Deal processed successfully", response.getBody());
    }

    @Test
    public void testProcessDeal_Conflict() {
        when(dealService.processDeal(any())).thenReturn(null);

        ResponseEntity<String> response = dealRestController.processDeal(new FxDeal());

        assertEquals(HttpStatus.CONFLICT, response.getStatusCode());
        assertEquals("Deal already exists", response.getBody());
    }

    @Test
    public void testGetDealById() {
        FxDeal deal = new FxDeal();
        when(dealService.getDealById(1L)).thenReturn(deal);

        ResponseEntity<FxDeal> response = dealRestController.getDeal(1L);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(deal, response.getBody());
    }

    @Test
    public void testGetAllDeals() {
        List<FxDeal> deals = new ArrayList<>();
        when(dealService.getAllDeals()).thenReturn(deals);

        ResponseEntity<List<FxDeal>> response = dealRestController.getAllDeals();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(deals, response.getBody());
    }
}

