package br.com.elo7.cassandratest.infra;

import java.util.concurrent.Executor;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
public class ExecutorConfiguration {

	@Bean
	@Primary
	public Executor asyncExecutor(@Value("${async.executor.coreThreads:20}") int coreThreads,
								  @Value("${async.executor.maxThreads:300}") int maxThreads) {
		ThreadPoolExecutor executor = new ThreadPoolExecutor(coreThreads, maxThreads, 60, TimeUnit.SECONDS, new SynchronousQueue<Runnable>());
		executor.prestartAllCoreThreads();
		return executor;
	}
}
