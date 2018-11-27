package io.cts.msa.om.item.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.cts.msa.om.item.domain.ItemDetails;
import io.cts.msa.om.item.service.ItemService;

@RestController
@RequestMapping("/service")
public class ItemController {

	@Autowired
	private ItemService itemService;

	@GetMapping("/items/{name}")
	public ItemDetails getItemByName(@PathVariable String name) {

		return itemService.getItemByName(name);

	}

	@GetMapping("/items")
	public List<ItemDetails> getAllItems() {

		return itemService.getAllItems();

	}

}
