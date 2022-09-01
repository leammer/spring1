package ru.vasiljeva;

import java.math.BigDecimal;
import java.util.List;
import java.util.Scanner;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import ru.vasiljeva.model.Product;
import ru.vasiljeva.service.ProductService;

//@formatter:off
/** Work with Grocery Catalogue:
* C - create new product
* R - read product info by id
* U - update product info
* D - delete product by id
* A - print full catalogue
* */
//@formatter:on
public class Main2 {

	public static void main(String[] args) {
		ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
		ProductService productService = context.getBean("productService", ProductService.class);

		Scanner scanner = new Scanner(System.in);
		while (true) {
			System.out.println("Choose operation: ");
			System.out.println("\tA - print full catalogue");
			System.out.println("\tC - add new product");
			System.out.println("\tR - get product info");
			System.out.println("\tU - edit product info");
			System.out.println("\tD - remove product");

			String operation = scanner.nextLine();
			switch (operation) {
			case "A": {
				List<Product> products = productService.getAll();
				System.out.println("\nGlocery catalogue:");
				products.forEach(System.out::println);
				break;
			}
			case "C": {

				System.out.println("\nCreate new product");
				System.out.println("\tEnter product title: ");
				String title = scanner.nextLine();

				System.out.println("\tEnter cost: ");
				Double cost = scanner.nextDouble();

				productService.addProduct(new Product(title, new BigDecimal(cost)));
				System.out.println("Product added successufully");
				break;
			}
			case "R": {
				System.out.println("\nRead product info");
				System.out.println("\tEnter product id: ");
				Long id = scanner.nextLong();
				Product product = productService.getProductById(id);
				System.out.println("Product info: " + product);
				break;
			}
			case "U": {
				System.out.println("\nUpdate product info");
				System.out.println("\tEnter product id: ");
				String id = scanner.nextLine();
				if (id.isBlank()) {
					System.out.println("id couldn't be blank");
					break;
				}
				Product product = productService.getProductById(Long.valueOf(id));
				System.out.println("Product info: " + product);

				System.out.println("\tEnter new title:");
				String title = scanner.nextLine();
				if (!title.isBlank()) {
					product.setName(title);
				}

				System.out.println("\tEnter new cost:");
				String cost = scanner.nextLine();
				if (!cost.isBlank()) {
					product.setCost(new BigDecimal(cost));
				}

				productService.updateProduct(product);

				break;
			}
			case "D": {
				System.out.println("\nDelete product");
				System.out.println("\tEnter product id: ");
				Long id = scanner.nextLong();
				productService.removeProduct(id);
				System.out.println("Product removed successufully");
				break;
			}
			}

			System.out.println("Enter \"end\" to exit");
			if (scanner.nextLine().equals("end")) {
				return;
			}
		}

	}

}
