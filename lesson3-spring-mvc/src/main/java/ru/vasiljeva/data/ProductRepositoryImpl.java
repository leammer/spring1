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

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Repository;

@Repository
public class ProductRepositoryImpl implements ProductRepository {

	private Map<Long, Product> productMap = new HashMap<>();
	
	@PostConstruct
    public void init() {
    	InputStream is = getClass().getClassLoader().getResourceAsStream("products.txt");
		try (InputStreamReader streamReader = new InputStreamReader(is, StandardCharsets.UTF_8);
				BufferedReader reader = new BufferedReader(streamReader)) {

			String line;
			Long id = 1L;
			while ((line = reader.readLine()) != null) {
				String s[] = line.split("\\s");
				addProduct(id, new Product(id, s[0], Float.parseFloat(s[1])));
		        id++;
			}

		} catch (IOException e) {
			e.printStackTrace();
		}
    }
	

    @Override
    public void addProduct(Long id, Product product) {
    	productMap.put(id, product);
    }


	@Override
	public Product findProductById(Long id) {
        return productMap.get(id);
	}

	@Override
	public List<Product> getAll() {
		return new ArrayList<Product>(productMap.values());
	}
}
