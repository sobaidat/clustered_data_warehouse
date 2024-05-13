package com.warehouse.clustereddata.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.warehouse.clustereddata.binding.FxDeal;
import com.warehouse.clustereddata.service.FxDealService;

@RestController
public class DealRestController {

	@Autowired
    private FxDealService dealService;
	
	@PostMapping("/deals")
    public ResponseEntity<String> processDeal(@RequestBody FxDeal deal) {
		FxDeal processedDeal = dealService.processDeal(deal);
        if (processedDeal != null) {
            return ResponseEntity.status(HttpStatus.CREATED).body("Deal processed successfully");
        } else {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Deal already exists");
        }
    }
	
	@GetMapping("/deals/{dealId}")
	public ResponseEntity<FxDeal> getDeal(@PathVariable("dealId") Long dealId) {
		FxDeal deal = dealService.getDealById(dealId);
		return new ResponseEntity<FxDeal>(deal, HttpStatus.OK);
	}
	
	@GetMapping("/deals")
	public ResponseEntity<List<FxDeal>> getAllDeals() {
		List<FxDeal> deals = dealService.getAllDeals();
		return new ResponseEntity<List<FxDeal>>(deals, HttpStatus.OK);
	}
}
