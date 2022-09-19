export class User {
	constructor(public id: number | null,
		public username: string,
		public roles: string[],
		public password?: string) {
	}
}
