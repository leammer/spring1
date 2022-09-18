import { Component } from '@angular/core';

@Component({
	selector: 'app-filters',
	templateUrl: './filters.component.html',
	styleUrls: ['./filters.component.css']
})
export class FiltersComponent {

	amountEntities: number[] = [10, 20, 50, 100];

	minPriceFilterHandler(value: number) {
		console.log(value);
	}

	maxPriceFilterHandler(value: number) {

	}
	searchFilterHandler(value: string) {

	}

	allFilterHandler() {
		console.log('all filters...');
	}

	amountEntitiesHandler(value: number) {
		console.log('!!!!' + value);
	}

}
