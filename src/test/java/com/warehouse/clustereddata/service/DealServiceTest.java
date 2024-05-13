package com.warehouse.clustereddata.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.warehouse.clustereddata.binding.FxDeal;
import com.warehouse.clustereddata.repo.FxDealRepository;

@ExtendWith(MockitoExtension.class)
public class DealServiceTest {

    @Mock
    private FxDealRepository dealRepository;

    @InjectMocks
    private FxDealService dealService = new FxDealServiceImpl();
    
    @Test
    public void testProcessDeal_NewDeal_Success() {
        FxDeal deal = new FxDeal();
        deal.setDealId("DEAL123");
        when(dealRepository.findByDealId("DEAL123")).thenReturn(null);
        when(dealRepository.save(deal)).thenReturn(deal);
        FxDeal savedDeal = dealService.processDeal(deal);
        assertNotNull(savedDeal);
        assertEquals(deal, savedDeal);
    }

    @Test
    public void testProcessDeal_ExistingDeal_Failure() {
        FxDeal existingDeal = new FxDeal();
        existingDeal.setDealId("DEAL123");
        when(dealRepository.findByDealId("DEAL123")).thenReturn(existingDeal);
        FxDeal deal = new FxDeal();
        deal.setDealId("DEAL123");
        FxDeal savedDeal = dealService.processDeal(deal);
        assertNull(savedDeal);
    }

    @Test
    public void testSaveDeal() {
        FxDeal validDeal = new FxDeal();
        dealService.saveDeal(validDeal);
        verify(dealRepository, atLeastOnce()).save(eq(validDeal));
    }
    
    @Test
    public void testGetAllDeals() {
        List<FxDeal> deals = new ArrayList<>();
        deals.add(new FxDeal());
        deals.add(new FxDeal());
        when(dealRepository.findAll()).thenReturn(deals);
        List<FxDeal> retrievedDeals = dealService.getAllDeals();
        assertNotNull(retrievedDeals);
        assertEquals(deals.size(), retrievedDeals.size());
    }

    @Test
    public void testGetDealById_DealExists() {
        FxDeal deal = new FxDeal();
        deal.setId(1L);
        Optional<FxDeal> optionalDeal = Optional.of(deal);
        when(dealRepository.findById(1L)).thenReturn(optionalDeal);
        FxDeal retrievedDeal = dealService.getDealById(1L);
        assertNotNull(retrievedDeal);
        assertEquals(deal, retrievedDeal);
    }

    @Test
    public void testGetDealById_DealNotExists() {
        when(dealRepository.findById(1L)).thenReturn(Optional.empty());
        FxDeal retrievedDeal = dealService.getDealById(1L);
        assertNull(retrievedDeal);
    }

    @Test
    public void testIsDealExists_DealExists() {
        when(dealRepository.findByDealId("DEAL123")).thenReturn(new FxDeal());
        boolean exists = dealService.isDealExists("DEAL123");
        assertTrue(exists);
    }

    @Test
    public void testIsDealExists_DealNotExists() {
        when(dealRepository.findByDealId("DEAL123")).thenReturn(null);
        boolean exists = dealService.isDealExists("DEAL123");
        assertFalse(exists);
    }
}
