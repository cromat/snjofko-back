package com.example.snjofko.item.service;

import com.example.snjofko.app_user.model.dto.UserPrincipal;
import com.example.snjofko.app_user.model.entity.AppUser;
import com.example.snjofko.app_user.repository.AppUserRepository;
import com.example.snjofko.item.model.command.ItemCreateCommand;
import com.example.snjofko.item.model.command.ItemFilterCommand;
import com.example.snjofko.item.model.dto.ItemDTO;
import com.example.snjofko.item.model.entity.Item;
import com.example.snjofko.item.model.entity.QItem;
import com.example.snjofko.item.repository.ItemRepository;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.Expressions;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class ItemServiceImpl implements ItemService {

    private final ItemRepository itemRepository;
    private final AppUserRepository userRepository;

    public ItemServiceImpl(ItemRepository itemRepository, AppUserRepository userRepository) {
        this.itemRepository = itemRepository;
        this.userRepository = userRepository;
    }

    @Override
    public ItemDTO getItem(Long id) {
        return ItemDTO.fromEntity(itemRepository.findById(id).orElse(null));
    }

    @Override
    public ItemDTO createItem(ItemCreateCommand cmd) {
        Item item = cmd.toEntity();
//        UserPrincipal user = (UserPrincipal) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//        item.setUser(userRepository.findById(user.getId()).orElse(null));
        AppUser user = new AppUser();
        user.setId(1L);
        item.setUser(user);

        return ItemDTO.fromEntity(itemRepository.save(item));
    }

    @Override
    public List<ItemDTO> filterItem(ItemFilterCommand cmd) {
        return ItemDTO.fromEntityList(itemRepository.findAll(getFilterExpression(cmd)));
    }

    private BooleanExpression getFilterExpression(ItemFilterCommand cmd) {
        BooleanExpression exp = Expressions.asBoolean(true).isTrue();

        if (cmd != null) {
            if (cmd.getName() != null && !cmd.getName().isBlank()) {
                exp = exp.and(QItem.item.name.eq(cmd.getName()));
            }
            if (cmd.getCategory() != null && !cmd.getCategory().isBlank()) {
                exp = exp.and(QItem.item.category.eq(cmd.getCategory()));
            }
            if (cmd.getFrom() != null) {
                exp = exp.and(QItem.item.created.after(cmd.getFrom()));
            }
            if (cmd.getTo() != null) {
                exp = exp.and(QItem.item.created.before(cmd.getTo()));
            }
        }

        return exp;
    }

    @Override
    public Boolean deleteItem(Long id) {
        itemRepository.deleteById(id);
        return true;
    }

}
