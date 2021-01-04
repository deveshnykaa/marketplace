package com.api.mktplace.nyk_marketplace.dao;

import com.api.mktplace.nyk_marketplace.entity.Seller;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;

public interface InventoryRepository extends CrudRepository<Seller, Integer> {
    @Modifying
    @Transactional
    @Query(value = "UPDATE seller SET price =:pr, quantity =:q WHERE product_id =:pid", nativeQuery = true)
    public void updateByPID(@Param("pid") int productId, @Param("pr") String price, @Param("q") String quantity);
}
