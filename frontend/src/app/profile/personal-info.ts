export interface PersonalInfo {
  id?: number;
  first_name: string;
  last_name: string;
  contacts: {
    id?: number;
    type: string;
    value: string;
  }[];
}
