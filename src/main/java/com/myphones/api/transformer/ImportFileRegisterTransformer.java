package com.myphones.api.transformer;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.myphones.api.model.dto.ImportFileRegisterDTO;
import com.myphones.api.model.entity.ImportFileRegister;


@Component
public class ImportFileRegisterTransformer {

	@Autowired
	private ModelMapper modelMapper;

	public ImportFileRegisterDTO convertToDto(ImportFileRegister importFileRegister) {
		return modelMapper.map(importFileRegister, ImportFileRegisterDTO.class);
	}

	public ImportFileRegister convertToEntity(ImportFileRegisterDTO importFileRegisterDTO) {
		return modelMapper.map(importFileRegisterDTO, ImportFileRegister.class);
	}
}
