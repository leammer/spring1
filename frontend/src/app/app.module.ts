import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { HttpClientModule } from '@angular/common/http';

import { AppComponent } from './app.component';
import { ProductsComponent } from './products/products.component';
import { ProductComponent } from './products/product/product.component';
import { FiltersComponent } from './products/filters/filters.component';
import { ProductService } from './products/product.service';

@NgModule({
  declarations: [
    AppComponent,
    ProductsComponent,
    ProductComponent,
    FiltersComponent
  ],
  imports: [
    BrowserModule,
    HttpClientModule
  ],
  providers: [ProductService],
  bootstrap: [AppComponent]
})
export class AppModule { }
