package com.project2.kitchentable.data;

import java.util.UUID;

import org.springframework.data.cassandra.repository.ReactiveCassandraRepository;
import org.springframework.stereotype.Repository;
import com.project2.kitchentable.beans.Kitchen;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
public interface ReactiveKitchenRepo extends ReactiveCassandraRepository<Kitchen, String>{
<<<<<<< HEAD

=======
	
>>>>>>> 18d5fe1bda32e734144ccf32dea8db27a0f196b6
}
