package com.springbootacadamy.pointofsale.service;


import com.springbootacadamy.pointofsale.dto.paginated.PaginatedResponseItemDTO;
import com.springbootacadamy.pointofsale.dto.request.ItemSaveRequestDTO;

public interface ItemService {

    String addItem(ItemSaveRequestDTO itemSaveRequestDTO);

    int countAllItems();

    PaginatedResponseItemDTO getAllItemsPaginated(int page, int size);
}
