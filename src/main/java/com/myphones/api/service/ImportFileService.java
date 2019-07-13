package com.myphones.api.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.myphones.api.model.dto.ImportFileRegisterDTO;
import com.myphones.api.model.entity.ImportFileRegister;
import com.myphones.api.model.entity.MobileNumber;
import com.myphones.api.model.entity.MobileNumberStatus;
import com.myphones.api.repository.ImportFileRegisterRepository;
import com.myphones.api.repository.MobileNumberRepository;
import com.myphones.api.transformer.ImportFileRegisterTransformer;
import com.myphones.api.validator.MobileNumberValidator;

@Service
public class ImportFileService {

  private static Logger LOGGER = LoggerFactory.getLogger(ImportFileService.class);
  private static final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss'Z'");
  private static final String COMMA_DELIMITER = ",";

  private ImportFileRegisterRepository importFileRegisterRepository;
  private MobileNumberRepository mobileNumberRepository;
  private ImportFileRegisterTransformer importFileRegisterTransformer;


  @Autowired
  public ImportFileService(ImportFileRegisterRepository importFileRegisterRepository, MobileNumberRepository mobileNumberRepository, ImportFileRegisterTransformer importFileRegisterTransformer) {
    this.importFileRegisterRepository = importFileRegisterRepository;
    this.mobileNumberRepository = mobileNumberRepository;
    this.importFileRegisterTransformer = importFileRegisterTransformer;
  }

  public ImportFileRegisterDTO importFile(MultipartFile file) {
    LOGGER.info("Importing file...");

    ImportFileRegister importFileRegister = saveNewImportFileRegister(file);

    LOGGER.info("File imported successfully!");

    return ImportFileRegisterTransformer.convertToImportFileRegisterDTO(importFileRegister);
  }

  private ImportFileRegister saveNewImportFileRegister(MultipartFile file) {
    ImportFileRegister importFileRegister = new ImportFileRegister(
        LocalDateTime.now().format(dateTimeFormatter),
        file.getOriginalFilename()
    );

    List<MobileNumber> extractedMobileNumbers = extractMobileNumbersFromFile(file);
    List<MobileNumber> mobileNumbers = filterNewMobileNumbers(extractedMobileNumbers);

    mobileNumbers.forEach(importFileRegister::addNumber);

    return importFileRegisterRepository.save(importFileRegister);
  }

  private List<MobileNumber> extractMobileNumbersFromFile(MultipartFile file) {
    try {
      LOGGER.info("Extracting mobile numbers from file");

      InputStream inputStream = file.getInputStream();
      BufferedReader br = new BufferedReader(new InputStreamReader(inputStream));

      List<MobileNumber> mobileNumberList = new ArrayList<>();
      String headerLine = br.readLine();
      String line;
      while ((line = br.readLine()) != null) {
        Optional<MobileNumber> mobileNumber = lineToMobileNumber(line);
        mobileNumber.ifPresent(mobileNumberList::add);
      }

      return mobileNumberList;

    } catch (IOException e) {
      LOGGER.error("Error when extracting mobile numbers from file");
      return new ArrayList<>();
    }
  }

  private List<MobileNumber> filterNewMobileNumbers(List<MobileNumber> mobileNumbers) {
    List<Long> mobileNumberIds = mobileNumbers.stream().map(MobileNumber::getId).collect(Collectors.toList());
    List<MobileNumber> existentMobileNumbers = mobileNumberRepository.findByIdIn(mobileNumberIds);
    List<Long> existentMobileNumberIds = existentMobileNumbers.stream().map(MobileNumber::getId).collect(Collectors.toList());

    return mobileNumbers.stream().filter(num -> !existentMobileNumberIds.contains(num.getId())).collect(Collectors.toList());
  }

  private Optional<MobileNumber> lineToMobileNumber(String line) {
    List<String> values = Arrays.asList(line.split(COMMA_DELIMITER));
    if (values.size() == 2) {

      Long id = Long.valueOf(values.get(0));
      String number = values.get(1);
      boolean isValidMobileNumber = MobileNumberValidator.isValidMobileNumber(number);

      return Optional.of(
          new MobileNumber(
              id,
              number,
              isValidMobileNumber ? MobileNumberStatus.VALID : MobileNumberStatus.INVALID,
              isValidMobileNumber ? null : "Is not a number || has not valid length || not start with correct number"
          )
      );
    }

    return Optional.empty();
  }

}