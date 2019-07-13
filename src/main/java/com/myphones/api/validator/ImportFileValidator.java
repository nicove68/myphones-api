package com.myphones.api.validator;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import com.myphones.api.exception.BadRequestException;


@Component
public class ImportFileValidator {

  private static final Long MAX_FILE_SIZE_IN_BYTES = 2097152L;

  public static void validateFile(MultipartFile file) {
    if (file == null) throw new BadRequestException("You must select the a file for uploading");
    if (file.getContentType() == null || !file.getContentType().equalsIgnoreCase("text/csv")) throw new BadRequestException("Import file must be a CSV file.");
    if (file.getSize() > MAX_FILE_SIZE_IN_BYTES) throw new BadRequestException("Import file size must be max 2mb");
  }

}
