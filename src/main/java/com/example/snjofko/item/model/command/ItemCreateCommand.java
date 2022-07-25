package com.example.snjofko.item.model.command;

import com.example.snjofko.item.model.entity.Item;
import lombok.Data;

import java.time.OffsetDateTime;

@Data
public class ItemCreateCommand {
    private String name;
    private String category;
    private String description;
    private Double price;
    private Boolean isActive;

    public Item toEntity() {
        return Item.builder()
                .name(this.name)
                .category(this.category)
                .description(this.description)
                .price(this.price)
                .isActive(this.isActive)
                .created(OffsetDateTime.now())
                .build();
    }

}
