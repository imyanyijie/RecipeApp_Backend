package com.imyanyijie.recipes.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import java.sql.Timestamp;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

@Entity
@Table(name = "grocery_items")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class GroceryItems {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long itemID;

  @CreationTimestamp
  private Timestamp createTime;

  @UpdateTimestamp
  private Timestamp updateTime;

  @OneToOne(cascade = CascadeType.ALL)
  Item item;

  @Column(name = "quantity")
  private int quantity;

  @Column(name = "unit")
  @Enumerated(EnumType.STRING)
  private Unit unit;

  @ManyToOne
  @JoinColumn(name = "planID", nullable = false)
  @JsonIgnore
  private MealPlan plan;
}
