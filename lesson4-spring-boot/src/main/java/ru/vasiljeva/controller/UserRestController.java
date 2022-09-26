package ru.vasiljeva.controller;

import static ru.vasiljeva.utils.AppConstants.REST_USER_CONTROLLER_MAPPING;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import static ru.vasiljeva.utils.AppConstants.BY_ID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.access.annotation.Secured;
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

import ru.vasiljeva.dto.UserDto;
import ru.vasiljeva.service.UserService;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping(REST_USER_CONTROLLER_MAPPING)
public class UserRestController {
	@Autowired
	private UserService service;

	@GetMapping
	@Secured({ "ROLE_ADMIN", "ROLE_SUPERUSER" })
	public Page<UserDto> getAllUsers(@RequestParam Optional<String> filter,
			@RequestParam(defaultValue = "0") Integer number, @RequestParam(defaultValue = "20") Integer size,
			@RequestParam Optional<String> sort) {
		String filterValue = filter.orElse("");
		String sortValue = sort.orElse("");
		return this.service.getAll(filterValue, number, size, sortValue);
	}

	@GetMapping(BY_ID)
	@Secured("ROLE_SUPERUSER")
	public UserDto getUser(@PathVariable Long id) {
		return this.service.getUser(id);
	}

	@PostMapping()
	@Secured("ROLE_SUPERUSER")
	public UserDto addUser(@RequestBody @Valid UserDto dto) {
		return this.service.addUser(dto);
	}

	@DeleteMapping(BY_ID)
	@Secured("ROLE_SUPERUSER")
	void removeUser(@PathVariable Long id) {
		this.service.removeUser(id);
	}

	@PutMapping(BY_ID)
	@Secured("ROLE_SUPERUSER")
	UserDto updateUser(@PathVariable Long id, @RequestBody @Valid UserDto dto) {
		return this.service.updateUser(dto);
	}

	@GetMapping("/roles")
	@Secured("ROLE_SUPERUSER")
	List<String> getRoles() {
		return this.service.getRoles();
	}

}
