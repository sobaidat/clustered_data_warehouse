package com.warehouse.clustereddata.service;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.warehouse.clustereddata.binding.FxDeal;
import com.warehouse.clustereddata.repo.FxDealRepository;

@Service("dealService")
public class FxDealServiceImpl implements FxDealService {

	private static final Logger logger = LoggerFactory.getLogger(FxDealServiceImpl.class);

	@Autowired
	private FxDealRepository dealRepository;
	   
	@Override
	public FxDeal processDeal(FxDeal deal) {
		if(isDealExists(deal.getDealId())) {
			logger.warn("Deal with ID {} already exists. Skipping processing.", deal.getDealId());
			return null;
		}

		logger.info("Processing deal with ID {}", deal.getDealId());
		return saveDeal(deal);
	}

	@Override
	public FxDeal saveDeal(FxDeal deal) {
		logger.info("Saving deal with ID {}", deal.getDealId());
		return dealRepository.save(deal);
	}

	@Override
	public FxDeal getDealById(Long id) {
		logger.info("Retrieving deal with ID {}", id);

		Optional<FxDeal> deal = dealRepository.findById(id);
		if(deal.isPresent()) {
			return deal.get();
		}

		logger.warn("Deal with ID {} not found", id);
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
