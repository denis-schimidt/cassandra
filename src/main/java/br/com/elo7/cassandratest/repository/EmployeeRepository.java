package br.com.elo7.cassandratest.repository;

import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.scheduling.annotation.Async;
import org.springframework.util.concurrent.ListenableFuture;

import br.com.elo7.cassandratest.model.Employee;

public interface EmployeeRepository extends CassandraRepository<Employee, String> {

	@Async
	ListenableFuture<Employee> findOneById(String id);
}
