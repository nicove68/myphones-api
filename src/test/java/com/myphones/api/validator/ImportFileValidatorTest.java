package com.myphones.api.validator;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.mock.web.MockMultipartFile;

import com.myphones.api.exception.BadRequestException;


@RunWith(MockitoJUnitRunner.class)
public class ImportFileValidatorTest {


  @Test(expected = BadRequestException.class)
  public void when_import_invalid_file_then_return_bad_request_exception() {
    MockMultipartFile file = null;
    ImportFileValidator.validateFile(file);
  }

  @Test(expected = BadRequestException.class)
  public void when_import_invalid_file_content_type_then_return_bad_request_exception() {
    MockMultipartFile file = new MockMultipartFile("file", "orig", "image/jpeg", "bar".getBytes());
    ImportFileValidator.validateFile(file);
  }

}