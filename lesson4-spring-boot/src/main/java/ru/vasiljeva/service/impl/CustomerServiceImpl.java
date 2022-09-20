package ru.vasiljeva.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import lombok.Setter;
import ru.vasiljeva.dto.PersonalInfoDto;
import ru.vasiljeva.exceptions.ExceptionType;
import ru.vasiljeva.exceptions.ServiceException;
import ru.vasiljeva.model.Customer;
import ru.vasiljeva.model.Role;
import ru.vasiljeva.model.User;
import ru.vasiljeva.repository.ContactRepository;
import ru.vasiljeva.repository.CustomerRepository;
import ru.vasiljeva.repository.RoleRepository;
import ru.vasiljeva.repository.UserRepository;
import ru.vasiljeva.service.CustomerService;
import ru.vasiljeva.utils.MappingUtils;

@Service
@RequiredArgsConstructor
@Setter
public class CustomerServiceImpl implements CustomerService {
	@Autowired
	private CustomerRepository customerRepository;

	@Autowired
	private ContactRepository contactRepository;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private RoleRepository roleRepository;

	@Autowired
	private MappingUtils mappingUtils;

	@Override
	public PersonalInfoDto getPersonalInfo(Long userId) {
		Customer entity = this.customerRepository.findByUserId(userId);
		if (entity == null) {
			throw new ServiceException(ExceptionType.NOT_FOUND, "customer", "userId=" + userId, "");
		}
		return this.mappingUtils.mapToDto(entity);
	}

	@Override
	public PersonalInfoDto addPersonalInfo(Long userId, PersonalInfoDto dto) {
		if (this.customerRepository.findByUserId(userId) != null) {
			throw new ServiceException(ExceptionType.ALREADY_EXISTS, "customer");
		}

		return saveOrUpdate(dto, userId);
	}

	@Override
	public PersonalInfoDto updatePersonalInfo(Long userId, PersonalInfoDto dto) {
		return saveOrUpdate(dto, userId);
	}

	@Override
	public void removePersonalInfo(Long userId, Long customerId) {
		User user = this.userRepository.findById(userId).get();
		user.getRoles().removeIf(e -> e.getName().equalsIgnoreCase("user"));
		this.userRepository.saveAndFlush(user);

		this.customerRepository.deleteById(customerId);
	}

	private PersonalInfoDto saveOrUpdate(PersonalInfoDto dto, Long userId) {
		Customer entity = mappingUtils.mapToEntity(dto);

		if (entity.getUser() == null) {
			User user = this.userRepository.findById(userId).get();
			if (user == null) {
				throw new ServiceException(ExceptionType.NOT_FOUND, "user", "id=" + userId, "");
			}
			Role userRole = this.roleRepository.findByName("user");
			user.getRoles().add(userRole);
			entity.setUser(user);
		}

		Customer newCustomer = this.customerRepository.saveAndFlush(entity);
		newCustomer.getContacts().forEach(c -> c.setCustomer(newCustomer));
		this.contactRepository.saveAllAndFlush(newCustomer.getContacts());

		return mappingUtils.mapToDto(newCustomer);
	}
}
