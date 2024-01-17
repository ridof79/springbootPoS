package com.springboot.pos.service;

import com.springboot.pos.dto.ItemDto;
import com.springboot.pos.model.Item;

import java.util.List;

public interface ItemService {

    ItemDto save(Item item);
    List<ItemDto> findAll();
    void delete(String id);
    Item findItemByItemCode(String itemCode);
    boolean itemPresent(String id);
}
