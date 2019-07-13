package com.myphones.api.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import com.myphones.api.model.dto.ImportFileRegisterDTO;
import com.myphones.api.service.ImportFileService;
import com.myphones.api.validator.ImportFileValidator;
import com.myphones.api.service.MobileNumberService;

@RestController
@RequestMapping(value = "/mobile_numbers/import_files")
public class ImportFileController extends BaseController {

  private ImportFileService importFileService;

  @Autowired
  public ImportFileController(ImportFileService importFileService) {
    this.importFileService = importFileService;
  }




  @PostMapping
  @ResponseStatus(HttpStatus.OK)
  public ImportFileRegisterDTO importFile(@RequestParam("file") MultipartFile file) {

    ImportFileValidator.validateFile(file);


    return importFileService.importFile(file);
  }

}
