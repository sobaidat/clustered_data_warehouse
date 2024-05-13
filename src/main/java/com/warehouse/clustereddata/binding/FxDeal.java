package com.warehouse.clustereddata.binding;

import com.warehouse.clustereddata.validator.ValidAmount;
import com.warehouse.clustereddata.validator.ValidCurrencyCode;
import com.warehouse.clustereddata.validator.ValidDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
@Entity
public class FxDeal {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	
	@NotBlank
	private String dealId;
	
	@ValidCurrencyCode(message = "From currency code is invalid")
    private String fromCurrency;
	
	@ValidCurrencyCode(message = "To currency code is invalid")
    private String toCurrency;
	
	@ValidDateTime
    private String timestamp;
	
	@NotNull
	@ValidAmount(minAmount = 0, maxAmount = 50, message = "Amount must be positive and less than or equal 50")
    private double amount;
    
    public Long getId() {
		return id;
	}

	public String getFromCurrency() {
		return fromCurrency;
	}

	public String getToCurrency() {
		return toCurrency;
	}

	public String getTimestamp() {
		return timestamp;
	}

	public double getAmount() {
		return amount;
	}

	public String getDealId() {
		return dealId;
	}
}
