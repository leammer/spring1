package ru.vasiljeva.service;

import ru.vasiljeva.dto.PersonalInfoDto;

public interface CustomerService {
	PersonalInfoDto addPersonalInfo(Long usereId, PersonalInfoDto dto);

	PersonalInfoDto getPersonalInfo(Long userId);

	PersonalInfoDto updatePersonalInfo(Long userId, PersonalInfoDto dto);

	void removePersonalInfo(Long userId, Long customerId);
}
