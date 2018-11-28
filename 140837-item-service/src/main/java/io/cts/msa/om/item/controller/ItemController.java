package io.cts.msa.om.item.controller;

import java.util.ArrayList;
import java.util.List;

import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.boot.json.JsonParserFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.gson.JsonArray;
import com.google.gson.JsonParser;

import io.cts.msa.om.item.domain.ItemDetails;
import io.cts.msa.om.item.service.ItemService;

@RestController
@RequestMapping("/service")
public class ItemController {

	@Autowired
	private ItemService itemService;

	@GetMapping("/item/{name}")
	public ItemDetails getItemByName(@PathVariable String name) {

		return itemService.getItemByName(name);

	}

	@PostMapping("/items")
	public List<ItemDetails> getItemList(@RequestBody String name) {
		
		
		
		List<ItemDetails> itemDetails = null;
		JSONObject obj = null;
		try {
			if (name != null) {
				JsonParser jsonParser = new JsonParser();
				JsonArray arr = jsonParser.parse(name).getAsJsonArray();
				ArrayList<String> items = new ArrayList<String>();

				for (int i = 0; i < arr.size(); i++) {

					items.add(arr.get(i).getAsString());
				}

				System.out.println(items);
				itemDetails	= itemService.getAllItemByName(items);

			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return itemDetails;

	}

	@GetMapping("/items")
	public List<ItemDetails> getAllItems() {

		return itemService.getAllItems();

	}

}
