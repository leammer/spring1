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

@NgModule({
  declarations: [
    AppComponent,
    ProductsComponent,
    ProductComponent,
    FiltersComponent,
    ProfileComponent,
    WelcomePageComponent,
    ProductInfoComponent
  ],
  imports: [
    BrowserModule,
    HttpClientModule,
    AppRoutingModule
  ],
  providers: [ProductService],
  bootstrap: [AppComponent]
})
export class AppModule { }
