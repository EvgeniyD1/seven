package com.example.seven.dto.item;

import com.example.seven.domain.item.Item;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ItemMapper {

    ItemMapper ITEM_MAPPER = Mappers.getMapper(ItemMapper.class);

    ItemDto itemToItemDto(Item item);

    Item itemDtoToItem(ItemDto itemDto);

}
