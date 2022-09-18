import { Component, OnInit } from '@angular/core';
import { AppService } from '../app.service';
import { PersonalInfo } from './personal-info';

@Component({
	selector: 'app-profile',
	templateUrl: './profile.component.html',
	styleUrls: ['./profile.component.css']
})
export class ProfileComponent implements OnInit {

	info: PersonalInfo = {
		first_name: '',
		last_name: '',
		contacts: []
	}

	constructor(private appService: AppService) { }

	ngOnInit(): void {
		this.appService.getPersonalInfo(1)
			.subscribe((info: PersonalInfo) => {
				console.log(info);
				this.info = info;
			});
	}

}
