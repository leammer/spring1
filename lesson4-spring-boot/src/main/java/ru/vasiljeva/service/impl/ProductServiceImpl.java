package ru.vasiljeva.service.impl;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.querydsl.core.BooleanBuilder;

import lombok.RequiredArgsConstructor;
import lombok.Setter;
import ru.vasiljeva.dto.ProductDto;
import ru.vasiljeva.exceptions.EntityNotFoundException;
import ru.vasiljeva.model.Product;
import ru.vasiljeva.model.QProduct;
import ru.vasiljeva.repository.ProductRepository;
import ru.vasiljeva.service.MappingUtils;
import ru.vasiljeva.service.ProductService;

@Service
@RequiredArgsConstructor
@Setter
public class ProductServiceImpl implements ProductService {

	@Autowired
	private ProductRepository productRepository;

	@Autowired
	private MappingUtils mappingUtils;

	@Override
	public void addProduct(ProductDto dto) {
		this.productRepository.saveAndFlush(mappingUtils.mapToProductEntity(dto));
	}

	@Override
	public void removeProduct(Long id) {
		this.productRepository.deleteById(id);
	}

	@Override
	public ProductDto getProductById(Long id) {
		//@formatter:off
		return this.productRepository
				.findById(id)
				.map(mappingUtils::mapToProductDto)
				.orElseThrow(() -> new EntityNotFoundException("Couldn't find product by id " + id));
		//@formatter:on

	}

	@Override
	public List<ProductDto> getAll(Map<String, Object> params) {
		QProduct product = QProduct.product;
		BooleanBuilder predicate = new BooleanBuilder();

		if (params.containsKey("search")) {
			predicate.and(product.name.containsIgnoreCase((String) params.get("search")));
		}
		if (params.containsKey("minPrice")) {
			predicate.and(product.cost.goe((Integer) params.get("minPrice")));
		}

		if (params.containsKey("maxPrice")) {
			predicate.and(product.cost.loe((Integer) params.get("maxPrice")));
		}

		Iterable<Product> list = this.productRepository.findAll(predicate);
		//@formatter:off
		return StreamSupport
				.stream(list.spliterator(), false)
				.map(mappingUtils::mapToProductDto)
				.toList();
		//@formatter:on
	}
}
