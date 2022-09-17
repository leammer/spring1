import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { Routes, RouterModule } from '@angular/router';

import { ProductsComponent } from '../products/products.component';
import { ProductComponent } from '../products/product/product.component';
import { CartComponent } from '../cart/cart.component';
import { ProfileComponent } from '../profile/profile.component';
import { WelcomePageComponent } from '../welcome-page/welcome-page.component';
import { ProductInfoComponent } from '../products/product-info/product-info.component';

const appRoutes: Routes = [
  {
    path: "",
    component: WelcomePageComponent
  }, {
    path: "catalog",
    component: ProductsComponent
  }, {
    path: "info/:id",
    component: ProductInfoComponent
  }, {
    path: "edit/:id",
    component: ProductComponent
  }, {
    path: "cart",
    component: CartComponent
  }, {
    path: "profile",
    component: ProfileComponent
  }
]

@NgModule({
  declarations: [],
  imports: [
    CommonModule,
    RouterModule.forRoot(appRoutes)
  ],
  exports: [ RouterModule ]
})
export class AppRoutingModule { }
