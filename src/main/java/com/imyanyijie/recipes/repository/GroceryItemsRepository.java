package com.imyanyijie.recipes.repository;

import com.imyanyijie.recipes.model.GroceryItems;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GroceryItemsRepository
  extends JpaRepository<GroceryItems, Long> {}
