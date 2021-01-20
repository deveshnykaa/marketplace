package com.api.mktplace.nyk_marketplace.dao;

import com.api.mktplace.nyk_marketplace.entity.Inventory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.Optional;

@Repository
public interface InventoryRepository extends JpaRepository<Inventory, Integer> {
    public Optional<Inventory> findBySku(String sku);

    public Optional<Inventory> findByProdIdAndVarId(String prodId, String varId);

    @Modifying
    @Transactional
    @Query(value = "UPDATE inventory SET inv_qty =:i, sale_price =:sa WHERE sku =:s", nativeQuery = true)
    public void updateByV3(@Param("s") String sku, @Param("i") double invQty, @Param("sa") double salePrice);

    @Modifying
    @Transactional
    @Query(value = "UPDATE inventory SET inv_qty =:i WHERE prod_id =:pid AND var_id =:vid", nativeQuery = true)
    public void updateByV2(@Param("pid") String prodId, @Param("vid") String varId, @Param("i") double invQty);
}
