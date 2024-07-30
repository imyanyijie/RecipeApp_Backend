package com.imyanyijie.recipes.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.sql.Timestamp;
import java.util.List;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.ManyToAny;
import org.hibernate.annotations.UpdateTimestamp;

@Table(name = "meal_plan")
@Entity
public class MealPlan {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long mealPlanID;

  @Column(name = "name")
  private String name;

  @CreationTimestamp
  private Timestamp createTime;

  @UpdateTimestamp
  private Timestamp updateTime;

  @ManyToAny
  @JoinTable(
    name = "plan_recipe",
    joinColumns = @JoinColumn(name = "mealPlan_id"),
    inverseJoinColumns = @JoinColumn(name = "recipe_id")
  )
  List<Recipe> addedRecipes;

  @OneToMany(mappedBy = "plan")
  List<GroceryItems> groceryItems;
}
