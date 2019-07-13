package com.myphones.api.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.myphones.api.model.entity.MobileNumber;

@Repository
public interface MobileNumberRepository extends CrudRepository<MobileNumber, Long> {

  Optional<MobileNumber> findMobileNumberById(Long mobileNumberId);
  List<MobileNumber> findAll();
  List<MobileNumber> findByIdIn(List<Long> mobileNumberIds);
  List<MobileNumber> findByImportFileRegisterId(Long importFileRegisterId);
}
