package com.myphones.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.myphones.api.model.dto.MobileNumberDTO;
import com.myphones.api.service.MobileNumberService;

@RestController
@RequestMapping(value = "/mobile_numbers")
public class MobileNumberController extends BaseController {

  private MobileNumberService mobileNumberService;

  @Autowired
  public MobileNumberController(MobileNumberService mobileNumberService) {
    this.mobileNumberService = mobileNumberService;
  }


  @GetMapping
  public List<MobileNumberDTO> getAllMobileNumbers() {

    return mobileNumberService.getAllMobileNumbers();
  }

  @GetMapping("/{mobileNumberId}")
  public MobileNumberDTO getMobileNumber(@PathVariable Long mobileNumberId) {

    return mobileNumberService.getMobileNumber(mobileNumberId);
  }
}
