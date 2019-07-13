package com.myphones.api.validator;

import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Component;


@Component
public class MobileNumberValidator {

  private static final Integer VALID_MAX_NUMBER_LENGTH = 11;
  private static final List<Integer> VALID_NUMBER_STARTS_WITH = Arrays.asList(6,7,8);

  public static boolean isValidMobileNumber(String number) {
    return isNumeric(number) && hasValidLength(number) && hasValidStart(number);
  }

  private static boolean isNumeric(String number) {
    try {
      Long l = Long.parseLong(number);
    } catch (NumberFormatException | NullPointerException nfe) {
      return false;
    }
    return true;
  }

  private static boolean hasValidLength(String number) {
    return number.length() <= VALID_MAX_NUMBER_LENGTH;
  }

  private static boolean hasValidStart(String number) {
    Integer firstNum = Integer.parseInt(number.substring(0, 1));
    return VALID_NUMBER_STARTS_WITH.contains(firstNum);
  }
}
