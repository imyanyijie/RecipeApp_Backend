package com.imyanyijie.recipes.service;

import com.imyanyijie.recipes.model.GroceryItems;
import com.imyanyijie.recipes.model.Item;
import com.imyanyijie.recipes.model.MealPlan;
import com.imyanyijie.recipes.repository.GroceryItemsRepository;
import com.imyanyijie.recipes.repository.ItemRepository;
import com.imyanyijie.recipes.repository.MealPlanRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Log4j2
public class MealPlanService {

  @Autowired
  MealPlanRepository mealPlanRepository;

  @Autowired
  GroceryItemsRepository groceryItemsRepository;

  @Autowired
  ItemRepository itemRepository;

  public MealPlan createMealPlan(MealPlan mealPlan) {
    return mealPlanRepository.save(mealPlan);
  }

  public List<MealPlan> getAllMealPlans() {
    return mealPlanRepository.findAll();
  }

  public MealPlan getMealPlans(long id) {
    return mealPlanRepository.findById(id).get();
  }

  //TODO: need to revisit this code to handle more edge case.
  public MealPlan updateMealPlan(MealPlan meal_plan) {
    Optional<MealPlan> mealplanOptional = mealPlanRepository.findById(
      meal_plan.getMealPlanID()
    );
    if (mealplanOptional.isPresent()) {
      log.warn("meal plan have" + mealplanOptional.get().getGroceryItems());
      MealPlan foundMealPlan = mealplanOptional.get();
      log.warn("meal plan have items");
      List<GroceryItems> groceryList = new ArrayList<>();
      for (GroceryItems items : meal_plan.getGroceryItems()) {
        GroceryItems saveGroceryItems = new GroceryItems();
        Optional<Item> foundItem = itemRepository.findById(items.getItemID());
        if (foundItem.isPresent()) {
          saveGroceryItems.setItem(foundItem.get());
        }
        saveGroceryItems.setPlan(foundMealPlan);
        groceryList.add(saveGroceryItems);
      }
      groceryItemsRepository.saveAll(groceryList);
      foundMealPlan.setGroceryItems(groceryList);
    }
    return meal_plan;
  }

  public List<GroceryItems> getAllGroceryItems(long id) {
    Optional<MealPlan> mealPlanOptional = mealPlanRepository.findById(id);
    List<GroceryItems> itemResult = new ArrayList<>();
    if (mealPlanOptional.isPresent()) {
      itemResult = mealPlanOptional.get().getGroceryItems();
    }
    return itemResult;
  }

  public MealPlan deleteMealPlan(long id) {
    Optional<MealPlan> mealPlanOption = mealPlanRepository.findById(id);
    if (mealPlanOption.isPresent()) mealPlanRepository.deleteById(id);
    return mealPlanOption.get();
  }
}
