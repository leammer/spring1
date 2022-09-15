package ru.vasiljeva.service.impl;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.util.MultiValueMap;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.dsl.BooleanExpression;

import lombok.RequiredArgsConstructor;
import lombok.Setter;
import ru.vasiljeva.dto.CartDto;
import ru.vasiljeva.dto.PersonalInfoDto;
import ru.vasiljeva.dto.ItemDto;
import ru.vasiljeva.dto.ProductDto;
import ru.vasiljeva.exceptions.ExceptionType;
import ru.vasiljeva.exceptions.ServiceException;
import ru.vasiljeva.model.Cart;
import ru.vasiljeva.model.Contact;
import ru.vasiljeva.model.ContactType;
import ru.vasiljeva.model.Customer;
import ru.vasiljeva.model.QCart;
import ru.vasiljeva.model.QContact;
import ru.vasiljeva.model.QProduct;
import ru.vasiljeva.repository.CartRepository;
import ru.vasiljeva.repository.ContactRepository;
import ru.vasiljeva.repository.CustomerRepository;
import ru.vasiljeva.repository.ItemRepository;
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
	private MappingUtils mappingUtils;

	@Override
	public PersonalInfoDto getPersonalInfo(Long customerId) {
		//@formatter:off
		return this.customerRepository
				.findById(customerId)
				.map(mappingUtils::mapToPersonalInfoDto)
				.orElseThrow(() -> new ServiceException(ExceptionType.NOT_FOUND, "customer", "id=" + customerId, ""));
		//@formatter:on
	}

	@Override
	public void updatePersonalInfoId(Long customerId, PersonalInfoDto dto) {
		this.customerRepository.saveAndFlush(mappingUtils.mapToCustomerEntity(dto));
	}

	@Override
	public PersonalInfoDto addContact(Long customerId, ContactType type, String value) {
		Customer customer = this.customerRepository.findById(customerId)
				.orElseThrow(() -> new ServiceException(ExceptionType.NOT_FOUND, "customer", "id=" + customerId, ""));
		Set<Contact> contacts = customer.getContacts();
		Optional<Contact> contact = contacts.stream().filter(c -> c.getType() == type).findAny();
		if (contact.isPresent()) {
			contact.get().setValue(value);
		} else {
			Contact newContact = new Contact(type, value);
			newContact.setCustomer(customer);
			contacts.add(newContact);
		}
		Customer newCustomer = this.customerRepository.save(customer);
		return mappingUtils.mapToPersonalInfoDto(newCustomer);
	}

	@Override
	public void removeContact(Long customerId, Long contactId) {
		this.contactRepository.deleteById(contactId);
	}

}
