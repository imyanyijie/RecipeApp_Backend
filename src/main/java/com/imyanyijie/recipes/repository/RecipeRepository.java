package com.imyanyijie.recipes.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.imyanyijie.recipes.model.Recipe;

public interface RecipeRepository extends JpaRepository<Recipe, Long> {
    
}
