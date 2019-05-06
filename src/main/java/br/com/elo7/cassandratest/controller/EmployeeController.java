package br.com.elo7.cassandratest.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.elo7.cassandratest.converter.EmployeeConverter;
import br.com.elo7.cassandratest.dto.EmployeeDTO;
import br.com.elo7.cassandratest.model.Employee;
import br.com.elo7.cassandratest.repository.EmployeeRepository;

@RestController
public class EmployeeController{

	@Autowired
	private EmployeeConverter converter;
	@Autowired
	private EmployeeRepository employeeRepository;
	@Autowired
	private Executor executor;

	@GetMapping(value = "/healthcheck", produces = "application/json; charset=utf-8")
	public String getHealthCheck()
	{
		return "{ \"isWorking\" : true }";
	}

	@GetMapping("/employees")
	public List<Employee> getEmployees() {
		Iterable<Employee> result = employeeRepository.findAll();
		List<Employee> employeesList = new ArrayList<Employee>();
		result.forEach(employeesList::add);
		return employeesList;
	}

	@GetMapping("/employee/{id}")
	public Optional<Employee> getEmployee(@PathVariable String id){
		Optional<Employee> emp = employeeRepository.findById(id);
		return emp;
	}

	@PutMapping("/employee/{id}")
	public Optional<Employee> updateEmployee(@RequestBody Employee newEmployee, @PathVariable String id)
	{
		Optional<Employee> optionalEmp = employeeRepository.findById(id);
		if (optionalEmp.isPresent()) {
			Employee emp = optionalEmp.get();
			emp.setFirstName(newEmployee.getFirstName());
			emp.setLastName(newEmployee.getLastName());
			emp.setEmail(newEmployee.getEmail());
			employeeRepository.save(emp);
		}
		return optionalEmp;
	}

	@DeleteMapping(value = "/employee/{id}", produces = "application/json; charset=utf-8")
	public String deleteEmployee(@PathVariable String id) {
		Boolean result = employeeRepository.existsById(id);
		employeeRepository.deleteById(id);
		return "{ \"success\" : "+ (result ? "true" : "false") +" }";
	}

	@PostMapping("/employee")
	public Employee addEmployee(@RequestBody Employee newEmployee)
	{
		String id = String.valueOf(new Random().nextInt());
		Employee emp = new Employee(id, newEmployee.getFirstName(), newEmployee.getLastName(), newEmployee.getEmail());
		employeeRepository.save(emp);
		return emp;
	}

	@GetMapping("/employee/async/{id}")
	public CompletableFuture<EmployeeDTO> test(@PathVariable String id){
		final CompletableFuture<Employee> futureEmployee = employeeRepository.findOneById(id);

		return futureEmployee.thenApplyAsync(Employee::toDto, executor);
	}
}