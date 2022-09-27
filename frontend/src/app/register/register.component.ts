import { Component, OnInit } from '@angular/core';
import { AbstractControl, FormBuilder, FormControl, FormGroup, ValidatorFn, Validators } from '@angular/forms';
import { PASSWORD_REGEX } from '../global-variable';

@Component({
	selector: 'app-register',
	templateUrl: './register.component.html',
	styleUrls: ['./register.component.css']
})
export class RegisterComponent implements OnInit {
	form: FormGroup;

	constructor() {
		this.form = new FormGroup({
			username: new FormControl('', Validators.required),
			password: new FormControl('', [Validators.required, Validators.pattern(PASSWORD_REGEX)]),
			confirmPassword: new FormControl('')
		});
		this.form.validator = this.matchValidator('password', 'confirmPassword');
	}

	ngOnInit(): void {
	}

	onSubmit() {
		console.log(this.form);
	}

	matchValidator(key: string, confirmationKey: string): any {
		return (group: FormGroup) => {
			const input = group.controls[key];
			const confirmationInput = group.controls[confirmationKey];
			return confirmationInput.setErrors(
				input.value !== confirmationInput.value ? { notMatched: true } : null
			);
		};
	}
}

