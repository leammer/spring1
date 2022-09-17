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
  totalPages: number = 1;
  totalElements: number = 0;
  pageNumber: number = 0;
  fromEntity: number = 0;
  toEntity: number = 0;
  paginator: number[] = [];
  
  constructor(private producrService: ProductService) { }

  ngOnInit(): void {
    this.producrService.getProducts()
      .subscribe( (page: ProductsPage) => { console.log(page);
         this.products = page.content;
         this.totalPages = page.totalPages;
         this.totalElements = page.totalElements;
         this.pageNumber = page.number;
         this.fromEntity = page.pageable.offset+1;
         this.toEntity = page.pageable.offset + page.numberOfElements;
         this.paginator = new Array(page.totalPages).fill(0).map((n, index) => index + 1);
      });
  }
  
  addProductHandler() {
	  console.log('add product...');
	  //this.products.push({id:4, name:'Meat', price:40});
  }
  
  updateProductHandler(product: Product) {
     /*
    this.producrService.updateProduct(id)
      .subscribe( (product: Products) => { 
      });*/
  }
  
  deleteProductHandler(id: any) {
	  this.producrService.deleteProduct(id)
      .subscribe((response) => {
        this.products = this.products.filter(p => p.id !== id);
      });
  }
}
