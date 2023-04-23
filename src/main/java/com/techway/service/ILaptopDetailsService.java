package com.techway.service;

import java.util.List;

import com.techway.dto.request.LaptopDetailsRequest;
import com.techway.entity.LaptopDetail;

public interface ILaptopDetailsService {

	List<LaptopDetail> findAll();

	LaptopDetail findById(long id);

	LaptopDetail save(long id, LaptopDetailsRequest request);

	LaptopDetail update(long id, LaptopDetailsRequest request);

}
