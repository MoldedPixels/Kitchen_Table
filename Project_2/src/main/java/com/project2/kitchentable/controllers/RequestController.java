package com.project2.kitchentable.controllers;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.datastax.oss.driver.api.core.uuid.Uuids;
import com.project2.kitchentable.beans.Recipe;
import com.project2.kitchentable.beans.Requests;
import com.project2.kitchentable.services.RecipeService;
import com.project2.kitchentable.services.RequestService;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping(value = "/request")
public class RequestController {

	private RequestService requestService;
	private RecipeService recipeService;

	@Autowired
	public void setRequestService(RequestService requestService) {
		this.requestService = requestService;
	}

	@Autowired
	public void setRecipeService(RecipeService recipeService) {
		this.recipeService = recipeService;
	}
	@PostMapping("/new")
	public Mono<ResponseEntity<Requests>> addRequest(@RequestBody Requests q) {
		q.setRequestId(Uuids.timeBased());
		if (q.getRecipeId() == (null)) {
			q.setRecipeId(Uuids.timeBased());
		}
		return requestService.addRequest(q).map(request -> ResponseEntity.status(201).body(request))
				.onErrorResume(error -> Mono.just(ResponseEntity.badRequest().body(q)));
	}
	
	@GetMapping("/approve")
	public Mono<ResponseEntity<Requests>> approveRequest(@RequestBody UUID qID) {
		Requests q = requestService.getRequestById(qID).block();
		if (recipeService.getRecipeByID(q.getRecipeId()) != null)
		{
			Recipe r = recipeService.getRecipeByID(q.getRecipeId()).block();
			r.setCuisine(q.getCuisine());
			r.setName(q.getName());
			r.setIngredients(q.getIngredients());
			recipeService.updateRecipe(r);
		}
		return requestService.approve(q).map(request -> ResponseEntity.status(201).body(request))
				.onErrorResume(error -> Mono.just(ResponseEntity.badRequest().body(q)));
	}
	@GetMapping("/reject")
	public Mono<ResponseEntity<Requests>> rejectRequest(@RequestBody UUID qID) {
		Requests q = requestService.getRequestById(qID).block();
		return requestService.reject(q).map(request -> ResponseEntity.status(201).body(request))
				.onErrorResume(error -> Mono.just(ResponseEntity.badRequest().body(q)));
	}

}