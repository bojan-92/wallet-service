package com.leovegas.wallet.repository;

import com.leovegas.wallet.model.Player;
import com.leovegas.wallet.model.Transaction;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long> {

  List<Transaction> findAllByPlayer(Player player);

}
