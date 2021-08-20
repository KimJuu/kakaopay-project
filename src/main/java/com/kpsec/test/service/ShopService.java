package com.kpsec.test.service;

import com.kpsec.test.model.AccountResult;
import com.kpsec.test.model.ShopResult;
import com.kpsec.test.model.TransactionResult;
import com.kpsec.test.model.entity.Shop;
import com.kpsec.test.repository.AccountRepository;
import com.kpsec.test.repository.ShopRepository;
import com.kpsec.test.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class ShopService {

    @Autowired
    private ShopRepository shopRepository;

    public List<ShopResult> getShopList(){
        List<ShopResult> aa = shopRepository.getShop();
        return aa;
    }
}
