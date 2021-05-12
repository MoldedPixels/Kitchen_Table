package com.project2.kitchentable.services;

import java.util.UUID;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import com.project2.kitchentable.beans.Recipe;
import com.project2.kitchentable.data.ReactiveRecipeRepo;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import org.springframework.beans.factory.annotation.Autowired;

@Service
public class RecipeServiceImpl implements RecipeService {
	private static Logger log = LogManager.getLogger(RecipeServiceImpl.class);

	@Autowired
	private ReactiveRecipeRepo recipeRepo;

	@Override
	public Mono<Recipe> addRecipe(Recipe r) {
		return recipeRepo.insert(r);
	}

	@Override
	public Flux<Recipe> getRecipes() {
		return recipeRepo.findAll();
	}

	@Override
	public Mono<Recipe> updateRecipe(Recipe r) {
		return recipeRepo.save(r);
	}

	@Override
	public Mono<Recipe> getRecipeById(UUID id) {
		String recipeId = id.toString();

		return recipeRepo.findById(recipeId);
	}

	@Override
	public Mono<Void> removeRecipe(Recipe r) {
		return recipeRepo.delete(r);
	}

	@Override
	public Mono<Recipe> getRecipeByID(UUID id) {
		// TODO Auto-generated method stub
		return null;
	}
}
