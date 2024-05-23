package com.imyanyijie.recipes.repository;

import com.imyanyijie.recipes.model.Ingrediant;
import com.imyanyijie.recipes.model.RecipeItemKey;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IngrediantRepository
  extends JpaRepository<Ingrediant, RecipeItemKey> {

    
  }
