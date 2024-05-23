package com.imyanyijie.recipes.model;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Embeddable
@EqualsAndHashCode
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class RecipeItemKey implements Serializable {

  @Column(name = "recipeID")
  private Long recipeID;

  @Column(name = "itemID")
  private Long itemID;
}
