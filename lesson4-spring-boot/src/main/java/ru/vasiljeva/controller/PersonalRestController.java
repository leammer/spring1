package ru.vasiljeva.controller;

import static ru.vasiljeva.utils.AppConstants.REST_PERSONAL_CONTROLLER_MAPPING;

import javax.validation.Valid;

import static ru.vasiljeva.utils.AppConstants.BY_ID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import ru.vasiljeva.dto.PersonalInfoDto;
import ru.vasiljeva.service.CustomerService;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping(REST_PERSONAL_CONTROLLER_MAPPING)
public class PersonalRestController {
	@Autowired
	private CustomerService service;

	@GetMapping(BY_ID)
	public PersonalInfoDto getPersonalInfo(@PathVariable Long id) {
		return this.service.getPersonalInfo(id);
	}

	@PostMapping(BY_ID)
	PersonalInfoDto addPersonalInfo(@PathVariable Long id, @RequestBody PersonalInfoDto dto) {
		return this.service.addPersonalInfo(id, dto);
	}

	@PutMapping(BY_ID)
	public PersonalInfoDto updatePersonalInfoId(@PathVariable Long id, @RequestBody @Valid PersonalInfoDto dto) {
		return this.service.updatePersonalInfo(id, dto);
	}

	@DeleteMapping(BY_ID)
	void removePersonalInfo(@PathVariable Long id, @RequestParam(name = "customer_id") Long customerId) {
		this.service.removePersonalInfo(id, customerId);
	}
}
