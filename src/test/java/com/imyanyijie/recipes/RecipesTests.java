package com.imyanyijie.recipes;

import static org.assertj.core.api.Assertions.assertThat;

import com.imyanyijie.recipes.model.Ingrediant;
import com.imyanyijie.recipes.model.Item;
import com.imyanyijie.recipes.model.Recipe;
import com.imyanyijie.recipes.model.RecipeItemKey;
import com.imyanyijie.recipes.model.Unit;
import com.imyanyijie.recipes.repository.IngrediantRepository;
import com.imyanyijie.recipes.repository.ItemRepository;
import com.imyanyijie.recipes.repository.RecipeRepository;
import java.sql.Timestamp;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@Rollback(false)
class RecipesTests {

  @Autowired
  private IngrediantRepository ingrediantRepository;

  @Autowired
  private ItemRepository itemRepository;

  @Autowired
  private RecipeRepository recipeRepository;

  @Test
  public void testListAll() {
    List<Ingrediant> ingrediants = ingrediantRepository.findAll();
    assertThat(ingrediants).isNotEmpty();
    ingrediants.forEach(System.out::println);
  }

  @Test
  public void testAddOrderDetail() {
    //create item object
    Item item = new Item("testItem", "testDescp", 3.5);
    itemRepository.save(item);

    //create recipe object
    Timestamp timestamp = new Timestamp(0);
    Recipe recipe = new Recipe(
      "testimg",
      "test instuction",
      "test desc",
      "test recipe",
      40L,
      40L,
      timestamp
    );

    recipeRepository.save(recipe);

    RecipeItemKey recipeItemKey = new RecipeItemKey(
      recipe.getRecipeID(),
      item.getItemID()
    );
    Ingrediant ingrediant = new Ingrediant();

    ingrediant.setIngrediantID(recipeItemKey);
    ingrediant.setItem(item);
    ingrediant.setRecipe(recipe);
    ingrediant.setItemAmount(8);
    ingrediant.setUnit(Unit.lb);

    Ingrediant savedIngrediant = ingrediantRepository.save(ingrediant);
    assertThat(savedIngrediant).isNotNull();
    assertThat(savedIngrediant.getIngrediantID().getItemID())
      .isEqualTo(item.getItemID());
    assertThat(savedIngrediant.getIngrediantID().getRecipeID())
      .isEqualTo(recipe.getRecipeID());
  }
}
