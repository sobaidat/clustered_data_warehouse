package com.warehouse.clustereddata.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import com.warehouse.clustereddata.binding.FxDeal;
import com.warehouse.clustereddata.repo.FxDealRepository;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Matchers.anyString;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class DealServiceTest {

	@Mock
    private FxDealRepository dealRepository;

	@InjectMocks
    private FxDealService dealService = new FxDealServiceImpl();
	
	@Test
    public void testSaveDeal() {

        FxDeal validDeal = new FxDeal();

        dealService.saveDeal(validDeal);

        verify(dealRepository, atLeastOnce()).save(eq(validDeal));
    }
	
	@Test
    public void testFIndValidDealByDealId() {

		FxDeal testDeal = new FxDeal();

        when(dealRepository.findByDealId(anyString())).thenReturn(testDeal);

        FxDeal returnedDeal = dealService.getDealById(1l);

        verify(dealRepository, atLeastOnce()).findByDealId(eq(testDeal.getDealId()));
        assertThat(returnedDeal, is(testDeal));
    }
}
