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
import ru.vasiljeva.exceptions.ExceptionType;
import ru.vasiljeva.exceptions.ServiceException;
import ru.vasiljeva.model.QProduct;
import ru.vasiljeva.repository.ProductRepository;
import ru.vasiljeva.service.ProductService;
import ru.vasiljeva.utils.MappingUtils;
import ru.vasiljeva.utils.SortingParameter;

@Service
@RequiredArgsConstructor
@Setter
public class ProductServiceImpl implements ProductService {

	private static final String PAGE = "page";
	private static final String PAGE_SIZE = "pageSize";
	private static final String SORT = "sort";
	private static final String MAX_PRICE = "maxPrice";
	private static final String MIN_PRICE = "minPrice";
	private static final String SEARCH = "search";

	@Autowired
	private ProductRepository productRepository;

	@Autowired
	private MappingUtils mappingUtils;

	@Override
	public ProductDto addProduct(ProductDto dto) {
		if (this.productRepository.findById(dto.getId()) != null) {
			throw new ServiceException(ExceptionType.ALREADY_EXISTS, "product");
		}
		return mappingUtils.mapToDto(this.productRepository.saveAndFlush(mappingUtils.mapToEntity(dto)));
	}

	@Override
	public ProductDto updateProduct(ProductDto dto) {
		return mappingUtils.mapToDto(this.productRepository.saveAndFlush(mappingUtils.mapToEntity(dto)));
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
				.map(mappingUtils::mapToDto)
				.orElseThrow(() -> new ServiceException(ExceptionType.NOT_FOUND, "product", "id=" + id, ""));
		//@formatter:on
	}

	@Override
	public Page<ProductDto> getAll(MultiValueMap<String, String> params) {
		QProduct product = QProduct.product;
		BooleanBuilder predicate = new BooleanBuilder();

		if (params.containsKey(SEARCH)) {
			predicate.and(product.name.containsIgnoreCase(params.getFirst(SEARCH)));
		}
		if (params.containsKey(MIN_PRICE) && !params.getFirst(MIN_PRICE).isBlank()) {
			predicate.and(product.cost.goe(Integer.parseInt(params.getFirst(MIN_PRICE))));
		}
		if (params.containsKey(MAX_PRICE) && !params.getFirst(MAX_PRICE).isBlank()) {
			predicate.and(product.cost.loe(Integer.parseInt(params.getFirst(MAX_PRICE))));
		}

		Sort sort = SortingParameter.getSortingParameter(params.getFirst(SORT)).getSort();

		int pageSize = 10;
		if (params.containsKey(PAGE_SIZE)) {
			pageSize = Integer.parseInt(params.getFirst(PAGE_SIZE));
		}

		int page = 0;
		if (params.containsKey(PAGE)) {
			page = Integer.parseInt(params.getFirst(PAGE));
		}

		//@formatter:off
		return this.productRepository
				.findAll(predicate, PageRequest.of(page, pageSize, sort))
				.map(mappingUtils::mapToDto);
		//@formatter:on
	}
}
