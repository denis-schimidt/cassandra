package br.com.elo7.cassandratest.repository;

import java.util.concurrent.CompletableFuture;

import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.scheduling.annotation.Async;

import br.com.elo7.cassandratest.model.Employee;

public interface EmployeeRepository extends CassandraRepository<Employee, String> {

	@Async
	CompletableFuture<Employee> findOneById(String id);
}
