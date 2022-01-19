package com.leovegas.wallet.setup;

import com.leovegas.wallet.model.Player;
import com.leovegas.wallet.model.Transaction;
import com.leovegas.wallet.model.constant.TransactionType;
import com.leovegas.wallet.repository.PlayerRepository;
import com.leovegas.wallet.repository.TransactionRepository;
import java.math.BigDecimal;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class DataSetup {

  @Autowired
  private PlayerRepository playerRepository;

  @Autowired
  private TransactionRepository transactionRepository;

  public void cleanData() {
    log.info("Deleting Test Data...");
    transactionRepository.deleteAll();
    playerRepository.deleteAll();
  }

  public Player createPlayer(Long id, String name, BigDecimal balance) {
    return playerRepository.save(Player.builder().id(id).name(name).balance(balance).build());
  }

  public void createTransaction(Long id, TransactionType type, BigDecimal amount, Player player) {
    transactionRepository.save(Transaction.builder().id(id).type(type).amout(amount).player(player).build());
  }

}
