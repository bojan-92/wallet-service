package com.leovegas.wallet.service;

import com.leovegas.wallet.controller.dto.CreateTransactionRequest;
import com.leovegas.wallet.controller.dto.PlayerTransactionHistoryResponse;

public interface TransactionService {

  void create(CreateTransactionRequest request);

  PlayerTransactionHistoryResponse getPlayerTransactionHistory(Long playerId);

}
