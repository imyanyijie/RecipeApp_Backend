package com.imyanyijie.recipes.model;

import jakarta.persistence.*;
import lombok.*;

@ToString
@Getter
@Setter
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "ingrediant")
public class Ingrediant {

  @EmbeddedId
  private RecipeItemKey ingrediantID;

  @ManyToOne
  @MapsId("itemID")
  @JoinColumn(name = "itemID")
  private Item item;

  @ManyToOne
  @MapsId("recipeID")
  @JoinColumn(name = "recipeID")
  private Recipe recipe;

  @Column(name = "itemAmount", nullable = false)
  private double itemAmount;

  @Column(name = "unit", nullable = false)
  @Enumerated(EnumType.STRING)
  private Unit unit;

  public Ingrediant(double itemAmount, Unit unit) {
    this.itemAmount = itemAmount;
    this.unit = unit;
  }
}
