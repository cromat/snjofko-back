package com.example.snjofko.item.model.entity;

import com.example.snjofko.app_user.model.entity.AppUser;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.OffsetDateTime;

@Entity
@Data
@Table(name = "item")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    private String name;

    private String category;

    private String description;

    private OffsetDateTime created;

    private OffsetDateTime modified;

    private Boolean isActive;

    private Double price;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private AppUser user;

}
