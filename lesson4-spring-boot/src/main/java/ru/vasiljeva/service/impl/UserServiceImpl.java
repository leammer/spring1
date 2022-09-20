package ru.vasiljeva.service.impl;

import java.util.Collections;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.querydsl.core.BooleanBuilder;

import lombok.RequiredArgsConstructor;
import lombok.Setter;
import ru.vasiljeva.dto.UserDto;
import ru.vasiljeva.exceptions.ExceptionType;
import ru.vasiljeva.exceptions.ServiceException;
import ru.vasiljeva.model.QRole;
import ru.vasiljeva.model.QUser;
import ru.vasiljeva.model.Role;
import ru.vasiljeva.model.User;
import ru.vasiljeva.repository.RoleRepository;
import ru.vasiljeva.repository.UserRepository;
import ru.vasiljeva.service.UserService;
import ru.vasiljeva.utils.MappingUtils;
import ru.vasiljeva.utils.SortingParameter;

@Service
@Setter
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
	@Autowired
	private UserRepository userRepository;

	@Autowired
	private RoleRepository roleRepository;

	@Autowired
	private MappingUtils mappingUtils;

	@Autowired
	private PasswordEncoder encoder;

	@Override
	public UserDto addUser(UserDto dto) {
		if (this.userRepository.findByUsername(dto.getUsername()).get() != null) {
			throw new ServiceException(ExceptionType.ALREADY_EXISTS, "user");
		}
		return saveOrUpdate(dto);
	}

	@Override
	public UserDto getUser(Long id) {
		User entity = this.userRepository.findById(id).get();
		UserDto dto = this.mappingUtils.mapToDto(entity);
		return dto;
	}

	@Override
	public UserDto updateUser(UserDto dto) {
		return saveOrUpdate(dto);
	}

	@Override
	public void removeUser(Long id) {
		this.userRepository.deleteById(id);
	}

	@Override
	public Page<UserDto> getAll(String filter, Integer number, Integer size, String sortValue) {
		QUser user = QUser.user;
		BooleanBuilder predicate = new BooleanBuilder();

		if (!filter.isBlank()) {
			predicate.and(user.username.containsIgnoreCase(filter));
		}

		SortingParameter sort = SortingParameter.getSortingParameter(sortValue);

		//@formatter:off
		return this.userRepository
				.findAll(predicate, PageRequest.of(number, size, sort.getSort()))
				.map(mappingUtils::mapToDto);
		//@formatter:on
	}

	@Override
	public org.springframework.security.core.userdetails.User findUserByUsername(String username) {
		return userRepository.findByUsername(username)
				.map(user -> new org.springframework.security.core.userdetails.User(user.getUsername(),
						user.getPassword(), Collections.singletonList(new SimpleGrantedAuthority("ADMIN"))))
				.orElseThrow(() -> new UsernameNotFoundException(username));
	}

	private UserDto saveOrUpdate(UserDto dto) {
		QRole role = QRole.role;
		User entity = mappingUtils.mapToEntity(dto, encoder);

		if (dto.getRoles() != null) {
			BooleanBuilder predicateRole = new BooleanBuilder();
			for (String roleValue : dto.getRoles()) {
				predicateRole.or(role.name.equalsIgnoreCase(roleValue));
			}
			Iterable<Role> iterator = this.roleRepository.findAll(predicateRole);
			Set<Role> roles = StreamSupport.stream(iterator.spliterator(), false).collect(Collectors.toSet());
			entity.setRoles(roles);
		}

		return mappingUtils.mapToDto(this.userRepository.saveAndFlush(entity));
	}
}
