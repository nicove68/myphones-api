package com.myphones.api.model.dto;

import java.util.List;

public class ImportFileRegisterDTO {

  private Long id;
  private String importedDate;
  private String fileName;
  private ImportFileRegisterStatisticDTO statistics;
  private List<MobileNumberDTO> numbers;

  public ImportFileRegisterDTO() {
  }

  public ImportFileRegisterDTO(Long id, String importedDate, String fileName, ImportFileRegisterStatisticDTO statistics, List<MobileNumberDTO> numbers) {
    this.id = id;
    this.importedDate = importedDate;
    this.fileName = fileName;
    this.statistics = statistics;
    this.numbers = numbers;
  }

  public ImportFileRegisterDTO(Long id, String importedDate, String fileName) {
    this.id = id;
    this.importedDate = importedDate;
    this.fileName = fileName;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getImportedDate() {
    return importedDate;
  }

  public void setImportedDate(String importedDate) {
    this.importedDate = importedDate;
  }

  public String getFileName() {
    return fileName;
  }

  public void setFileName(String fileName) {
    this.fileName = fileName;
  }

  public ImportFileRegisterStatisticDTO getStatistics() {
    return statistics;
  }

  public void setStatistics(ImportFileRegisterStatisticDTO statistics) {
    this.statistics = statistics;
  }

  public List<MobileNumberDTO> getNumbers() {
    return numbers;
  }

  public void setNumbers(List<MobileNumberDTO> numbers) {
    this.numbers = numbers;
  }
}
