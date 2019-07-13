package com.myphones.api.transformer;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.myphones.api.model.dto.MobileNumberDTO;
import com.myphones.api.model.entity.MobileNumber;


@Component
public class MobileNumberTransformer {

	@Autowired
	private ModelMapper modelMapper;

	public MobileNumberDTO convertToDto(MobileNumber mobileNumber) {
		return modelMapper.map(mobileNumber, MobileNumberDTO.class);
	}

	public MobileNumber convertToEntity(MobileNumberDTO mobileNumberDTO) {
		return modelMapper.map(mobileNumberDTO, MobileNumber.class);
	}
}
