package com.project2.kitchentable.data;

import java.util.UUID;

import org.springframework.data.cassandra.repository.ReactiveCassandraRepository;

import com.project2.kitchentable.beans.User;

import reactor.core.publisher.Mono;

import org.springframework.stereotype.Repository;

@Repository
public interface ReactiveUserRepo extends ReactiveCassandraRepository<User, String> {
	
}
