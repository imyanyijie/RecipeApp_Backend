package com.imyanyijie.recipes.model;

import jakarta.persistence.*;
import java.sql.Timestamp;
import java.util.List;
import lombok.*;

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

  @Column(name = "createTime")
  private Timestamp createTime;

  @OneToMany(mappedBy = "recipe", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
  List<Ingrediant> ingrediant;

  public Recipe(
    String imagePath,
    String instruction,
    String description,
    String name,
    Long cookDuration,
    Long prepDuration,
    Timestamp createTime
  ) {
    this.imagePath = imagePath;
    this.instruction = instruction;
    this.description = description;
    this.name = name;
    this.cookDuration = cookDuration;
    this.prepDuration = prepDuration;
    this.createTime = createTime;
  }
}
