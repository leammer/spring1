package ru.vasiljeva.service.impl;

import java.util.List;
import java.util.stream.Collectors;

//import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.Setter;
import ru.vasiljeva.dto.ProductDto;
import ru.vasiljeva.model.Product;
import ru.vasiljeva.repository.ProductRepository;
import ru.vasiljeva.service.MappingUtils;
import ru.vasiljeva.service.ProductService;

@Service
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
		Product entity = this.productRepository.getReferenceById(id);
		return mappingUtils.mapToProductDto(entity);
	}

	@Override
	public List<ProductDto> getAll() {
		return productRepository.findAll().stream().map(mappingUtils::mapToProductDto).collect(Collectors.toList());
	}
}
