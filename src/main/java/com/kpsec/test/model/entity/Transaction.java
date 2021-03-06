package com.kpsec.test.model.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Transaction {
    @Id
    private String trDate;

    private String accountNo;

    private String trNo;

    private int trAmount;

    private int trFee;

    private String cancelYN;
}
