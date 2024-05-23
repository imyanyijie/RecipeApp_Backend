package com.imyanyijie.recipes;

import static org.assertj.core.api.Assertions.assertThat;

import com.imyanyijie.recipes.model.Item;
import com.imyanyijie.recipes.repository.ItemRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@Rollback(true)
public class ItemTest {

  @Autowired
  private ItemRepository itemRepository;

  @Test
  public void testItemInsertion() {
    Item item = new Item("testItem1", "testDescp", 3.5);
    Item itemSaved = itemRepository.save(item);
    assertThat(itemSaved).isEqualTo(item);
    System.out.println(item.getItemID());
  }
}
