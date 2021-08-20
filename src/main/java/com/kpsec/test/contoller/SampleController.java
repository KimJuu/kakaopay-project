package com.kpsec.test.contoller;

import java.util.List;

import com.kpsec.test.model.AmountByyearRankResult;
import com.kpsec.test.model.ShopResult;
import com.kpsec.test.model.TransactionResult;
import com.kpsec.test.service.ShopService;
import com.kpsec.test.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kpsec.test.model.AccountResult;
import com.kpsec.test.service.AccountService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(tags = "Sample")
@RestController
@RequestMapping("/test/")
public class SampleController {
    @Autowired
    private AccountService accountService;
    @Autowired
    private ShopService shopService;
    @Autowired
    private TransactionService transactionService;

    @ApiOperation(value = "sample")
    @GetMapping(value = "/account")
    public List<AccountResult> getAccountInfo(String branchCode) {
        return accountService.getAccountByBranchCode(branchCode);
    }

    @ApiOperation(value = "sample")
    @GetMapping(value = "/shop")
    public List<ShopResult> getShopList() {
        return shopService.getShopList();
    }

    @ApiOperation(value = "sample")
    @GetMapping(value = "/transaction")
    public List<TransactionResult> getTransactionList() {
        return transactionService.getTransaction();
    }

    //1.2018년, 2019년 각 연도별 합계 금액이 가장 많은 고객을 추출하는 API 개발.(단, 취소여부가 ‘Y’ 거래는 취소된 거래임, 합계 금액은 거래금액에서 수수료를 차감한 금액임)
    @ApiOperation(value = "sample")
    @GetMapping(value = "/amount/byyear-rank")
    public List<AmountByyearRankResult> getAmountByyearRank() {
        return transactionService.getAmountByyearRank();
    }

}
