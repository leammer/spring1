package ru.vasiljeva.service.impl;

import java.util.List;
import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.MultiValueMap;

import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;

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

	@Autowired
	private EntityManager em;

	private int perSize = 10;

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
	public List<ProductDto> getAll(MultiValueMap<String, String> params) {
		QProduct product = QProduct.product;
		JPAQuery<Product> query = new JPAQueryFactory(em).selectFrom(product);

		if (params.containsKey("search")) {
			query.where(product.name.containsIgnoreCase(params.getFirst("search")));
		}
		if (params.containsKey("minPrice")) {
			query.where(product.cost.goe(Integer.parseInt(params.getFirst("minPrice"))));
		}
		if (params.containsKey("maxPrice")) {
			query.where(product.cost.loe(Integer.parseInt(params.getFirst("maxPrice"))));
		}

		if (params.containsKey("sort")) {
			String sortValue = params.getFirst("sort");
			switch (sortValue) {
			case "priceUp":
				query.orderBy(product.cost.asc());
				break;
			case "priceDown":
				query.orderBy(product.cost.desc());
				break;
			case "titleUp":
				query.orderBy(product.name.asc());
				break;
			case "titleDown":
				query.orderBy(product.name.desc());
				break;
			}
		}

		int page = 0;
		if (params.containsKey("perSize")) {
			this.perSize = Integer.parseInt(params.getFirst("perSize"));
		}
		if (params.containsKey("page")) {
			page = Integer.parseInt(params.getFirst("page"));
			if (page > 0) {
				page -= 1;
			}
		}

		//@formatter:off 
		return query
				.limit(perSize)
				.offset(page * perSize)
				.fetch()
				.stream()
				.map(mappingUtils::mapToProductDto)
				.toList();
		//@formatter:on
	}
}
