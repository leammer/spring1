#!/bin/bash

echo $'Get full product list\n'

curl http://localhost:8090/app/products

echo $'\n\nGet product by id = 5\n'
curl http://localhost:8090/app/products/5

echo $'\n\nPost new product\n{"title":"new product", "price":"12.33"}\n'
curl -d '{"title":"new product", "price":"12.33"}' -H "Content-Type: application/json" -X POST http://localhost:8090/app/products

echo $'\n\nDelete product by id = 21\n'
curl http://localhost:8090/app/products/delete/21

echo $'\n\nGet products where 300 <= price <= 500'
curl -v 'http://localhost:8090/app/products?minPrice=300&maxPrice=500'