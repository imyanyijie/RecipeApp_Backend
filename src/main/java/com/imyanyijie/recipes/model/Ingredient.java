package com.imyanyijie.recipes.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

@ToString
@Getter
@Setter
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "ingredient")
public class Ingredient {

  @EmbeddedId
  private RecipeItemKey ingredientID;

  @ManyToOne
  @MapsId("itemID")
  @JoinColumn(name = "itemID")
  private Item item;

  @ManyToOne
  @MapsId("recipeID")
  @JoinColumn(name = "recipeID")
  @JsonIgnore
  private Recipe recipe;

  @Column(name = "itemAmount", nullable = false)
  private double itemAmount;

  @Column(name = "unit", nullable = false)
  @Enumerated(EnumType.STRING)
  private Unit unit;

  public Ingredient(double itemAmount, Unit unit) {
    this.itemAmount = itemAmount;
    this.unit = unit;
  }

  @PreRemove
  //make sure to remove assoicication with item before removal
  public void preRemoval() {
    this.item.getIngredients().clear();
    this.item = null;
  }
}
