package com.example.snjofko.item.model.command;

import lombok.Data;

import java.time.OffsetDateTime;

@Data
public class ItemFilterCommand {
    private String name;
    private String category;
    private OffsetDateTime from;
    private OffsetDateTime to;
}
