import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { Routes, RouterModule } from '@angular/router';

import { ProductsComponent } from '../products/products.component';
import { ProductComponent } from '../products/product/product.component';
import { ProfileComponent } from '../profile/profile.component';
import { WelcomePageComponent } from '../welcome-page/welcome-page.component';

const appRoutes: Routes = [
  {
    path: "",
    component: WelcomePageComponent
  }, {
    path: "app/products",
    component: ProductsComponent
  }, {
    path: "app/products/:id",
    component: ProductComponent
  }, {
    path: "app/personal",
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
