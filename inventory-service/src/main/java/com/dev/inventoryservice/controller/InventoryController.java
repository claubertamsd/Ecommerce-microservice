package com.dev.inventoryservice.controller;

import com.dev.inventoryservice.dto.InventoryResponse;
import com.dev.inventoryservice.model.Inventory;
import com.dev.inventoryservice.repository.InventoryRepository;
import com.dev.inventoryservice.service.InventoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/inventory")
@RequiredArgsConstructor
public class InventoryController {
    private final InventoryService inventoryService;
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<InventoryResponse> isInStock(@RequestParam List<String> skuCode){
         return inventoryService.isInStock(skuCode);
    }


}

