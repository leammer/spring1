import { Component, OnInit, ViewChild } from '@angular/core';
import { NgForm } from '@angular/forms';
import { PASSWORD_REGEX } from '../global-variable';

@Component({
	selector: 'app-login',
	templateUrl: './login.component.html',
	styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {
	@ViewChild('form')
	form!: NgForm;

	regex = PASSWORD_REGEX;

	constructor() { }

	ngOnInit(): void {

	}

	onSubmit(username: string, password: string): void {
		console.log(username + "  " + password);
	}
}
