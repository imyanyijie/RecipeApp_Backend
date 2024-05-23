package com.imyanyijie.recipes.controller;

import com.imyanyijie.recipes.model.Recipe;
import com.imyanyijie.recipes.repository.RecipeRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/recipes")
public class RecipeController {

  @Autowired
  private RecipeRepository recipeRepository;

  @GetMapping
  public List<Recipe> listRecipe() {
    return recipeRepository.findAll();
  }

  @GetMapping("/{id}")
  public ResponseEntity<Recipe> getRecipe(@PathVariable long id)
    throws NotFoundException {
    Optional<Recipe> recipe = recipeRepository.findById(id);
    if (recipe.isPresent()) {
      return new ResponseEntity<>(recipe.get(), HttpStatus.OK);
    } else {
      throw new NotFoundException();
    }
  }

  @PostMapping
  public Recipe createRecipe(Recipe recipe) {
    return recipeRepository.save(recipe);
  }
}
