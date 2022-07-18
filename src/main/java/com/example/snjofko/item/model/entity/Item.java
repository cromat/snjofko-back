package com.example.snjofko.item.model.entity;

import com.example.snjofko.app_user.model.entity.AppUser;

import javax.persistence.*;
import java.time.OffsetDateTime;

@Entity
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

    @ManyToOne
    @JoinColumn(name = "user_id")
    private AppUser user;

}
