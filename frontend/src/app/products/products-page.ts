import { Product } from './product'

export interface ProductsPage {
	content: Product[],
	totalElements: number,
	totalPages: number,
	number: number,
	numberOfElements: number,
	pageable: {
		offset: number,
		pageNumber: number,
		pageSize: number
	}
}
