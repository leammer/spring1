import { Component } from '@angular/core';

@Component({
	selector: 'app-root',
	templateUrl: './app.component.html',
	styleUrls: ['./app.component.css']
})
export class AppComponent {
	title = 'Grocery EShop';
	isLogged = false;

	check() {
		this.isLogged = !this.isLogged;
	}
}
