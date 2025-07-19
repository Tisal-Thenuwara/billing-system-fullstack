package com.base.billing.ms.service;

import com.base.billing.ms.model.dao.Item;

import java.util.List;

public interface ItemService {
    Item getItemByItemCode(String itemCode);

    List<Item> getItemsByStatus(String status);

    void updateItemStatus(String itemCode, String status);

    void updateItemDetails(String itemCode, String itemName, String description);

    void updateItemPricingAndQuantity(String itemCode, double pricePerUnit, int quantity);
}
