package com.myphones.api.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.myphones.api.model.entity.ImportFileRegister;

@Repository
public interface ImportFileRegisterRepository extends CrudRepository<ImportFileRegister, Long> {

  Optional<ImportFileRegister> findImportFileRegisterById(Long importFileRegisterId);
  List<ImportFileRegister> findAll();
}
