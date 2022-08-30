package ru.vasiljeva.controller;

import java.math.BigDecimal;
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
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@Controller
@RequestMapping("/product")
@Slf4j
@RequiredArgsConstructor
public class ProductController {
	@Autowired
	@Setter
	private ProductService productService;

	@GetMapping
	public String getAll(Model model, HttpServletResponse response) {
		log.info("get full product list ");
		List<Product> products = this.productService.getAll();
		response.setStatus(products == null ? HttpServletResponse.SC_NOT_FOUND : HttpServletResponse.SC_OK);
		model.addAttribute("products", products);
		return "product";
	}
	
	@GetMapping("/{id}")
	public String getProductById(@PathVariable("id") long id, Model model, HttpServletResponse response) {
		log.info("get product by id " + id);

		Product product = this.productService.getProductById(id);
		if (product == null) {
			model.addAttribute("message", "Product by such id couldn't be found");
			response.setStatus(HttpServletResponse.SC_NOT_FOUND);
			return "error_form";
		}
		model.addAttribute("product", product);
		return "product_form";
	}

	@GetMapping("/new")
	public String addNewProduct(Model model) {
		return "new_product_form";
	}

	@PostMapping
	public String saveOrUpdate(@RequestBody Product bean, HttpServletResponse response) {
		if (null == bean.getId()) {
			log.info("Save product " + bean);
			
			response.setStatus(HttpServletResponse.SC_CREATED);
			this.productService.addProduct(bean);
		} else {
			log.info("Update product " + bean);
			response.setStatus(HttpServletResponse.SC_NOT_FOUND);
			if (null != this.productService.getProductById(bean.getId())) {
				this.productService.updateProduct(bean);
				response.setStatus(HttpServletResponse.SC_NO_CONTENT);
			}
		}
		return "redirect:/product";
	}

	@DeleteMapping("/{id}")
	public String remove(@PathVariable("id") Long id, HttpServletResponse response) {
		log.info("Remove product by id " + id);
		
		response.setStatus(HttpServletResponse.SC_NOT_FOUND);
		Product bean = this.productService.getProductById(id);
		if (null != bean) {
			this.productService.removeProduct(id);
			response.setStatus(HttpServletResponse.SC_NO_CONTENT);
		}
		return "redirect:/product";
	}
}
