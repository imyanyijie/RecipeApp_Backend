package com.imyanyijie.service;

import com.imyanyijie.recipes.model.Recipe;
import com.imyanyijie.recipes.repository.RecipeRepository;
import exception.BadRequestException;
import exception.ItemNotFoundException;
import java.util.List;
import java.util.Optional;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

@Service
@Log4j2
public class RecipeService {

  RecipeRepository recipeRepository;

  public List<Recipe> getAllRecipes() {
    return recipeRepository.findAll();
  }

  public Recipe getRecipeByID(long id) {
    Optional<Recipe> recipeOption = recipeRepository.findById(id);
    if (!recipeOption.isPresent()) {
      throw new ItemNotFoundException(
        "The recipe with id " + id + " is not found"
      );
    }
    return recipeOption.get();
  }

  public Recipe creatRecipe(Recipe recipe) {
    log.debug("Trying to insert " + recipe.getName());
    if (recipe.getRecipeID() != null) {
      throw new BadRequestException("Item already exist");
    }
    return recipeRepository.save(recipe);
  }

  public void deleteRecipe(long id) {
    Optional<Recipe> recipeOption = recipeRepository.findById(id);
    if (!recipeOption.isPresent()) {
      throw new ItemNotFoundException(
        "The recipe with id " + id + " is not found"
      );
    }
    recipeRepository.deleteById(id);
  }
}
