	package ru.vasiljeva.dao;

import java.util.List;

import ru.vasiljeva.model.Item;

public interface ItemDao {
	public void addItem(Item Item);

	public void updateItem(Item Item);

	public void removeItem(Long id);

	public Item getItemById(Long id);

	public List<Item> getAll();
}
