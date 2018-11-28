package io.cts.msa.om.item.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import io.cts.msa.om.item.entity.Item;

@Repository
public interface ItemRepository extends JpaRepository<Item, Long> {
	
	Item findByName(String name);
	List<Item> findAllByNameIn(List<String> names);

}
