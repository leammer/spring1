package ru.vasiljeva.service.impl;

import java.util.List;

//import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.Setter;
import ru.vasiljeva.dao.ProductDao;
import ru.vasiljeva.model.Product;
import ru.vasiljeva.service.ProductService;

@Service
@Setter
public class ProductServiceImpl implements ProductService {

	@Autowired
	private ProductDao productDao;

	@Override
	public void addProduct(Product product) {
		this.productDao.saveAndFlush(product);
	}

	@Override
	public void updateProduct(Product product) {
		this.productDao.saveAndFlush(product);
	}

	@Override
	public void removeProduct(Long id) {
		this.productDao.deleteById(id);
	}

	@Override
	public Product getProductById(Long id) {
		return this.productDao.getReferenceById(id);
	}

	@Override
	public List<Product> getAll() {
		return this.productDao.findAll();
	}
}
