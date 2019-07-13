package com.myphones.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import com.myphones.api.model.dto.ImportFileRegisterDTO;
import com.myphones.api.service.ImportFileService;
import com.myphones.api.validator.ImportFileValidator;

@RestController
@RequestMapping(value = "/mobile_numbers/import_files")
public class ImportFileController extends BaseController {

  private ImportFileService importFileService;

  @Autowired
  public ImportFileController(ImportFileService importFileService) {
    this.importFileService = importFileService;
  }


  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public ImportFileRegisterDTO importFile(@RequestParam("file") MultipartFile file) {

    ImportFileValidator.validateFile(file);

    return importFileService.importFile(file);
  }

  @GetMapping
  public List<ImportFileRegisterDTO> getAllImportFileRegisters() {

    return importFileService.getAllImportFileRegisters();
  }

  @GetMapping("/{importFileRegisterId}")
  public ImportFileRegisterDTO getImportFileRegister(@PathVariable Long importFileRegisterId) {

    return importFileService.getImportFileRegister(importFileRegisterId);
  }
}
