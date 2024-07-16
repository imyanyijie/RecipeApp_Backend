package com.imyanyijie.recipes.service;

import com.imyanyijie.recipes.exception.ItemNotFoundException;
import com.imyanyijie.recipes.model.Ingrediant;
import com.imyanyijie.recipes.model.Recipe;
import com.imyanyijie.recipes.model.RecipeItemKey;
import com.imyanyijie.recipes.repository.IngrediantRepository;
import com.imyanyijie.recipes.repository.RecipeRepository;
import dto.CreateRecipeDTO;
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
  IngrediantRepository ingrediantRepository;

  public List<Recipe> getAllRecipes() {
    List<Recipe> recipes = recipeRepository.findAll();
    return recipes;
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
  //this method will also create the key relationship with ingrediant
  public Recipe creatRecipe(CreateRecipeDTO recipeRequest) {
    System.out.println("Recipe name is " + recipeRequest.name());
    //save recipe
    Recipe recipe = new Recipe(
      recipeRequest.imagePath(),
      recipeRequest.instruction(),
      recipeRequest.description(),
      recipeRequest.name(),
      recipeRequest.cookDuration(),
      recipeRequest.prepduration()
    );
    recipeRepository.save(recipe);

    List<CreateRecipeDTO.Ingrediant> ingrediants = recipeRequest.ingrediants();
    List<Ingrediant> saveIngrediants = new ArrayList<>();
    for (CreateRecipeDTO.Ingrediant ing : ingrediants) {
      Ingrediant ingrediant = new Ingrediant();
      ingrediant.setIngrediantID(
        new RecipeItemKey(recipe.getRecipeID(), ing.item().getItemID())
      );
      ingrediant.setItem(ing.item());
      ingrediant.setRecipe(recipe);
      ingrediant.setItemAmount(ing.itemAmount());
      ingrediant.setUnit(ing.unit());
      saveIngrediants.add(ingrediant);
    }
    ingrediantRepository.saveAll(saveIngrediants);
    recipe.setIngrediant(saveIngrediants);
    return recipe;
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
