import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { HttpClientModule } from '@angular/common/http';

import { AppComponent } from './app.component';
import { ProductsComponent } from './products/products.component';
import { ProductComponent } from './products/product/product.component';
import { FiltersComponent } from './products/filters/filters.component';
import { ProductService } from './products/product.service';
import { ProfileComponent } from './profile/profile.component';
import { AppRoutingModule } from './app-routing/app-routing.module';
import { WelcomePageComponent } from './welcome-page/welcome-page.component';
import { ProductInfoComponent } from './products/product-info/product-info.component';
import { CartComponent } from './cart/cart.component';
import { AppService } from './app.service';
import { CartService } from './cart/cart.service';

@NgModule({
	declarations: [
		AppComponent,
		ProductsComponent,
		ProductComponent,
		FiltersComponent,
		ProfileComponent,
		WelcomePageComponent,
		ProductInfoComponent,
		CartComponent
	],
	imports: [
		BrowserModule,
		HttpClientModule,
		AppRoutingModule
	],
	providers: [ProductService, AppService, CartService],
	bootstrap: [AppComponent]
})
export class AppModule { }
