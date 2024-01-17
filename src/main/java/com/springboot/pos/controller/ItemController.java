package com.springboot.pos.controller;

import com.springboot.pos.dto.ItemDto;
import com.springboot.pos.model.Item;
import com.springboot.pos.service.ItemService;
import com.springboot.pos.util.ApiUrlConstant;
import com.springboot.pos.util.Constants;
import com.springboot.pos.util.Response;
import com.springboot.pos.util.ResponseMessage;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(ApiUrlConstant.ITEM_PATH)
public class ItemController {

    private final ItemService itemService;

    public ItemController(ItemService itemService) {
        this.itemService = itemService;
    }

    @PostMapping("/add")
    public ResponseEntity<?> addItem(@RequestBody Item item) {
        ItemDto itemDto = itemService.save(item);
        Response<?> response = new Response<>(Constants.SUCCESSFULLY_ADD_ITEM, itemDto);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/list")
    public ResponseEntity<List<ItemDto>> getAllItem() {
        List<ItemDto> items = itemService.findAll();
        return ResponseEntity.status(HttpStatus.OK)
                .contentType(MediaType.APPLICATION_JSON)
                .body(items);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<ResponseMessage> deleteItemById(@PathVariable String id) {
        if (itemService.itemPresent(id)) {
            itemService.delete(id);
            return ResponseEntity.status(HttpStatus.OK)
                    .contentType(MediaType.APPLICATION_JSON)
                    .body(new ResponseMessage(
                            String.format(Constants.SUCCESSFULLY_DELETE_ITEM, id) ));
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .contentType(MediaType.APPLICATION_JSON)
                .body(new ResponseMessage(
                        String.format(Constants.ITEM_NOT_FOUND, id))
                );
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<ResponseMessage> updateItem(@PathVariable String id,
                                                      @RequestBody Item item) {
        if(itemService.itemPresent(id)) {
            itemService.save(item);
            return ResponseEntity.status(HttpStatus.OK)
                    .contentType(MediaType.APPLICATION_JSON)
                    .body(new ResponseMessage(
                            String.format(Constants.SUCCESSFULLY_UPDATE_ITEM, item.getId()))
                    );
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .contentType(MediaType.APPLICATION_JSON)
                .body(new ResponseMessage(
                                String.format(Constants.ITEM_NOT_FOUND, item.getId()))
                );
    }
}
