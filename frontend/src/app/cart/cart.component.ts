import { Component, OnInit } from '@angular/core';
import { CartService } from './cart.service';
import { Item } from './item';
import { Cart } from './cart';

@Component({
  selector: 'app-cart',
  templateUrl: './cart.component.html',
  styleUrls: ['./cart.component.css']
})
export class CartComponent implements OnInit {
  id:any;
  content: Item[] = [];

  constructor(private cartService: CartService) { }

  ngOnInit(): void {
    this.cartService.getCartContent(1)
      .subscribe( (response: Cart) => { console.log(response);
        this.id = response.id;
        this.content = response.cart;
      });
  }

}
