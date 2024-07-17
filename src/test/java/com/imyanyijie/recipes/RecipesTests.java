package com.imyanyijie.recipes;

import static org.assertj.core.api.Assertions.assertThat;

import com.imyanyijie.recipes.dto.CreateRecipeDTO;
import com.imyanyijie.recipes.model.Ingrediant;
import com.imyanyijie.recipes.model.Item;
import com.imyanyijie.recipes.model.Recipe;
import com.imyanyijie.recipes.model.Unit;
import com.imyanyijie.recipes.repository.IngrediantRepository;
import com.imyanyijie.recipes.repository.ItemRepository;
import com.imyanyijie.recipes.repository.RecipeRepository;
import com.imyanyijie.recipes.service.RecipeService;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;

@SpringBootTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@Rollback(true)
class RecipesTests {

  @Autowired
  private IngrediantRepository ingrediantRepository;

  @Autowired
  private ItemRepository itemRepository;

  @Autowired
  private RecipeRepository recipeRepository;

  @Autowired
  private RecipeService recipeService;

  @Test
  public void testListAllIngradiants() {
    List<Ingrediant> ingrediants = ingrediantRepository.findAll();
    assertThat(ingrediants.size()).isEqualTo(2);
    System.out.println("There are: " + ingrediants.size());
  }

  @Test
  public void testListAllRecipes() {
    List<Recipe> recipes = recipeRepository.findAll();
    assertThat(recipes.size()).isEqualTo(3);
    System.out.println("There are: " + recipes.size());
  }

  @Test
  public void testAddRecipe() {
    //create item object
    Item item = new Item("Apple", "It is a apple for testing", 3.5);
    itemRepository.save(item);
    // System.out.println("Item ID is " + savedItem.getItemID());
    CreateRecipeDTO.Ingrediant ingrediant = new CreateRecipeDTO.Ingrediant(
      item,
      2,
      Unit.g
    );
    List<CreateRecipeDTO.Ingrediant> ingrediants = new ArrayList<>();
    ingrediants.add(ingrediant);
    //create recipe object
    CreateRecipeDTO createRecipeDTO = new CreateRecipeDTO(
      "testPath",
      "Apple pie testInstruct",
      "apple pie description",
      "Apple Pie",
      30,
      30,
      ingrediants
    );
    Recipe savedRecipe = recipeService.creatRecipe(createRecipeDTO);
    assertThat(savedRecipe).isNotNull();
  }
}
