package com.kpsec.test.service;

import com.kpsec.test.model.AccountResult;
import com.kpsec.test.model.AmountByyearRankResult;
import com.kpsec.test.model.ShopResult;
import com.kpsec.test.model.TransactionResult;
import com.kpsec.test.repository.AccountRepository;
import com.kpsec.test.repository.ShopRepository;
import com.kpsec.test.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class TransactionService {

    @Autowired
    private TransactionRepository transactionRepository;

    public List<TransactionResult> getTransaction(){
        List<TransactionResult> aa = transactionRepository.getTransaction();
        return aa;
    }

    public List<AmountByyearRankResult> getAmountByyearRank(){
        List<AmountByyearRankResult> AmountByyearRankList = transactionRepository.getTransactionResultList();
        return AmountByyearRankList;
    }
}
