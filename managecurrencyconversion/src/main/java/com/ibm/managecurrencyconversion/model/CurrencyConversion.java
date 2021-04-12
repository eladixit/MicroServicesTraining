package com.ibm.managecurrencyconversion.model;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Data;

@Entity
@Data
public class CurrencyConversion {
	
	@Id
	private String countryCd ;
	
	private double conversionFac;
	
	public String getCountryCd() {
		return countryCd;
	}
	public void setCountryCd(String countryCd) {
		this.countryCd = countryCd;
	}
	public double getConversionFac() {
		return conversionFac;
	}
	public void setConversionFac(double conversionFac) {
		this.conversionFac = conversionFac;
	}
	
	
}
