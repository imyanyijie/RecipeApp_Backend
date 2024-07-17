package com.imyanyijie.recipes.dto;

public record ShortRecipeDTO(
  Long recipeID,
  String imagePath,
  String name,
  long cookDuration,
  long prepDuration
) {}
