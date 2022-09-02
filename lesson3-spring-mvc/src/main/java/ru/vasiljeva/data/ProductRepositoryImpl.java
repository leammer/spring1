package ru.vasiljeva.data;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Repository;

@Repository
public class ProductRepositoryImpl implements ProductRepository {

	private Map<Long, Product> productMap = new ConcurrentHashMap<>();

	private final AtomicLong identity = new AtomicLong(0);

	@PostConstruct
	public void init() {
		InputStream is = getClass().getClassLoader().getResourceAsStream("products.txt");
		try (InputStreamReader streamReader = new InputStreamReader(is, StandardCharsets.UTF_8);
				BufferedReader reader = new BufferedReader(streamReader)) {

			String line;
			while ((line = reader.readLine()) != null) {
				String s[] = line.split("\\s");
				insert(new Product(s[0], Float.parseFloat(s[1])));
			}

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void insert(Product product) {
		long id = identity.incrementAndGet();
		product.setId(id);
		productMap.put(id, product);
	}

	@Override
	public Product getById(Long id) {
		return productMap.get(id);
	}

	@Override
	public List<Product> getAll() {
		return new ArrayList<Product>(productMap.values());
	}

	@Override
	public void remove(Long id) {
		productMap.remove(id);
	}

	@Override
	public Product update(Product product) {
		if (product.getId() == null) {
			product.setId(identity.incrementAndGet());
		}
		productMap.put(product.getId(), product);
		return product;

	}
}
