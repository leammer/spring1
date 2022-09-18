import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Item } from './item';
import { Cart } from './cart';

@Injectable()
export class CartService {

	constructor(private http: HttpClient) { }

	getCartContent(id: number) {
		return this.http
			.get<Cart>(`http://localhost:8090/app/cart/${id}`);
	}

	addItem(id: number, item: Item) {
		return this.http.post<Cart>(`http://localhost:8090/app/cart/${id}`, item);
	}

	updateItem(id: number, item: Item) {
		return this.http.put(`http://localhost:8090/app/cart/${id}`, item);
	}

	deleteItem(id: number, item: Item) {
		return this.http.delete(`http://localhost:8090/app/cart/${id}`);
	}
}
