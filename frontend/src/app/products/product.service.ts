import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Product } from './product';
import { ProductsPage } from './products-page';

@Injectable()
export class ProductService {

	constructor(private http: HttpClient) { }

	getProducts() {
		return this.http
			.get<ProductsPage>(`http://localhost:8090/app/products`);
	}

	getProductById(id: number) {
		return this.http
			.get<Product>(`http://localhost:8090/app/products/${id}`);
	}

	addProduct(product: Product) {
		return this.http.post<Product>(`http://localhost:8090/app/products`, product);
	}

	updateProduct(id: number, product: Product) {
		return this.http.put<Product>(`http://localhost:8090/app/products/${id}`, product);
	}

	deleteProduct(id: number) {
		return this.http.delete(`http://localhost:8090/app/products/${id}`);
	}
}
