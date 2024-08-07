package com.imyanyijie.recipes.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
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

  @Column(name = "unit", nullable = false)
  @Enumerated(EnumType.STRING)
  private Unit unit;

  @Column(name = "category", nullable = false)
  @Enumerated(EnumType.STRING)
  private Category category;

  @CreationTimestamp
  private Timestamp createTime;

  @UpdateTimestamp
  private Timestamp updateTime;

  @OneToMany(
    mappedBy = "item",
    cascade = CascadeType.ALL,
    fetch = FetchType.EAGER
  )
  @JsonIgnore
  List<Ingredient> ingredients;

  public Item(String name, String description, double unitCost) {
    this.name = name;
    this.description = description;
    this.unitCost = unitCost;
  }
}
