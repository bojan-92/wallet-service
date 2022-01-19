package com.leovegas.wallet.service;

import com.leovegas.wallet.controller.dto.CreateTransactionRequest;
import com.leovegas.wallet.controller.dto.PlayerTransactionHistoryResponse;
import com.leovegas.wallet.controller.dto.TransactionDto;
import com.leovegas.wallet.error.WalletException;
import com.leovegas.wallet.model.Player;
import com.leovegas.wallet.model.Transaction;
import com.leovegas.wallet.model.constant.TransactionType;
import com.leovegas.wallet.repository.TransactionRepository;
import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
    Player player = playerService.findById(request.getPlayerId());
    TransactionType transactionType = TransactionType.findByValue(request.getType());

    if (transactionType == null) {
      throw new WalletException("Transaction type is not valid", HttpStatus.BAD_REQUEST);
    }

    updatePlayerBalance(player, transactionType, request.getAmount());

    transactionRepository.save(
        Transaction.builder()
            .player(player)
            .type(transactionType)
            .amout(request.getAmount())
            .build());
  }

  @Override
  public PlayerTransactionHistoryResponse getPlayerTransactionHistory(Long playerId) {
    Player player = playerService.findById(playerId);
    List<Transaction> transactions = transactionRepository.findAllByPlayer(player);
    return PlayerTransactionHistoryResponse
        .builder()
        .playerName(player.getName())
        .transactions(
            transactions
                .stream()
                .map(this::transactionMapToTransactionDto)
                .collect(Collectors.toList()))
        .build();
  }

  private TransactionDto transactionMapToTransactionDto(Transaction transaction) {
    return TransactionDto.builder().type(transaction.getType()).amount(transaction.getAmout()).build();
  }

  private void updatePlayerBalance(Player player, TransactionType transactionType, BigDecimal amount) {
    if (TransactionType.DEBIT.equals(transactionType)) {
      if (!isDebitTransactionPossible(player.getBalance(), amount)) {
        throw new WalletException("Debit transaction is not possible", HttpStatus.BAD_REQUEST);
      }
      player.setBalance(player.getBalance().subtract(amount));
    } else if (TransactionType.CREDIT.equals(transactionType)) {
      player.setBalance(player.getBalance().add(amount));
    }
  }

  private boolean isDebitTransactionPossible(BigDecimal currentBalance, BigDecimal debitTransactionAmount) {
    return currentBalance.subtract(debitTransactionAmount).compareTo(BigDecimal.ZERO) >= 0;
  }

}
