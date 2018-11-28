package io.cts.msa.om.item.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.cts.msa.om.item.domain.ItemDetails;
import io.cts.msa.om.item.entity.Item;
import io.cts.msa.om.item.repository.ItemRepository;

@Service
public class ItemService {

	@Autowired
	private ItemRepository itemRepository;

	public List<ItemDetails> getAllItems() {

		List<Item> items = itemRepository.findAll();

		List<ItemDetails> allItems = null;

		if (items != null) {

			allItems = items.stream().map(i -> {

				ItemDetails itemDetails = new ItemDetails();
				itemDetails.setId(i.getId());
				itemDetails.setName(i.getName());
				itemDetails.setDescription(i.getDescription());
				itemDetails.setPrice(i.getPrice());

				return itemDetails;
			}).collect(Collectors.toList());

		}

		return allItems;

	}

	public ItemDetails getItemByName(String name) {

		Item item = itemRepository.findByName(name);
		ItemDetails itemDetails = null;
		if (item != null) {
			itemDetails = new ItemDetails();
			itemDetails.setId(item.getId());
			itemDetails.setName(item.getName());
			itemDetails.setDescription(item.getDescription());
			itemDetails.setPrice(item.getPrice());

		}

		return itemDetails;

	}

	public List<ItemDetails> getAllItemByName(List<String> names) {
       
		System.out.println(names);
		List<Item> items = itemRepository.findAllByNameIn(names);
		System.out.println(items);
		List<ItemDetails> allItems = null;
		if (items != null) {

			allItems = items.stream().map(i -> {

				ItemDetails itemDetails = new ItemDetails();
				itemDetails.setId(i.getId());
				itemDetails.setName(i.getName());
				itemDetails.setDescription(i.getDescription());
				itemDetails.setPrice(i.getPrice());

				return itemDetails;
			}).collect(Collectors.toList());
		}

		return allItems;
	}
}
