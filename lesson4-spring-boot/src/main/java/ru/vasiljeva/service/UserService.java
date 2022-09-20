package ru.vasiljeva.service;

import org.springframework.data.domain.Page;

import ru.vasiljeva.dto.UserDto;

public interface UserService {
	UserDto addUser(UserDto dto);

	UserDto getUser(Long id);

	UserDto updateUser(UserDto dto);

	void removeUser(Long id);

	Page<UserDto> getAll(String filter, Integer number, Integer size, String sort);

	org.springframework.security.core.userdetails.User findUserByUsername(String username);
}
