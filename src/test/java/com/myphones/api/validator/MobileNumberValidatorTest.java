package com.myphones.api.validator;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;


@RunWith(MockitoJUnitRunner.class)
public class MobileNumberValidatorTest {


  @Test
  public void when_mobile_number_is_not_numeric_then_return_false() {

    String number_1 = "711111111EE";
    String number_2 = "71111111111";

    boolean validation_1 = MobileNumberValidator.isValidMobileNumber(number_1);
    boolean validation_2 = MobileNumberValidator.isValidMobileNumber(number_2);

    assertFalse(validation_1);
    assertTrue(validation_2);
  }

  @Test
  public void when_mobile_number_has_wrong_start_then_return_false() {

    String number_1 = "31111111111";
    String number_2 = "91111111111";
    String number_3 = "71111111111";

    boolean validation_1 = MobileNumberValidator.isValidMobileNumber(number_1);
    boolean validation_2 = MobileNumberValidator.isValidMobileNumber(number_2);
    boolean validation_3 = MobileNumberValidator.isValidMobileNumber(number_3);

    assertFalse(validation_1);
    assertFalse(validation_2);
    assertTrue(validation_3);
  }

  @Test
  public void when_mobile_number_has_wrong_length_then_return_false() {

    String number_1 = "6666666666666";
    String number_2 = "71111111111";

    boolean validation_1 = MobileNumberValidator.isValidMobileNumber(number_1);
    boolean validation_2 = MobileNumberValidator.isValidMobileNumber(number_2);

    assertFalse(validation_1);
    assertTrue(validation_2);
  }
}