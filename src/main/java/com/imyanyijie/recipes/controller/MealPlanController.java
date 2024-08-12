package com.imyanyijie.recipes.controller;

import com.imyanyijie.recipes.model.GroceryItems;
import com.imyanyijie.recipes.model.MealPlan;
import com.imyanyijie.recipes.service.MealPlanService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
@RequestMapping("/api/v1/mealplans")
public class MealPlanController {

  @Autowired
  private MealPlanService mealPlanService;

  @GetMapping
  public List<MealPlan> getAllMealPlans() {
    return mealPlanService.getAllMealPlans();
  }

  @GetMapping("/mealplanID={id}")
  public List<GroceryItems> getAllPlanItems(@PathVariable long id) {
    return mealPlanService.getAllGroceryItems(id);
  }

  @PostMapping
  public ResponseEntity<MealPlan> createMealPlan(
    @RequestBody MealPlan mealPlan
  ) {
    return new ResponseEntity<>(
      mealPlanService.createMealPlan(mealPlan),
      HttpStatus.OK
    );
  }

  @PutMapping
  public ResponseEntity<MealPlan> updateMealPlan(
    @RequestBody MealPlan mealPlan
  ) {
    return new ResponseEntity<>(
      mealPlanService.updateMealPlan(mealPlan),
      HttpStatus.OK
    );
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<MealPlan> deleteMealplan(@PathVariable long id) {
    return new ResponseEntity<>(
      mealPlanService.deleteMealPlan(id),
      HttpStatus.OK
    );
  }
}
