package com.myphones.api.model.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

@Entity
public class ImportFileRegister {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;

  private String importedDate;

  private String fileName;

  @OneToMany(cascade = CascadeType.ALL, mappedBy = "importFileRegister", orphanRemoval = true)
  private List<MobileNumber> numbers = new ArrayList<>();

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

  public List<MobileNumber> getNumbers() {
    return numbers;
  }

  public void addNumber(MobileNumber mobileNumber) {
    numbers.add(mobileNumber);
    mobileNumber.setImportFileRegister(this);
  }
}
