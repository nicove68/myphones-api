package com.myphones.api.model.entity;

public enum MobileNumberStatus {
  VALID, FIXED, INVALID;

  public static boolean contains(String value) {
    for (MobileNumberStatus status : MobileNumberStatus.values()) {
      if (status.name().equals(value)) {
        return true;
      }
    }
    return false;
  }
}
