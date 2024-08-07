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
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.ManyToAny;
import org.hibernate.annotations.UpdateTimestamp;

@Table(name = "meal_plan")
@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class MealPlan {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long mealPlanID;

  @Column(name = "name", nullable = false)
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
  @Column(name = "grocery_items", nullable = true)
  List<GroceryItems> groceryItems;
}
