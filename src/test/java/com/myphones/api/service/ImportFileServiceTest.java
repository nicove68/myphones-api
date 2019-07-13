package com.myphones.api.service;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.junit4.SpringRunner;

import com.myphones.api.model.dto.ImportFileRegisterDTO;
import com.myphones.api.model.entity.MobileNumber;
import com.myphones.api.repository.MobileNumberRepository;


@RunWith(SpringRunner.class)
@SpringBootTest
public class ImportFileServiceTest {

  @Autowired @InjectMocks
  private ImportFileService importFileService;

  @Autowired
  private MobileNumberRepository mobileNumberRepository;


  @Test
  public void when_import_mobile_numbers_then_return_correct_statistics() {

    String mobileNumbersCSV =
        "id,sms_phone\n" +
        "1,666\n" +
        "2,84528784843\n" +
        "3,7556676\n" +
        "4,27736529279\n" +
        "5,TEXT\n" +
        "6,8333319855201547\n" +
        "7,64528784555";

    MockMultipartFile file = new MockMultipartFile("file", "orig", null, mobileNumbersCSV.getBytes());

    ImportFileRegisterDTO importFileRegisterDTO = importFileService.importFile(file);

    assertEquals(Long.valueOf(4), importFileRegisterDTO.getStatistics().getValidNumbers());
    assertEquals(Long.valueOf(3), importFileRegisterDTO.getStatistics().getInvalidNumbers());
    assertEquals(Long.valueOf(7), importFileRegisterDTO.getStatistics().getTotalNumbers());

    List<MobileNumber> savedNumbers = mobileNumberRepository.findByImportFileRegisterId(importFileRegisterDTO.getId());
    assertEquals(7, savedNumbers.size());
    mobileNumberRepository.deleteAll();
  }


  @Test
  public void when_import_repeat_mobile_numbers_then_discard_duplicated_and_return_correct_statistics() {

    String mobileNumbersCSV =
        "id,sms_phone\n" +
        "1,666\n" +
        "1,84528784843\n" +
        "1,7556676\n" +
        "2,64528784555";

    MockMultipartFile file = new MockMultipartFile("file", "orig", null, mobileNumbersCSV.getBytes());

    ImportFileRegisterDTO importFileRegisterDTO = importFileService.importFile(file);

    assertEquals(Long.valueOf(2), importFileRegisterDTO.getStatistics().getValidNumbers());

    List<MobileNumber> savedNumbers = mobileNumberRepository.findByImportFileRegisterId(importFileRegisterDTO.getId());
    assertEquals(2, savedNumbers.size());
    mobileNumberRepository.deleteAll();
  }


  @Test
  public void when_import_mobile_numbers_and_import_another_repeat_mobile_numbers_then_discard_duplicated_and_return_correct_statistics() {

    String mobileNumbersCSV =
        "id,sms_phone\n" +
        "1,666\n" +
        "2,84528784843\n" +
        "3,7556676\n" +
        "4,27736529279\n" +
        "5,TEXT\n" +
        "6,8333319855201547\n" +
        "7,64528784555";

    MockMultipartFile file = new MockMultipartFile("file", "orig", null, mobileNumbersCSV.getBytes());

    ImportFileRegisterDTO importFileRegisterDTO = importFileService.importFile(file);

    assertEquals(Long.valueOf(4), importFileRegisterDTO.getStatistics().getValidNumbers());
    assertEquals(Long.valueOf(3), importFileRegisterDTO.getStatistics().getInvalidNumbers());
    assertEquals(Long.valueOf(7), importFileRegisterDTO.getStatistics().getTotalNumbers());

    String secondRepeatedMobileNumbersCSV =
        "id,sms_phone\n" +
            "1,666\n" +
            "2,84528784843\n" +
            "3,7556676\n" +
            "10,69998784555\n" +
            "11,TEXT";

    MockMultipartFile secondFile = new MockMultipartFile("file", "orig", null, secondRepeatedMobileNumbersCSV.getBytes());

    ImportFileRegisterDTO secondImportFileRegisterDTO = importFileService.importFile(secondFile);

    assertEquals(Long.valueOf(1), secondImportFileRegisterDTO.getStatistics().getValidNumbers());
    assertEquals(Long.valueOf(1), secondImportFileRegisterDTO.getStatistics().getInvalidNumbers());
    assertEquals(Long.valueOf(2), secondImportFileRegisterDTO.getStatistics().getTotalNumbers());

    List<MobileNumber> savedNumbers = mobileNumberRepository.findAll();
    assertEquals(9, savedNumbers.size());
    mobileNumberRepository.deleteAll();
  }


  @Test
  public void when_import_empty_mobile_numbers_then_return_correct_statistics() {

    String mobileNumbersCSV = "id,sms_phone";

    MockMultipartFile file = new MockMultipartFile("file", "orig", null, mobileNumbersCSV.getBytes());

    ImportFileRegisterDTO importFileRegisterDTO = importFileService.importFile(file);

    assertEquals(Long.valueOf(0), importFileRegisterDTO.getStatistics().getValidNumbers());
    assertEquals(Long.valueOf(0), importFileRegisterDTO.getStatistics().getFixedNumbers());
    assertEquals(Long.valueOf(0), importFileRegisterDTO.getStatistics().getInvalidNumbers());
    assertEquals(Long.valueOf(0), importFileRegisterDTO.getStatistics().getTotalNumbers());

    List<MobileNumber> savedNumbers = mobileNumberRepository.findByImportFileRegisterId(importFileRegisterDTO.getId());
    assertEquals(0, savedNumbers.size());
    mobileNumberRepository.deleteAll();
  }
}