package ru.vasiljeva.homework;

import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;

@Repository
public class ProductRepositoryImpl implements ProductRepository {

    private final Map<Long, Product> productMap = new HashMap<>();

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
    public Product findProductById(Long id) {
        return productMap.get(id);
    }

    @Override
    public void addProduct(Long id, Product product) {
        productMap.put(id, product);
    }
}
