package com.allstate.repositories;

import com.allstate.entities.Transaction;
import org.springframework.data.repository.CrudRepository;

public interface ITransactionRepository extends CrudRepository<Transaction, Integer> {
}
