package com.base.billing.ms.service.impl;

import com.base.billing.ms.model.dao.Item;
import com.base.billing.ms.repository.ItemRepository;
import com.base.billing.ms.service.ItemService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ItemServiceImpl implements ItemService {

    private final ItemRepository itemRepository;

    public ItemServiceImpl(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    @Override
    public Item getItemByItemCode(String itemCode) {
        return itemRepository.findByItemCode(itemCode);
    }

    @Override
    public List<Item> getItemsByStatus(String status) {
        return itemRepository.findByStatus(status);
    }

    @Override
    @Transactional
    public void updateItemStatus(String itemCode, String status) {
        itemRepository.updateStatusByItemCode(status, itemCode);
    }

    @Override
    @Transactional
    public void updateItemDetails(String itemCode, String itemName, String description) {
        itemRepository.updateItemNameAndDescriptionByItemCode(itemName, description, itemCode);
    }

    @Override
    @Transactional
    public void updateItemPricingAndQuantity(String itemCode, double pricePerUnit, int quantity) {
        itemRepository.updatePricePerUnitAndQuantityByItemCode(pricePerUnit, quantity, itemCode);
    }
}
