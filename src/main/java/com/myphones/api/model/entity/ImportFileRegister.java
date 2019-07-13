package com.myphones.api.model.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class ImportFileRegister {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String importedDate;

  private String fileName;

  public ImportFileRegister() {
  }

  public ImportFileRegister(String importedDate, String fileName) {
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
}
