package com.ibm.managecurrencyconversion.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ibm.managecurrencyconversion.model.CurrencyConversion;

/**
 * Repository for currency conversion 
 * @author ElaDixit
 *
 */
public interface CurrencyConversionRepository extends JpaRepository<CurrencyConversion, String> {

	//CurrencyConversion findByCountryCode(String countryCd);
}
