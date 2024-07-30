package com.imyanyijie.recipes.service;

import com.imyanyijie.recipes.dto.CreateRecipeDTO;
import com.imyanyijie.recipes.dto.ShortRecipeDTO;
import com.imyanyijie.recipes.exception.ItemNotFoundException;
import com.imyanyijie.recipes.model.Ingredient;
import com.imyanyijie.recipes.model.Recipe;
import com.imyanyijie.recipes.model.RecipeItemKey;
import com.imyanyijie.recipes.repository.RecipeRepository;
import com.imyanyijie.recipes.repository.ingredientRepository;
import jakarta.persistence.TableGenerator;
import jakarta.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Log4j2
public class RecipeService {

  @Autowired
  RecipeRepository recipeRepository;

  @Autowired
  ingredientRepository ingredientRepository;

  public List<Recipe> getAllRecipes() {
    List<Recipe> recipes = recipeRepository.findAll();
    return recipes;
  }

  public List<ShortRecipeDTO> getShortRecipes() {
    return recipeRepository.findShortRecipes();
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

  //will take a request recipe type and convert to the entity type.
  //this method will also create the key relationship with ingredient
  public Recipe creatRecipe(CreateRecipeDTO recipeRequest) {
    System.out.println("Recipe name is " + recipeRequest.name());
    //save recipe
    Recipe recipe = new Recipe(
      recipeRequest.imagePath(),
      recipeRequest.instruction(),
      recipeRequest.description(),
      recipeRequest.name(),
      recipeRequest.cookDuration(),
      recipeRequest.prepDuration()
    );
    recipeRepository.save(recipe);

    List<CreateRecipeDTO.ingredient> ingredients = recipeRequest.ingredients();
    List<Ingredient> saveingredients = new ArrayList<>();
    for (CreateRecipeDTO.ingredient ing : ingredients) {
      Ingredient ingredient = new Ingredient();
      ingredient.setIngredientID(
        new RecipeItemKey(recipe.getRecipeID(), ing.item().getItemID())
      );
      ingredient.setItem(ing.item());
      ingredient.setRecipe(recipe);
      ingredient.setItemAmount(ing.itemAmount());
      ingredient.setUnit(ing.unit());
      saveingredients.add(ingredient);
    }
    ingredientRepository.saveAll(saveingredients);
    recipe.setIngredients(saveingredients);
    return recipe;
  }

  public void deleteRecipe(long id) {
    Optional<Recipe> recipeOption = recipeRepository.findById(id);
    if (!recipeOption.isPresent()) {
      throw new ItemNotFoundException(
        "The recipe with id " + id + " is not found"
      );
    }
    // Recipe deleteRecipe = recipeOption.get();
    // deleteRecipe.getIngredients().removeAll(deleteRecipe.getIngredients());
    recipeRepository.delete(recipeOption.get());
  }
}
