package ru.vasiljeva.dto;

import java.time.Instant;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonProperty;

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
	@JsonProperty("first_name")
	private String firstName;
	@JsonProperty("last_name")
	private String lastName;
	@JsonProperty("passport_number")
	private String passportNumber;
	@JsonProperty("passport_serial")
	private String passportSerial;
	@JsonProperty("passport_issuedBy")
	private String passportIssuedBy;
	@JsonProperty("passport_issuedDate")
	private Instant passportIssuedDate;

	private Set<ContactDto> contacts;
}
