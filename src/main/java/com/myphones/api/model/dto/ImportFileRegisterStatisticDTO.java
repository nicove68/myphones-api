package com.myphones.api.model.dto;

public class ImportFileRegisterStatisticDTO {

  private Long validNumbers;
  private Long fixedNumbers;
  private Long invalidNumbers;
  private Long totalNumbers;

  public ImportFileRegisterStatisticDTO() {
  }

  public ImportFileRegisterStatisticDTO(Long validNumbers, Long fixedNumbers, Long invalidNumbers, Long totalNumbers) {
    this.validNumbers = validNumbers;
    this.fixedNumbers = fixedNumbers;
    this.invalidNumbers = invalidNumbers;
    this.totalNumbers = totalNumbers;
  }

  public Long getValidNumbers() {
    return validNumbers;
  }

  public void setValidNumbers(Long validNumbers) {
    this.validNumbers = validNumbers;
  }

  public Long getFixedNumbers() {
    return fixedNumbers;
  }

  public void setFixedNumbers(Long fixedNumbers) {
    this.fixedNumbers = fixedNumbers;
  }

  public Long getInvalidNumbers() {
    return invalidNumbers;
  }

  public void setInvalidNumbers(Long invalidNumbers) {
    this.invalidNumbers = invalidNumbers;
  }

  public Long getTotalNumbers() {
    return totalNumbers;
  }

  public void setTotalNumbers(Long totalNumbers) {
    this.totalNumbers = totalNumbers;
  }
}
