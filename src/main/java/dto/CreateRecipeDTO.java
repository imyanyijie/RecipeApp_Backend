package dto;

import com.imyanyijie.recipes.model.Item;
import com.imyanyijie.recipes.model.Unit;
import java.util.List;

public record CreateRecipeDTO(
  String imgPath,
  String instruction,
  String description,
  String name,
  long cookDuration,
  long prepduration,
  List<Ingrediant> ingrediants
) {
  public record Ingrediant(Item item, double itemAmount, Unit unit) {}
}
