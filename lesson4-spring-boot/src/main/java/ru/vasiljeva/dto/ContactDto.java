package ru.vasiljeva.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.vasiljeva.model.ContactType;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ContactDto {

	private Long id;
	private ContactType type;
	private String value;
}
