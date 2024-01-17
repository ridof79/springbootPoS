package com.springboot.pos.service.impl;

import com.springboot.pos.dto.ItemDto;
import com.springboot.pos.model.Item;
import com.springboot.pos.repository.ItemRepository;
import com.springboot.pos.service.ItemService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ItemServiceImpl implements ItemService {

    private final ItemRepository itemRepository;

    public ItemServiceImpl(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    @Override
    @Transactional
    public ItemDto save(Item item) {
        return new ItemDto(itemRepository.save(item));
    }

    @Override
    @Transactional
    public List<ItemDto> findAll() {
        return itemRepository.findAll().stream()
                .map(ItemDto::new)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public void delete(String id) {
        itemRepository.deleteById(id);
    }

    @Override
    @Transactional
    public Item findItemByItemCode(String itemCode) {
        return itemRepository.findItemByItemCode(itemCode);
    }

    @Override
    @Transactional
    public boolean itemPresent(String id) {
        if(itemRepository.findById(id).isPresent()){
            return true;

        }
        return false;
    }
}
