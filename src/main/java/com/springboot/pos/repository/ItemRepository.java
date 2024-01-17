package com.springboot.pos.repository;

import com.springboot.pos.model.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemRepository extends JpaRepository<Item, String> {
    Item findItemByItemCode(String itemCode);
}
