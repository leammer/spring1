package ru.vasiljeva.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.validation.constraints.NotBlank;

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
import ru.vasiljeva.model.QUser;
import ru.vasiljeva.model.Role;
import ru.vasiljeva.model.User;
import ru.vasiljeva.repository.UserRepository;
import ru.vasiljeva.service.UserService;
import ru.vasiljeva.utils.MappingUtils;
import ru.vasiljeva.utils.SortingParameter;

@Service
@Setter
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
	@Autowired
	private UserRepository repo;

	@Autowired
	private MappingUtils mappingUtils;

	@Override
	public UserDto addUser(UserDto dto) {
		QUser user = QUser.user;
		BooleanBuilder predicate = new BooleanBuilder(user.username.containsIgnoreCase(dto.getUsername()));
		if (this.repo.exists(predicate)) {
			throw new ServiceException(ExceptionType.ALREADY_EXISTS, "user");
		}

		if (dto.getId() != null) {
			dto.setId(null);
		}
		User entity = mappingUtils.mapToEntity(dto);
		return mappingUtils.mapToDto(this.repo.saveAndFlush(entity));
	}

	@Override
	public UserDto getUser(Long id) {
		User entity = this.repo.findById(id).get();
		UserDto dto = this.mappingUtils.mapToDto(entity);
		return dto;
	}

	@Override
	public UserDto updateUser(UserDto dto) {
		return mappingUtils.mapToDto(this.repo.saveAndFlush(mappingUtils.mapToEntity(dto)));
	}

	@Override
	public void removeUser(Long id) {
		this.repo.deleteById(id);
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
		return this.repo
				.findAll(predicate, PageRequest.of(number, size, sort.getSort()))
				.map(mappingUtils::mapToDto);
		//@formatter:on
	}
}
