package com.imyanyijie.recipes.repository;

import com.imyanyijie.recipes.model.Ingredient;
import com.imyanyijie.recipes.model.RecipeItemKey;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ingredientRepository
  extends JpaRepository<Ingredient, RecipeItemKey> {}
