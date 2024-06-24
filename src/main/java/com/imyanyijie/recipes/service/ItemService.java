package com.imyanyijie.recipes.service;

import com.imyanyijie.recipes.exception.BadRequestException;
import com.imyanyijie.recipes.exception.ItemNotFoundException;
import com.imyanyijie.recipes.model.Item;
import com.imyanyijie.recipes.repository.ItemRepository;
import java.util.List;
import java.util.Optional;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Log4j2
public class ItemService {

  @Autowired
  ItemRepository itemRepository;

  /**
   * @return all items
   */
  public List<Item> getAllItem() {
    return itemRepository.findAll();
  }

  /**
   * @param id
   * @return item found
   */
  public Item getItemByID(long id) {
    Optional<Item> itemOption = itemRepository.findById(id);
    if (!itemOption.isPresent()) {
      throw new ItemNotFoundException(
        "The Item with id " + id + " is not found"
      );
    }
    return itemOption.get();
  }

  /**
   * @param item
   * @return item created
   */
  public Item createItem(Item item) {
    log.debug("Trying to insert: " + item.getName());
    if (item.getItemID() != null) {
      throw new BadRequestException("Item already exist");
    }
    return itemRepository.save(item);
  }

  /**
   * @param id
   */
  public void deleteItem(long id) {
    Optional<Item> itemOption = itemRepository.findById(id);
    if (!itemOption.isPresent()) {
      throw new ItemNotFoundException(
        "The Item with id " + id + " is not found"
      );
    }
    itemRepository.deleteById(id);
  }
}
