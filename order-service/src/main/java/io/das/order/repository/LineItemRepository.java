package io.das.order.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import io.das.order.entity.LineItem;

@Repository
public interface LineItemRepository extends JpaRepository<LineItem, Long>  {

}
