package com.ibm.managecurrencyconversion.controller;

import java.util.List;
import java.util.Optional;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ibm.managecurrencyconversion.model.CurrencyConversion;
import com.ibm.managecurrencyconversion.repository.CurrencyConversionRepository;

@RestController
@RequestMapping("/currencyConversion")
public class CurrencyConversionController {

	@Autowired
	CurrencyConversionRepository respository;

	@PostConstruct
	private void init() {
		//Currency Code 
		//IN001     2
		//US002     10
		CurrencyConversion currencyConversion = new CurrencyConversion();
		currencyConversion.setConversionFac(2.0);
		currencyConversion.setCountryCd("INDIA");
		respository.save(currencyConversion);

	}

	@GetMapping("/all")
	public ResponseEntity<List<CurrencyConversion>> getAll(){
		return ResponseEntity.ok(respository.findAll());
	}

	@PostMapping("/add")
	public ResponseEntity<ConversionResponse> addConversionFactor(@RequestBody CurrencyConversion cc ) {

		ConversionResponse response = new ConversionResponse();
		try {
			respository.save(cc);
			response.setMessage("Successfully added country conversion factor");
			response.setStatus("200");
		} catch(Exception e ) {
			response.setMessage("Error adding country conversion factor");
			response.setStatus(e.getMessage());
		}
		return ResponseEntity.ok(response);
	}

	@GetMapping("/getByCode/{countryCd}")
	public ResponseEntity<Double> getConversionFactor(@PathVariable String  countryCd) {
		Optional<CurrencyConversion> cc = respository.findById(countryCd);
		return cc.isPresent()?ResponseEntity.ok(cc.get().getConversionFac()):ResponseEntity.ok(0.0);
	}

	@PostMapping("/update")
	public ResponseEntity<CurrencyConversion> updateConversionFactor(@RequestBody CurrencyConversion cc ) {

		Optional<CurrencyConversion> currentConversionObj = respository.findById(cc.getCountryCd());
		if(currentConversionObj.isPresent()) {
			CurrencyConversion currency = currentConversionObj.get();
			currency.setConversionFac(cc.getConversionFac());
			return ResponseEntity.ok(respository.save(currency));
		}
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}

}
