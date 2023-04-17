package com.techway.service;

import java.util.List;

import com.techway.model.dto.request.LaptopDetailsRequest;
import com.techway.model.dto.response.LaptopDetailsResponse;

public interface ILaptopDetailsService {

	List<LaptopDetailsResponse> findAll();

	LaptopDetailsResponse findById(long id);

	LaptopDetailsResponse save(long id, LaptopDetailsRequest request);

	LaptopDetailsResponse update(long id, LaptopDetailsRequest request);

}
