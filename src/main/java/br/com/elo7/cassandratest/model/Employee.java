package br.com.elo7.cassandratest.model;

import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.Table;

import br.com.elo7.cassandratest.dto.EmployeeDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
@Table
public class Employee {
	@PrimaryKey
	private @NonNull String id;
	private @NonNull String firstName;
	private @NonNull String lastName;
	private @NonNull String email;

	public EmployeeDTO toDto() {
		return new EmployeeDTO(id, firstName, lastName, email);
	}
}
