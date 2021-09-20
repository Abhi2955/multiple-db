package com.jiohh.poc.multipledb.repository.db2;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.jiohh.poc.multipledb.model.db2.Transaction;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long>{}
