package com.kpsec.test.repository;

import com.kpsec.test.model.AmountByyearRankResult;
import com.kpsec.test.model.TransactionResult;
import com.kpsec.test.model.entity.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TransactionRepository extends JpaRepository<Transaction, String> {

    @Query(value = "SELECT * FROM transaction", nativeQuery = true)
    List<TransactionResult> getTransaction();

    @Query(value = "SELECT YEAR, NAME, ACCT_NO, SUM_AMT FROM (\n" +
            "SELECT YEAR,\n" +
            "   NAME,\n" +
            "   ACCT_NO,   \n" +
            "   SUM_AMT,\n" +
            "   RANK() OVER (PARTITION BY YEAR ORDER BY SUM_AMT DESC) AS RANK \n" +
            "FROM (\n" +
            "SELECT YEAR(A.TR_DATE) AS YEAR,\n" +
            "          B.ACCOUNT_NAME AS NAME,\n" +
            "          B.ACCOUNT_NO AS ACCT_NO,\n" +
            "          SUM(TR_AMOUNT-TR_FEE) AS SUM_AMT,\n" +
            " FROM TRANSACTION A \n" +
            "      LEFT OUTER JOIN ACCOUNT B ON A.ACCOUNT_NO = B.ACCOUNT_NO\n" +
            "WHERE A.CANCELYN = 'N' \n" +
            "AND YEAR(A.TR_DATE) IN ('2018','2019')\n" +
            "GROUP BY  YEAR(A.TR_DATE),B.ACCOUNT_NAME,B.ACCOUNT_NO)\n" +
            ")\n" +
            "\n" +
            "WHERE RANK = '1' ORDER BY YEAR ASC\n", nativeQuery = true)
    List<AmountByyearRankResult> getTransactionResultList();

}
