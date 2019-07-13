package com.myphones.api.model.dto;

public class MobileNumberDTO {

  private Long id;
  private String number;
  private String status;
  private String validationComment;

  public MobileNumberDTO() {
  }

  public MobileNumberDTO(Long id, String number, String status, String validationComment) {
    this.id = id;
    this.number = number;
    this.status = status;
    this.validationComment = validationComment;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getNumber() {
    return number;
  }

  public void setNumber(String number) {
    this.number = number;
  }

  public String getStatus() {
    return status;
  }

  public void setStatus(String status) {
    this.status = status;
  }

  public String getValidationComment() {
    return validationComment;
  }

  public void setValidationComment(String validationComment) {
    this.validationComment = validationComment;
  }
}
