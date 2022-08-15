package ru.vasiljeva;

import ru.vasiljeva.persist.Product;
import ru.vasiljeva.persist.ProductRepository;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;

@WebServlet(urlPatterns = "/product/*")
public class ProductServlet extends HttpServlet {

	private final String BAD_REQUEST_MESSAGE = "The request must contain the product number in a numeric value";
	private final String NOT_FOUND_MESSAGE = "There is no such product";

	private ProductRepository productRepository;

	@Override
	public void init() throws ServletException {
		this.productRepository = new ProductRepository();

		InputStream is = getClass().getClassLoader().getResourceAsStream("products.txt");
		try (InputStreamReader streamReader = new InputStreamReader(is, StandardCharsets.UTF_8);
				BufferedReader reader = new BufferedReader(streamReader)) {

			String line;
			while ((line = reader.readLine()) != null) {
				String s[] = line.split("\\s");
				productRepository.insert(new Product(s[0], Float.parseFloat(s[1])));

			}

		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		PrintWriter wr = resp.getWriter();

		if (req.getPathInfo() == null || req.getPathInfo().equals("/")) {
			printAllProducts(req.getRequestURL().toString(), wr);
			return;
		}
		
		Long id = getIdFromInfo(req.getPathInfo());
		if (id == -1) {
			resp.sendError(HttpServletResponse.SC_BAD_REQUEST, BAD_REQUEST_MESSAGE);
			return;
		}

		Product product = productRepository.findById(id);
		if (product == null) {
			resp.sendError(HttpServletResponse.SC_NOT_FOUND, NOT_FOUND_MESSAGE);
			return;
		}

		wr.println("<h1>Product info</h1>");
		
		wr.println("<p>Title: " + product.getTitle() + "</p>");
		wr.println("<p>Cost: " + product.getCost() + "</p>");
	}

	private void printAllProducts(String requestURL, PrintWriter wr) {

		wr.println("<h1>Product list</h1>");

		wr.println("<table>");
		wr.println("<tr>");
		wr.println("<th>Id</th>");
		wr.println("<th>Title</th>");
		wr.println("<th>Cost</th>");
		wr.println("</tr>");

		for (Product product : productRepository.findAll()) {
			wr.println("<tr>");
			wr.println("<td><a href = \""+requestURL+"/"+product.getId()+"\">" + product.getId() + "</a></td>");
			wr.println("<td>" + product.getTitle() + "</td>");
			wr.println("<td>" + product.getCost() + "</td>");
			wr.println("</tr>");
		}

		wr.println("</table>");
	}

	private static Long getIdFromInfo(String str) {
		try {
			Long id = Long.parseLong(str.substring(1));
			return id;
		} catch (NullPointerException | NumberFormatException e) {
			return (long) -1;
		}
	}
}