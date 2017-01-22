package com.allstate.repositories;

import com.allstate.entities.Transaction;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.math.BigDecimal;
import java.util.Optional;

public interface ITransactionRepository extends CrudRepository<Transaction, Integer> {
    String sql1 = "select sum(quantity) from transactions where user_id = :userId and action = :action and symbol = :symbol";
    @Query(value = sql1, nativeQuery = true)
    public Optional<BigDecimal> countSharesPurchasedOrSoldBySymbol(@Param("userId") int userId, @Param("action") String action, @Param("symbol") String symbol);
}
