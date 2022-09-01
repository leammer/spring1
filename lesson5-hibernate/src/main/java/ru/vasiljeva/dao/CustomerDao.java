package ru.vasiljeva.dao;

import java.util.List;

import ru.vasiljeva.model.Customer;

public interface CustomerDao {
	public void addCustomer(Customer Customer);

	public void updateCustomer(Customer Customer);

	public void removeCustomer(Long id);

	public Customer getCustomerById(Long id);

	public List<Customer> getAll();
}
