package ru.vasiljeva.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import javax.validation.constraints.NotBlank;

import org.hibernate.collection.internal.PersistentList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
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

	@Override
	public UserDto addUser(UserDto dto) {
		QUser user = QUser.user;
		QRole role = QRole.role;

		if (this.userRepository.findByUsername(dto.getUsername()) != null) {
			throw new ServiceException(ExceptionType.ALREADY_EXISTS, "user");
		}

		User entity = mappingUtils.mapToEntity(dto);

		if (dto.getRoles() != null) {
			BooleanBuilder predicateRole = new BooleanBuilder();
			for (String roleValue : dto.getRoles()) {
				predicateRole.or(role.name.equalsIgnoreCase(roleValue));
			}
			Iterable<Role> iterator = this.roleRepository.findAll(predicateRole);
			List<Role> roles = StreamSupport.stream(iterator.spliterator(), false).collect(Collectors.toList());
			entity.setRoles(roles);
		}

		return mappingUtils.mapToDto(this.userRepository.saveAndFlush(entity));
	}

	@Override
	public UserDto getUser(Long id) {
		User entity = this.userRepository.findById(id).get();
		UserDto dto = this.mappingUtils.mapToDto(entity);
		return dto;
	}

	@Override
	public UserDto updateUser(UserDto dto) {
		return null;// mappingUtils.mapToDto(this.userRepository.saveAndFlush(mappingUtils.mapToEntity(dto)));
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
}
