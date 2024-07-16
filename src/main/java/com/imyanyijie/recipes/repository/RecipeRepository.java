package com.imyanyijie.recipes.repository;

import com.imyanyijie.recipes.model.Recipe;
import dto.ShortRecipeDTO;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface RecipeRepository extends JpaRepository<Recipe, Long> {
  @Query(
    value = "SELECT new dto.ShortRecipeDTO(r.recipeID, r.imagePath, r.name, r.cookDuration, r.prepDuration)" +
    "from Recipe r"
  )
  List<ShortRecipeDTO> findShortRecipes();
}
