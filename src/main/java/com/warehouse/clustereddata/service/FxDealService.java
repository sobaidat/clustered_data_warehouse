package com.warehouse.clustereddata.service;

import java.util.List;

import com.warehouse.clustereddata.binding.FxDeal;

public interface FxDealService {
	 
	 FxDeal processDeal(FxDeal deal);
	 
	 FxDeal saveDeal(FxDeal deal);  
	 
	 FxDeal getDealById(Long id);
	 
	 List<FxDeal> getAllDeals();
	 
	 boolean isDealExists(String dealId);
}
