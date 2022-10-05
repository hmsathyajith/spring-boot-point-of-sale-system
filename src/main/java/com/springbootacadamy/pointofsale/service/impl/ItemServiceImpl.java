package com.springbootacadamy.pointofsale.service.impl;

import com.springbootacadamy.pointofsale.dto.ItemDTO;
import com.springbootacadamy.pointofsale.dto.paginated.PaginatedResponseItemDTO;
import com.springbootacadamy.pointofsale.dto.request.ItemSaveRequestDTO;
import com.springbootacadamy.pointofsale.entity.Item;
import com.springbootacadamy.pointofsale.exceptions.EntryDuplicationException;
import com.springbootacadamy.pointofsale.repository.ItemRepo;
import com.springbootacadamy.pointofsale.service.ItemService;
import com.springbootacadamy.pointofsale.utils.mappers.ItemMapper;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ItemServiceImpl implements ItemService {

    @Autowired
    private ItemRepo itemRepo;

    @Autowired
    private ItemMapper itemMapper;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public String addItem(ItemSaveRequestDTO itemSaveRequestDTO) {
        /*Item item = itemMapper.RequestDtoToEntity(itemSaveRequestDTO);
        System.out.println(itemSaveRequestDTO.getBalanceQty());
        item.setBalanceQty(itemSaveRequestDTO.getBalanceQty());
        System.out.println(item.getBalanceQty());*/

        Item item = modelMapper.map(itemSaveRequestDTO, Item.class);
        item.setActiveState(true);
        if(!itemRepo.existsById(item.getItemId())){
            return itemRepo.save(item).getItemName();
        }else {
            throw new EntryDuplicationException("alredy in database ");
        }
    }

    @Override
    public int countAllItems() {
        int count = itemRepo.countAllByActiveStateEquals(true);
        return count;
    }

    @Override
    public PaginatedResponseItemDTO getAllItemsPaginated(int page, int size) {
        Page<Item> getAllItemsByPaginated = itemRepo.findAll(PageRequest.of(page,size));
        return new PaginatedResponseItemDTO(
                itemMapper.pageToList(getAllItemsByPaginated),
                itemRepo.count()
        );
    }
}
