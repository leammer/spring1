import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-products',
  templateUrl: './products.component.html',
  styleUrls: ['./products.component.css']
})
export class ProductsComponent implements OnInit {

  status='';
  products: {id: number, name: string, price: number}[] = 
		  [{id:1, name:'Milk', price:10}, 
		   {id:2, name:'Onion', price:20}, 
		   {id:3, name:'Bread', price:30}];
 	
  constructor() { }

  ngOnInit(): void {
  }
  
  addProductHandler() {
	  console.log('add product...');
	  this.products.push({id:4, name:'Meat', price:40});
  }
}
