package com.techway.service.impl;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.techway.exception.ResourceNotFoundException;
import com.techway.model.dto.request.LaptopDetailsRequest;
import com.techway.model.entity.LaptopDetail;
import com.techway.model.entity.ScreenTech;
import com.techway.repository.LaptopDetailRepository;
import com.techway.repository.ProductRepository;
import com.techway.repository.ScreenTechRepository;
import com.techway.service.ILaptopDetailsService;

@Service
public class LaptopDetailsService implements ILaptopDetailsService{
	@Autowired
	LaptopDetailRepository laptopDetailRepository;

	@Autowired
	ScreenTechRepository screenTechRepository;
	@Autowired
	ProductRepository productRepository;
	
	@Override
	public List<LaptopDetail> findAll() {
		List<LaptopDetail> list = laptopDetailRepository.findAll();
//				.stream().map(o -> entityToResponse(o)).collect(Collectors.toList());
		return list;
	}
	
	@Override
	public LaptopDetail save(long productId, LaptopDetailsRequest request) {
		LaptopDetail laptopDetail = new LaptopDetail();
		request.setId(productId);
		LaptopDetail savedLaptopDetail = laptopDetailRepository.save(requestToEntity(request, laptopDetail));
		return savedLaptopDetail ;
	}
	
	private LaptopDetail requestToEntity(LaptopDetailsRequest request, LaptopDetail entity) {
		entity.setProduct(productRepository.findById(request.getId()).get());
		entity.setCpu(request.getCpu());
		entity.setCore(request.getCore());
		entity.setThread(request.getThread());
		entity.setCpuSpeed(request.getCpuSpeed());
		entity.setCpuMaxSpeed(request.getCpuMaxSpeed());
		entity.setCache(request.getCache());
		entity.setRam(request.getRam());
		entity.setType(request.getType());
		entity.setBusRAM(request.getBusRAM());
		entity.setMaxRAM(request.getMaxRAM());
		entity.setSsd(request.getSsd());
		entity.setScreenWidth(request.getScreenWidth());
		entity.setScreenResolution(request.getScreenResolution());
		entity.setHz(request.getHz());
		Set<ScreenTech> set = new HashSet<ScreenTech>();
		Set<Long> setIds = request.getScreenTechIds();
		setIds.stream().forEach(id -> set.add(screenTechRepository.findById(id).get()));
		entity.setScreenTechs(set);
		entity.setScreenCard(request.getScreenCard());
		entity.setSound(request.getSound());
		return entity;
	}
	
//	private LaptopDetail entityToResponse(LaptopDetail o) {
//		Product product = productService.findById(o.getId());		
//		LaptopDetail details = new LaptopDetail();
//		details.setProduct(product);
//		details.setProduct(o.getProduct());
//		details.setCpu(o.getCpu());
//		details.setCore(o.getCore());
//		details.setThread(o.getThread());
//		details.setCpuSpeed(o.getCpuSpeed());
//		details.setCpuMaxSpeed(o.getCpuMaxSpeed());
//		details.setCache(o.getCache());
//		details.setRam(o.getRam());
//		details.setType(o.getType());
//		details.setBusRAM(o.getBusRAM());
//		details.setMaxRAM(o.getMaxRAM());
//		details.setSsd(o.getSsd());
//		details.setScreenWidth(o.getScreenWidth());
//		details.setScreenResolution(o.getScreenResolution());
//		details.setHz(o.getHz());
//		Set<ScreenTech> set = new HashSet<ScreenTech>();
//		screenTechRepository.findScreenTechsByLaptopsId(o.getId()).forEach(
//				set::add
//				);
//		details.setScreenTechs(set);
//		details.setScreenCard(o.getScreenCard());
//		details.setSound(o.getSound());
//		
//		return details;
//	}
	@Override
	public LaptopDetail findById(long id) {
		LaptopDetail o = laptopDetailRepository.findById(id).orElseThrow(
				() -> new ResourceNotFoundException("Not found")
				);
		
		return o;
	}

	@Override
	public LaptopDetail update(long id, LaptopDetailsRequest request) {
		LaptopDetail laptopDetail = laptopDetailRepository.findById(id).orElseThrow(
				() -> new ResourceNotFoundException(
						String.format("Product with id %d not found", id))
				);
		LaptopDetail updatedLaptopDetail = laptopDetailRepository.save(requestToEntity(request, laptopDetail));
		return updatedLaptopDetail;
	}
	
}
