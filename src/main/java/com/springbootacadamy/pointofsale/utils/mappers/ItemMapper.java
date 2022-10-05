package com.springbootacadamy.pointofsale.utils.mappers;

import com.springbootacadamy.pointofsale.dto.ItemDTO;
import com.springbootacadamy.pointofsale.dto.request.ItemSaveRequestDTO;
import com.springbootacadamy.pointofsale.entity.Item;
import org.mapstruct.Mapper;
import org.springframework.data.domain.Page;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ItemMapper {

    Item RequestDtoToEntity(ItemSaveRequestDTO itemSaveRequestDTO);
    List<ItemDTO> pageToList(Page<Item> page);
}
