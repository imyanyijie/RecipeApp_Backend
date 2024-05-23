package com.imyanyijie.recipes.controller;

import com.imyanyijie.recipes.model.Item;
import com.imyanyijie.recipes.repository.ItemRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/item")
public class ItemController {

  @Autowired
  private ItemRepository itemRepository;

  @GetMapping
  public List<Item> listItem() {
    return itemRepository.findAll();
  }

  @GetMapping("/{id}")
  public ResponseEntity<Item> getItem(@PathVariable long id)
    throws NotFoundException {
    Optional<Item> item = itemRepository.findById(id);
    if (item.isPresent()) {
      return new ResponseEntity<>(item.get(), HttpStatus.OK);
    } else {
      throw new NotFoundException();
    }
  }

  @PostMapping
  public Item createItem(Item item) {
    return itemRepository.save(item);
  }
}
