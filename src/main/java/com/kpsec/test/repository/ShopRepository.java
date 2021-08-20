package com.kpsec.test.repository;

import com.kpsec.test.model.ShopResult;
import com.kpsec.test.model.entity.Shop;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ShopRepository extends JpaRepository<Shop, String> {

    @Query(value = "SELECT branch_code as branchCode, branch_name as branchName FROM shop", nativeQuery = true)
    List<ShopResult> getShop();

}
