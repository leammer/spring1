import { Component, OnInit } from '@angular/core';
import { ProductService } from './product.service';
import { Product } from './product'
import { ProductsPage } from './products-page'

@Component({
  selector: 'app-products',
  templateUrl: './products.component.html',
  styleUrls: ['./products.component.css']
})
export class ProductsComponent implements OnInit {

  status='';
  products: Product[] = [];

  constructor(private producrService: ProductService) { }

  ngOnInit(): void {
    this.producrService.getProducts()
      .subscribe( (page: ProductsPage) => { this.products = page.content} );
  }
  
  addProductHandler() {
	  console.log('add product...');
	  //this.products.push({id:4, name:'Meat', price:40});
  }
}
