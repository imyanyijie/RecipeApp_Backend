package dto;

public record ShortRecipeDTO(
  Long recipeID,
  String imagePath,
  String name,
  long cookDuration,
  long prepduration
) {}
