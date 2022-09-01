package ru.vasiljeva;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.List;
import java.util.Scanner;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import ru.vasiljeva.dao.ItemDao;
import ru.vasiljeva.exceptions.EntityNotFoundException;
import ru.vasiljeva.model.Customer;
import ru.vasiljeva.model.Item;
import ru.vasiljeva.model.Product;
import ru.vasiljeva.service.CartService;
import ru.vasiljeva.service.ProductService;

/* Work with customer purchases */
public class Main3 {

	public static void main(String[] args) {
		ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
		CartService cartService = context.getBean("cartService", CartService.class);
		cartService.setCurrentCustomer(1l);
		
		Scanner scanner = new Scanner(System.in);
		while (true) {
			System.out
					.println("Choose operation:\n" + 
			                 "\tC - change current customer\n" + 
							 "\tP - print current customer's cart content\n" +
							 "\tA - add product to current customer's cart\n" + 
							 "\tD - remove product from current customer's cart");
			
			String operation = scanner.nextLine();
			switch (operation) {
			case "C": {
				System.out.println("\nChange current customer");
				System.out.println("\tEnter customer id: ");
				Long id = scanner.nextLong();
				try {
					cartService.setCurrentCustomer(id);
				} catch (EntityNotFoundException ex) {
					System.out.println(ex.getMessage());
				}
				break;
			}
			case "P": {
				System.out.println("\nCart content:");
				List<Item> list = cartService.getCartContent();
				if(list == null || list.isEmpty()) {
					System.out.println("Empty");
					break;
				}
				list.forEach(System.out::println);
				break;
			}
			case "A": {
				System.out.println("\nAdd product to cart");
				System.out.println("\tEnter product id: ");
				Long id = scanner.nextLong();
				System.out.println("\tEnter quantity: ");
				Long quantity = scanner.nextLong();
				cartService.addProductToCart(id, quantity);
				System.out.println("Product added successfully");
				break;
			}
			case "D": {
				System.out.println("\nRemove product from cart");
				System.out.println("\tEnter product id: ");
				Long id = scanner.nextLong();
				cartService.removeProductFromCart(id);
				System.out.println("Product removed successfully");
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
