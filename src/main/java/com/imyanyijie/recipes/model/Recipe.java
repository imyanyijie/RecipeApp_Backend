package com.imyanyijie.recipes.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import java.sql.Timestamp;
import java.util.List;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

@ToString
@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "recipe")
public class Recipe {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long recipeID;

  @Column(name = "imagePath")
  private String imagePath;

  @Column(name = "instruction")
  private String instruction;

  @Column(name = "description")
  private String description;

  @Column(name = "name")
  private String name;

  @Column(name = "cookDuration")
  private Long cookDuration;

  @Column(name = "prepDuration")
  private Long prepDuration;

  @CreationTimestamp
  private Timestamp createTime;

  @UpdateTimestamp
  private Timestamp updateTimestamp;

  @OneToMany(
    mappedBy = "recipe",
    cascade = CascadeType.ALL,
    orphanRemoval = true
  )
  List<Ingredient> ingredients;

  @ManyToMany(mappedBy = "addedRecipes")
  @JsonIgnore
  List<MealPlan> plans;

  public Recipe(
    String imagePath,
    String instruction,
    String description,
    String name,
    Long cookDuration,
    Long prepDuration
  ) {
    this.imagePath = imagePath;
    this.instruction = instruction;
    this.description = description;
    this.name = name;
    this.cookDuration = cookDuration;
    this.prepDuration = prepDuration;
  }
}
