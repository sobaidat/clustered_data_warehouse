package com.warehouse.clustereddata.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import com.warehouse.clustereddata.binding.FxDeal;

public interface FxDealRepository extends JpaRepository<FxDeal, Long> {
	 FxDeal findByDealId(String dealId);
}
