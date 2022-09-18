package ru.vasiljeva.service;

import ru.vasiljeva.dto.PersonalInfoDto;
import ru.vasiljeva.model.ContactType;

public interface CustomerService {
	PersonalInfoDto getPersonalInfo(Long customerId);

	void updatePersonalInfoId(Long customerId, PersonalInfoDto dto);

	PersonalInfoDto addContact(Long customerId, ContactType type, String value);

	void removeContact(Long customerId, Long contactId);
}
