import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { ProductService } from '../product.service';
import { Product } from '../product'

@Component({
  selector: '[app-product]',
  templateUrl: './product.component.html',
  styleUrls: ['./product.component.css']
})
export class ProductComponent implements OnInit {

  id: number = 0;
  product: Product = {title: '', price: 0 };

  constructor(private route: ActivatedRoute, private producrService: ProductService) { }

  ngOnInit(): void {
	  this.id = this.route.snapshot.params['id'];
	  this.producrService.getProductById(this.id)
      .subscribe( (product: Product) => {console.log(product);
         this.product = product;
      });
  }

}
