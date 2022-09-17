import { Product } from './product'

export interface ProductsPage {
	content: Product[],
	totalElements: number,
	totalPages: number
}
