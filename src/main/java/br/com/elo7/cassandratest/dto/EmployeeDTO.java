package br.com.elo7.cassandratest.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@RequiredArgsConstructor
@AllArgsConstructor
@Getter
@ToString
public class EmployeeDTO {
	private String id;
	private String firstName;
	private String lastName;
	private String email;
}
