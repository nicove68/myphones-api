package com.myphones.api.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.myphones.api.exception.ResourceNotFoundException;
import com.myphones.api.model.dto.MobileNumberDTO;
import com.myphones.api.model.entity.MobileNumber;
import com.myphones.api.repository.MobileNumberRepository;
import com.myphones.api.transformer.MobileNumberTransformer;

@Service
public class MobileNumberService {

  private static Logger LOGGER = LoggerFactory.getLogger(MobileNumberService.class);
  private MobileNumberRepository mobileNumberRepository;
  private MobileNumberTransformer mobileNumberTransformer;

  @Autowired
  public MobileNumberService(MobileNumberRepository mobileNumberRepository, MobileNumberTransformer mobileNumberTransformer) {
    this.mobileNumberRepository = mobileNumberRepository;
    this.mobileNumberTransformer = mobileNumberTransformer;
  }

  public List<MobileNumberDTO> getAllMobileNumbers() {
    LOGGER.info("Getting all mobile numbers...");

    List<MobileNumber> mobileNumberList = mobileNumberRepository.findAll();

    return mobileNumberList.stream()
        .map(mobileNumberTransformer::convertToDto)
        .collect(Collectors.toList());
  }

  public MobileNumberDTO getMobileNumber(Long mobileNumberId) {

    MobileNumber mobileNumber = getMobileNumberFromRepository(mobileNumberId);

    return mobileNumberTransformer.convertToDto(mobileNumber);
  }

  private MobileNumber getMobileNumberFromRepository(Long mobileNumberId) {
    LOGGER.info("Find mobile number with id: " + mobileNumberId);

    Optional<MobileNumber> mobileNumber = mobileNumberRepository.findMobileNumberById(mobileNumberId);

    return mobileNumber.orElseThrow(ResourceNotFoundException::new);
  }
}