package ru.vasiljeva.dto;

import java.util.Set;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PersonalInfoDto {

	private Long id;
	private String firstName;
	private String lastName;
	private Set<ContactDto> contacts;
}
