package com.myphones.api.transformer;

import org.springframework.stereotype.Component;

import com.myphones.api.model.dto.MobileNumberDTO;
import com.myphones.api.model.entity.MobileNumber;


@Component
public class MobileNumberTransformer {

	public static MobileNumberDTO convertToMobileNumberDTO(MobileNumber mobileNumber) {
		return new MobileNumberDTO(
				mobileNumber.getId(),
				mobileNumber.getNumber(),
				mobileNumber.getStatus().name(),
				mobileNumber.getValidationComment()
		);
	}
}
