package com.example.snjofko.item.service;

import com.example.snjofko.item.model.command.ItemCreateCommand;
import com.example.snjofko.item.model.command.ItemFilterCommand;
import com.example.snjofko.item.model.dto.ItemDTO;

import java.util.List;

public interface ItemService {

    ItemDTO getItem(Long id);

    ItemDTO createItem(ItemCreateCommand cmd);

    List<ItemDTO> filterItem(ItemFilterCommand cmd);

    Boolean deleteItem(Long id);
}
