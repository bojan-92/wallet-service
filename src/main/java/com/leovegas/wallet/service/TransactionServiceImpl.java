package com.leovegas.wallet.service;

import com.leovegas.wallet.controller.dto.CreateTransactionRequest;
import com.leovegas.wallet.model.Player;
import com.leovegas.wallet.model.Transaction;
import com.leovegas.wallet.model.constant.TransactionType;
import com.leovegas.wallet.repository.TransactionRepository;
import java.math.BigDecimal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class TransactionServiceImpl implements TransactionService {

  @Autowired
  private TransactionRepository transactionRepository;

  @Autowired
  private PlayerService playerService;

  @Override
  @Transactional(propagation = Propagation.REQUIRES_NEW)
  public void create(CreateTransactionRequest request) {
    Player player = playerService.findPlayerById(request.getPlayerId());
    transactionRepository.save(
        Transaction.builder()
            .player(player)
            .type(TransactionType.findByValue(request.getType()))
            .amout(request.getAmount())
            .build());
  }

}
