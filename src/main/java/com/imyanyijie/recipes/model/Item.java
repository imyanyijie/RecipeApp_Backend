package com.imyanyijie.recipes.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.util.List;
import lombok.*;

@ToString
@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "item")
public class Item {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long itemID;

  @Column(name = "name", nullable = false)
  private String name;

  @Column(name = "description", nullable = true)
  private String description;

  @Column(name = "unitCost", nullable = false)
  private double unitCost;

  @OneToMany(
    mappedBy = "item",
    cascade = CascadeType.ALL,
    fetch = FetchType.LAZY
  )
  List<Ingrediant> ingrediant;

  public Item(String name, String description, double unitCost) {
    this.name = name;
    this.description = description;
    this.unitCost = unitCost;
  }
}
