package com.kpsec.test.infra;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.List;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;

import com.kpsec.test.model.entity.Shop;
import com.kpsec.test.model.entity.Transaction;
import com.kpsec.test.repository.ShopRepository;
import com.kpsec.test.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import com.kpsec.test.model.entity.Account;
import com.kpsec.test.repository.AccountRepository;

@Component
public class InitData {

    @Autowired
    AccountRepository accountRepository;
    @Autowired
    ShopRepository shopRepository;
    @Autowired
    TransactionRepository transactionRepository;

    @PostConstruct
    private void initAccount() throws IOException {
        if (accountRepository.count() == 0) {
            Resource resource = new ClassPathResource("계좌정보.csv");
            List<Account> accountList = Files.readAllLines(resource.getFile().toPath(), StandardCharsets.UTF_8)
                    .stream().skip(1).map(line -> {
                        String[] split = line.split(",");
                        return Account.builder().accountNo(split[0]).accountName(split[1]).branchCode(split[2])
                                .build();
                    }).collect(Collectors.toList());
            accountRepository.saveAll(accountList);
        }

        if (shopRepository.count() == 0) {
            Resource resource = new ClassPathResource("관리점정보.csv");
            List<Shop> shopList = Files.readAllLines(resource.getFile().toPath(), StandardCharsets.UTF_8)
                    .stream().skip(1).map(line -> {
                        String[] split = line.split(",");
                        return Shop.builder().branchCode(split[0]).branchName(split[1])
                                .build();
                    }).collect(Collectors.toList());
            shopRepository.saveAll(shopList);
        }

        if (transactionRepository.count() == 0) {
            Resource resource = new ClassPathResource("거래내역.csv");
            List<Transaction> transactionList = Files.readAllLines(resource.getFile().toPath(), StandardCharsets.UTF_8)
                    .stream().skip(1).map(line -> {
                        String[] split = line.split(",");
                        return Transaction.builder().trDate(split[0]).accountNo(split[1]).trNo(split[2]).trAmount(Integer.parseInt(split[3])).trFee(Integer.parseInt(split[4])).cancelYN(split[5])
                                .build();
                    }).collect(Collectors.toList());
            transactionRepository.saveAll(transactionList);
        }

    }
}
