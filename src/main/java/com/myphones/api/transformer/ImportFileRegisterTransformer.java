package com.myphones.api.transformer;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.myphones.api.model.dto.ImportFileRegisterDTO;
import com.myphones.api.model.dto.ImportFileRegisterStatisticDTO;
import com.myphones.api.model.dto.MobileNumberDTO;
import com.myphones.api.model.entity.ImportFileRegister;
import com.myphones.api.model.entity.MobileNumberStatus;


@Component
public class ImportFileRegisterTransformer {

	public static ImportFileRegisterDTO convertToImportFileRegisterDTO(ImportFileRegister importFileRegister) {
		List<MobileNumberDTO> numbers = importFileRegister.getNumbers().stream()
				.map(MobileNumberTransformer::convertToMobileNumberDTO)
				.collect(Collectors.toList());

		ImportFileRegisterStatisticDTO statistics = convertToImportFileRegisterStatisticDTO(importFileRegister);

		return new ImportFileRegisterDTO(
			importFileRegister.getId(),
			importFileRegister.getImportedDate(),
			importFileRegister.getFileName(),
			statistics,
			numbers
		);
	}

	private static ImportFileRegisterStatisticDTO convertToImportFileRegisterStatisticDTO(ImportFileRegister importFileRegister) {
		Long validNumbers = importFileRegister.getNumbers().stream().filter(num -> num.getStatus().equals(MobileNumberStatus.VALID)).count();
		Long fixedNumbers = importFileRegister.getNumbers().stream().filter(num -> num.getStatus().equals(MobileNumberStatus.FIXED)).count();
		Long invalidNumbers = importFileRegister.getNumbers().stream().filter(num -> num.getStatus().equals(MobileNumberStatus.INVALID)).count();
		Long totalNumbers = validNumbers + fixedNumbers + invalidNumbers;
		return new ImportFileRegisterStatisticDTO(
				validNumbers,
				fixedNumbers,
				invalidNumbers,
				totalNumbers
		);
	}

	public static ImportFileRegisterDTO convertToBasicImportFileRegisterDTO(ImportFileRegister importFileRegister) {
		return new ImportFileRegisterDTO(
				importFileRegister.getId(),
				importFileRegister.getImportedDate(),
				importFileRegister.getFileName()
		);
	}
}
