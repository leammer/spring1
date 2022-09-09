package ru.vasiljeva.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.util.MultiValueMap;

import com.querydsl.core.BooleanBuilder;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import ru.vasiljeva.dto.ProductDto;
import ru.vasiljeva.exceptions.EntityNotFoundException;
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

	private int pageSize = 10;

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
	public Page<ProductDto> getAll(MultiValueMap<String, String> params) {
		QProduct product = QProduct.product;
		BooleanBuilder predicate = new BooleanBuilder();

		if (params.containsKey("search")) {
			predicate.and(product.name.containsIgnoreCase(params.getFirst("search")));
		}
		if (params.containsKey("minPrice") && !params.getFirst("minPrice").isBlank()) {
			predicate.and(product.cost.goe(Integer.parseInt(params.getFirst("minPrice"))));
		}
		if (params.containsKey("maxPrice")&& !params.getFirst("maxPrice").isBlank()) {
			predicate.and(product.cost.loe(Integer.parseInt(params.getFirst("maxPrice"))));
		}

		Sort sort = Sort.unsorted();
		if (params.containsKey("sort")) {
			String sortValue = params.getFirst("sort");
			switch (sortValue) {
			case "priceUp":
				sort = Sort.by(Sort.Direction.ASC, "cost");
				break;
			case "priceDown":
				sort = Sort.by(Sort.Direction.DESC, "cost");
				break;
			case "titleUp":
				sort = Sort.by(Sort.Direction.ASC, "name");
				break;
			case "titleDown":
				sort = Sort.by(Sort.Direction.DESC, "name");
				break;
			}
		}

		int page = 0;
		if (params.containsKey("pageSize")) {
			this.pageSize = Integer.parseInt(params.getFirst("pageSize"));
		}
		if (params.containsKey("page")) {
			page = Integer.parseInt(params.getFirst("page"));
			if (page > 0) {
				page -= 1;
			}
		}

		//@formatter:off
		return this.productRepository
				.findAll(predicate, PageRequest.of(page, this.pageSize, sort))
				.map(mappingUtils::mapToProductDto);
		//@formatter:on
	}
}
