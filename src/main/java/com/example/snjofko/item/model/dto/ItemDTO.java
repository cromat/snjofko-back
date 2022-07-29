package com.example.snjofko.item.model.dto;

import com.example.snjofko.app_user.model.dto.UserPrincipal;
import com.example.snjofko.item.model.entity.Item;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Builder
@Data
@JsonInclude(value = JsonInclude.Include.NON_NULL)
public class ItemDTO {
    private Long id;
    private String name;
    private String description;
    private String category;
    private OffsetDateTime created;
    private OffsetDateTime modified;
    private Boolean isActive;
    private Double price;
    private String imageUrl;
    private UserPrincipal user;

    public static ItemDTO fromEntity(Item entity) {
        if (entity != null) {
            return ItemDTO.builder()
                    .id(entity.getId())
                    .name(entity.getName())
                    .description(entity.getDescription())
                    .category(entity.getCategory())
                    .created(entity.getCreated())
                    .modified(entity.getModified())
                    .isActive(entity.getIsActive())
                    .user(UserPrincipal.fromAppUser(entity.getUser()))
                    .price(entity.getPrice())
                    .imageUrl(entity.getImageUrl())
                    .build();
        }
        return null;
    }

    public static List<ItemDTO> fromEntityList(Iterable<Item> entityList) {
        return StreamSupport.stream(entityList.spliterator(), false)
                .map(ItemDTO::fromEntity).collect(Collectors.toList());
    }

}
