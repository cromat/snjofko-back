package com.example.snjofko.item.controller;

import com.example.snjofko.item.model.command.ItemCreateCommand;
import com.example.snjofko.item.model.command.ItemFilterCommand;
import com.example.snjofko.item.model.dto.ItemDTO;
import com.example.snjofko.item.service.ItemService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/items")
public class ItemController {

    private final ItemService itemService;

    public ItemController(ItemService itemService) {
        this.itemService = itemService;
    }

    @GetMapping("{id}")
    public ResponseEntity<ItemDTO> getItem(@PathVariable Long id) {
        return ResponseEntity.ok(itemService.getItem(id));
    }

    @PostMapping("create")
    public ResponseEntity<ItemDTO> createItem(@RequestBody ItemCreateCommand cmd) {
        return ResponseEntity.ok(itemService.createItem(cmd));
    }

    @PostMapping("filter")
    public ResponseEntity<List<ItemDTO>> filterItem(@RequestBody ItemFilterCommand cmd) {
        return ResponseEntity.ok(itemService.filterItem(cmd));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Boolean> deleteItem(@PathVariable Long id) {
        return ResponseEntity.ok(itemService.deleteItem(id));
    }

}
