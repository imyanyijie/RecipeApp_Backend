package com.imyanyijie.recipes.controller;

import com.imyanyijie.recipes.model.Item;
import com.imyanyijie.recipes.service.ItemService;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@RestController
@RequestMapping("/api/v1/items")
public class ItemController {

  @Autowired
  private ItemService itemService;

  @GetMapping
  public List<Item> listItem() {
    return itemService.getAllItem();
  }

  @GetMapping("/{id}")
  public ResponseEntity<Item> getItem(@PathVariable long id)
    throws NotFoundException {
    return new ResponseEntity<>(itemService.getItemByID(id), HttpStatus.OK);
  }

  @PostMapping
  public ResponseEntity<Item> createItem(@RequestBody Item item) {
    return new ResponseEntity<>(
      itemService.createItem(item),
      HttpStatus.CREATED
    );
  }

  @DeleteMapping("/{id}")
  public void deleteItem(@PathVariable long id) {
    itemService.deleteItem(id);
  }
}
