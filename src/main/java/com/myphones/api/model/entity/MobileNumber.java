package com.myphones.api.model.entity;

import javax.persistence.*;

@Entity
public class MobileNumber {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String number;

  @Enumerated(EnumType.STRING)
  private MobileNumberStatus status;

  private String validationComment;

  @ManyToOne
  @JoinColumn(name = "import_file_register_id", nullable = false)
  private ImportFileRegister importFileRegister;

  public MobileNumber() {
  }

  public MobileNumber(String number, MobileNumberStatus status, String validationComment, ImportFileRegister importFileRegister) {
    this.number = number;
    this.status = status;
    this.validationComment = validationComment;
    this.importFileRegister = importFileRegister;
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
