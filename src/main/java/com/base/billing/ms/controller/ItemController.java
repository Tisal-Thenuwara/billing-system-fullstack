package com.base.billing.ms.controller;

import com.base.billing.ms.model.dao.Item;
import com.base.billing.ms.service.ItemService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/items")
public class ItemController {

    private final ItemService itemService;

    public ItemController(ItemService itemService) {
        this.itemService = itemService;
    }

    @GetMapping("/{itemCode}")
    public Item getItemByItemCode(@PathVariable String itemCode) {
        return itemService.getItemByItemCode(itemCode);
    }

    @GetMapping("/status/{status}")
    public List<Item> getItemsByStatus(@PathVariable String status) {
        return itemService.getItemsByStatus(status);
    }

    @PutMapping("/{itemCode}/status")
    public void updateItemStatus(@PathVariable String itemCode, @RequestParam String status) {
        itemService.updateItemStatus(itemCode, status);
    }

    @PutMapping("/{itemCode}/details")
    public void updateItemDetails(@PathVariable String itemCode, @RequestBody Item item) {
        itemService.updateItemDetails(itemCode, item.getItemName(), item.getDescription());
    }

    @PutMapping("/{itemCode}/pricing")
    public void updateItemPricingAndQuantity(@PathVariable String itemCode, @RequestParam double pricePerUnit, @RequestParam int quantity) {
        itemService.updateItemPricingAndQuantity(itemCode, pricePerUnit, quantity);
    }
}
