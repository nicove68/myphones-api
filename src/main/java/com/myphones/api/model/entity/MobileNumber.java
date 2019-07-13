package com.myphones.api.model.entity;

import javax.persistence.*;

@Entity
public class MobileNumber {

  @Id
  private Long id;

  private String number;

  @Enumerated(EnumType.STRING)
  private MobileNumberStatus status;

  private String validationComment;

  @ManyToOne
  private ImportFileRegister importFileRegister;

  public MobileNumber() {
  }

  public MobileNumber(Long id, String number, MobileNumberStatus status, String validationComment) {
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

  public MobileNumberStatus getStatus() {
    return status;
  }

  public void setStatus(MobileNumberStatus status) {
    this.status = status;
  }

  public String getValidationComment() {
    return validationComment;
  }

  public void setValidationComment(String validationComment) {
    this.validationComment = validationComment;
  }

  public ImportFileRegister getImportFileRegister() {
    return importFileRegister;
  }

  public void setImportFileRegister(ImportFileRegister importFileRegister) {
    this.importFileRegister = importFileRegister;
  }
}
