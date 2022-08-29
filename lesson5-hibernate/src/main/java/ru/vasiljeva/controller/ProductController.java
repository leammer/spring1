package ru.vasiljeva.controller;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import ru.vasiljeva.model.Product;
import ru.vasiljeva.service.ProductService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/product")
@Slf4j
@RequiredArgsConstructor
public class ProductController {
	@Autowired
	@Setter
	private ProductService productService;

	@ResponseBody
	@RequestMapping(method = RequestMethod.POST)
	public Product save(@RequestBody Product bean, HttpServletResponse response) {

		response.setStatus(HttpServletResponse.SC_CONFLICT);
		Product product = this.productService.getProductById(bean.getId());
		if (null == product) {
			response.setStatus(HttpServletResponse.SC_CREATED);
			this.productService.addProduct(bean);
		}
		return bean;
	}

	@ResponseBody
	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public void update(@PathVariable("id") Long id, @RequestBody Product bean, HttpServletResponse response) {

		response.setStatus(HttpServletResponse.SC_NOT_FOUND);
		if (null != this.productService.getProductById(id)) {
			bean.setId(id);
			this.productService.updateProduct(bean);
			response.setStatus(HttpServletResponse.SC_NO_CONTENT);
		}

	}

	@ResponseBody
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public void delete(@PathVariable("id") Long id, HttpServletResponse response) {

		response.setStatus(HttpServletResponse.SC_NOT_FOUND);
		Product bean = this.productService.getProductById(id);
		if (null != bean) {
			this.productService.removeProduct(id);
			response.setStatus(HttpServletResponse.SC_NO_CONTENT);
		}

	}

	@ResponseBody
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public Object get(@PathVariable("id") Long id, HttpServletResponse response) {
		log.info("!!!!!!!!!!!! get by id " + id);
		Product bean = this.productService.getProductById(id);
		response.setStatus(bean == null ? HttpServletResponse.SC_NOT_FOUND : HttpServletResponse.SC_OK);
		return bean;
	}

	@ResponseBody
	@RequestMapping(method = RequestMethod.GET)
	public List<Product> getAll(HttpServletResponse response) {
		log.info("!!!!!!!!!!!! get all");
		List<Product> products = this.productService.getAll();
		response.setStatus(products == null ? HttpServletResponse.SC_NOT_FOUND : HttpServletResponse.SC_OK);
		return products;
	}
}
