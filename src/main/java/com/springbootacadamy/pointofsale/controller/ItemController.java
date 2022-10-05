package com.springbootacadamy.pointofsale.controller;


import com.springbootacadamy.pointofsale.dto.paginated.PaginatedResponseItemDTO;
import com.springbootacadamy.pointofsale.dto.request.ItemSaveRequestDTO;
import com.springbootacadamy.pointofsale.service.ItemService;
import com.springbootacadamy.pointofsale.utils.StandardResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.Max;

@RestController
@CrossOrigin
@RequestMapping("api/v1/item")
public class ItemController {

    @Autowired
    private ItemService itemService;

    @PostMapping("/save")
    public ResponseEntity<StandardResponse> saveItem(@RequestBody ItemSaveRequestDTO itemSaveRequestDTO){
        String id = itemService.addItem(itemSaveRequestDTO);
        System.out.println(itemSaveRequestDTO.getBalanceQty());

        return new ResponseEntity<StandardResponse>(
                new StandardResponse(201,id + " item successfully saved", id),
                HttpStatus.CREATED
        );
    }

    @GetMapping(
            path = "count-all-item"
    )
    public ResponseEntity<StandardResponse> getAllItemCounts(){
        int itemCount = itemService.countAllItems();

        return new ResponseEntity<StandardResponse>(new StandardResponse(200,"success", itemCount), HttpStatus.OK);
    }

    @GetMapping(
            path = "get-all-items-paginated",
            params = {"page", "size"}
    )
    public ResponseEntity<StandardResponse> getAllItemsPaginated(
            @RequestParam(value = "page") int page,
            @RequestParam(value = "size") @Max(50) int size
    ){
        PaginatedResponseItemDTO paginatedResponseItemDTO = itemService.getAllItemsPaginated(page, size);
        return new ResponseEntity<StandardResponse>(new StandardResponse(200,"success", paginatedResponseItemDTO), HttpStatus.OK);
    }

}
