package com.imyanyijie.recipes.dto;

import com.imyanyijie.recipes.model.Item;
import com.imyanyijie.recipes.model.Unit;
import java.util.List;

public record CreateRecipeDTO(
  String imagePath,
  String instruction,
  String description,
  String name,
  long cookDuration,
  long prepDuration,
  List<ingredient> ingredients
) {
  public record ingredient(Item item, double itemAmount, Unit unit) {}
}
