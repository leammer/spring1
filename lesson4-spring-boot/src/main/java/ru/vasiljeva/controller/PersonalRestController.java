package ru.vasiljeva.controller;

import static ru.vasiljeva.utils.AppConstants.REST_PERSONAL_CONTROLLER_MAPPING;

import javax.validation.Valid;

import static ru.vasiljeva.utils.AppConstants.BY_ID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

import ru.vasiljeva.dto.PersonalInfoDto;
import ru.vasiljeva.exceptions.ExceptionType;
import ru.vasiljeva.exceptions.ServiceException;
import ru.vasiljeva.model.ContactType;
import ru.vasiljeva.service.CustomerService;

@RestController
@RequestMapping(REST_PERSONAL_CONTROLLER_MAPPING)
public class PersonalRestController {
	@Autowired
	private CustomerService service;

	@GetMapping(BY_ID)
	public PersonalInfoDto getPersonalInfo(@PathVariable Long id) {
		return this.service.getPersonalInfo(id);
	}

	@PutMapping(BY_ID)
	public void updatePersonalInfoId(@PathVariable Long id, @RequestBody @Valid PersonalInfoDto dto) {
		this.service.updatePersonalInfoId(id, dto);
	}

	@PostMapping(BY_ID)
	PersonalInfoDto addContact(@PathVariable Long id, @RequestBody JsonNode node) {
		if (!node.hasNonNull("type") || !node.hasNonNull("value")) {
			throw new ServiceException(ExceptionType.BAD_REQUEST, "Incorrect json format: type or value missed");
		}
		ContactType type = ContactType.valueOf(node.get("type").asText());
		String value = node.get("value").asText();
		return this.service.addContact(id, type, value);
	}

	@DeleteMapping(BY_ID)
	void removeContact(@PathVariable Long id, @RequestBody ObjectNode node) {
		if (!node.hasNonNull("contact_id")) {
			throw new ServiceException(ExceptionType.BAD_REQUEST, "Incorrect json format: contact_id missed");
		}
		Long contactId = node.get("contact_id").asLong();
		this.service.removeContact(id, contactId);
	}
}
