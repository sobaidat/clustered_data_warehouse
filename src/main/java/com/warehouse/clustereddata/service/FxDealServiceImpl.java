package com.warehouse.clustereddata.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.warehouse.clustereddata.binding.FxDeal;
import com.warehouse.clustereddata.repo.FxDealRepository;

@Service("dealService")
public class FxDealServiceImpl implements FxDealService {

	@Autowired
	private FxDealRepository dealRepository;
	   
	@Override
	public FxDeal processDeal(FxDeal deal) {
		if(isDealExists(deal.getDealId())) {
			return null;
		}
		
		return saveDeal(deal);
	}

	@Override
	public FxDeal saveDeal(FxDeal deal) {
		return dealRepository.save(deal);
	}

	@Override
	public FxDeal getDealById(Long id) {
		Optional<FxDeal> deal = dealRepository.findById(id);
		if(deal.isPresent()) {
			return deal.get();
		}		
		return null;
	}

	@Override
	public List<FxDeal> getAllDeals() {
		return dealRepository.findAll();
	}

	@Override
	public boolean isDealExists(String dealId) {
		return dealRepository.findByDealId(dealId) != null;
	}
	
}
