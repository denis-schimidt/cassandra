package br.com.elo7.cassandratest.converter;

import org.springframework.stereotype.Component;

import br.com.elo7.cassandratest.dto.EmployeeDTO;
import br.com.elo7.cassandratest.model.Employee;

@Component
public class EmployeeConverter {

	public EmployeeDTO toDto(Employee e) {
		return new EmployeeDTO(e.getId(), e.getFirstName(), e.getLastName(), e.getEmail());
	}
}
