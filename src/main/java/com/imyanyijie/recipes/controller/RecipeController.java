package com.imyanyijie.recipes.controller;

import com.imyanyijie.recipes.model.Recipe;
import com.imyanyijie.recipes.service.RecipeService;
import dto.CreateRecipeDTO;
import dto.ShortRecipeDTO;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/recipes")
public class RecipeController {

  @Autowired
  private RecipeService recipeService;

  @GetMapping
  public List<Recipe> listRecipe() {
    return recipeService.getAllRecipes();
  }

  @GetMapping("/{id}")
  public ResponseEntity<Recipe> getRecipe(@PathVariable long id)
    throws NotFoundException {
    return new ResponseEntity<>(recipeService.getRecipeByID(id), HttpStatus.OK);
  }

  @GetMapping("/short")
  public ResponseEntity<List<ShortRecipeDTO>> getShortRecipes()
    throws NotFoundException {
    return new ResponseEntity<>(recipeService.getShortRecipes(), HttpStatus.OK);
  }

  @PostMapping
  public Recipe createRecipe(@RequestBody CreateRecipeDTO recipeRequest) {
    return recipeService.creatRecipe(recipeRequest);
  }
}
