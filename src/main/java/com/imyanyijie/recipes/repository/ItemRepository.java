package com.imyanyijie.recipes.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.imyanyijie.recipes.model.Item;

public interface ItemRepository extends JpaRepository<Item,Long>{}
